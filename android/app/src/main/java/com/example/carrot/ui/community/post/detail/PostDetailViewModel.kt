package com.example.carrot.ui.community.post.detail

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.RetrofitClient
import com.example.carrot.api.RetrofitClient.ComPostApiService
import com.example.carrot.model.ComPostResponse
import com.example.carrot.model.CommentRequest
import com.example.carrot.model.CommentResponse
import com.example.carrot.model.TokenStore
import kotlinx.coroutines.flow.collect

class PostDetailViewModel(

): ViewModel() {
    private val _comPostDetail = mutableStateOf(ComPostResponse(
        comPostId = 0,
        title = "",
        nickname = "",
        userEmail = "",
        content = "",
        commentNum = 0,
        likesNum = 0,
        viewcount = 0,
        location = "",
        contentImg = "",
        updatedAt = ""
    ))
    val comPostDetail = _comPostDetail

    suspend fun setPostDetailData(postId: Long){
        try {
            val response = ComPostApiService.getComPostDetail(postId)
            when(response.code()){
                200 -> {
                    _comPostDetail.value = response.body()!!
                    Log.i("POST DETAIL", "post detail success : ${response.body()?.nickname}")
                }
                else -> {
                    Log.i("POST DETAIL", "post detail failed : ${response.code()}")
                }
            }
        } catch (e: Exception){
            Log.e("POST DETAIL", "post detail failed : $e")
        }
    }

    private val _comment = mutableStateOf("")
    val comment = _comment
    val setComment: (String) -> Unit = {
        _comment.value = it
    }
    suspend fun uploadComment(context: Context, postId: Long){
        val tokenStore = TokenStore(context)
        tokenStore.getAccessToken.collect{
            try {
                val userResponse = RetrofitClient.authApiService.getMyInfo(("Bearer $it"))
                when(userResponse.code()){
                    200 -> {
                        val commentRequest = CommentRequest(
                            boardPostId = postId,
                            content = _comment.value,
                            username = userResponse.body()?.nickname!!
                        )
                        try {
                            val response = ComPostApiService.createComPostComment(postId, commentRequest)
                            when(response.code()){
                                200 -> {
                                    response.body()?.let { updatedComment ->
                                        _commentList.add(updatedComment)
                                    }
                                }
                                else -> {
                                    Log.e("POST DETAIL", "create comment failed : ${response.code()}")
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("POST DETAIL", "create commentList failed : $e")
                        }
                    }
                    else -> {
                        Log.e("POST DETAIL", "get user profile failed : ${userResponse.code()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("POST DETAIL", "get user profile failed : $e")
            }
        }
    }


    private val _commentList = mutableStateListOf<CommentResponse>()
    val commentList = _commentList
    suspend fun setCommentList(postId: Long){
        try {
            val response = ComPostApiService.getCommentList(postId,0, 10)
            when(response.code()){
                200 -> {
                    response.body()?.let {
                        _commentList.addAll(it)
                        Log.i("POST DETAIL", "get commentList $postId success : ${it[0].content}")
                    }
                }
                else -> {
                    Log.e("POST DETAIL", "get commentList failed : ${response.code()}")
                }
            }
        } catch (e: Exception) {
            Log.e("POST DETAIL", "get commentList $postId failed : $e")
        }
    }
}