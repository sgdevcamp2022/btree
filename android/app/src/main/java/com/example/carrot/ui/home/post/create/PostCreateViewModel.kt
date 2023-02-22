package com.example.carrot.ui.home.post.create

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.core.net.toFile
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient
import com.example.carrot.api.RetrofitClient.PostUtilApiService
import com.example.carrot.api.RetrofitClient.SalePostApiService
import com.example.carrot.model.SalePostRequest
import com.example.carrot.model.TokenStore
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okio.BufferedSink
import okio.source
import java.io.File

class PostCreateViewModel(

):ViewModel() {
    private val _title = mutableStateOf("")
    val title = _title
    val setTitle: (String) -> Unit = {
        _title.value = it
    }

    private val _price = mutableStateOf("")
    val price = _price
    val setPrice: (String) -> Unit = {
        _price.value = it
    }

    private val _contents = mutableStateOf("")
    val contents = _contents
    val setContents: (String) -> Unit = {
        _contents.value = it
    }

//    private val imagesUris = mutableStateListOf<Uri?>()
//    val setImageUris: (List<Uri>) -> Unit = {
//        imagesUris.addAll(it)
//    }

    private val imageUri = mutableStateOf("")

    suspend fun createSalePost(context: Context) {
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect {
            try {
                val userResponse = RetrofitClient.authApiService.getMyInfo("Bearer $it")
                when(userResponse.code()){
                    200 -> {
                        val salePostRequest = SalePostRequest(
                            category = "test",
                            title = _title.value,
                            content = _contents.value,
                            price = _price.value.toInt(),
                            salesImg = imageUri.value,
                            gpsauth = true,
                            locate = "개신동",
                            nickname = userResponse.body()?.nickname!!,
                            useremail = userResponse.body()?.email!!
                        )
                        Log.i("CREATE SALEPOST", "saleImg : ${salePostRequest.salesImg}, imageUri : ${imageUri.value}")
                        try {
                            val response = SalePostApiService.createSalePost(salePostRequest = salePostRequest)
                            when (response.code()) {
                                200 -> Log.i("CREATE SALEPOST", "create sale post success")
                                else -> Log.i("CREATE SALEPOST", "create sale post failed : ${response.code()}")
                            }
                        } catch (e: Exception) {
                            Log.i("CREATE SALEPOST", "create sale post failed : $e")
                        }
                    }
                    else -> {
                        Log.e("CREATE SALEPOST", "responsing user profile failed: ${userResponse.code()}")
                    }
                }
            } catch (e: Exception){
                Log.e("CREATE SALEPOST", "get user profile failed: $e")
            }
        }
    }

    suspend fun uploadImage(uri: Uri, context: Context) {

        val file = uri.asMultipart("files", context.contentResolver)

        try {
            val response = PostUtilApiService.uploadPostImage(files = file!!)
            when (response.code()) {
                200 -> {
                    imageUri.value = response.body()?.path!!
                    Log.i("CREATE SALEPOST", "upload image url : ${response.body()?.path}")
                }
                else -> {
                    Log.e("CREATE SALEPOST", "upload image failed : ${response.code()}")
                }
            }
        } catch (e: Exception) {
            Log.e("CREATE SALEPOST", "upload image failed with response : $e")
        }
    }
}

@SuppressLint("Range")
fun Uri.asMultipart(name: String, contentResolver: ContentResolver): MultipartBody.Part? {
    return contentResolver.query(this, null, null, null, null)?.let {
        if (it.moveToNext()) {
            val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            val requestBody = object : RequestBody() {
                override fun contentType(): MediaType {
                    return ("images/jpeg").toMediaType()
                }

                override fun writeTo(sink: BufferedSink) {
                    sink.writeAll(contentResolver.openInputStream(this@asMultipart)?.source()!!)
                }
            }
            it.close()
            MultipartBody.Part.createFormData(name, displayName, requestBody)
        } else {
            it.close()
            null
        }
    }
}