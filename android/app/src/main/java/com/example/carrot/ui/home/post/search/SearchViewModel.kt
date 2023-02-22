package com.example.carrot.ui.home.post.search

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carrot.api.PostUtilApi
import com.example.carrot.api.RetrofitClient.PostUtilApiService
import com.example.carrot.model.PostHit
import com.example.carrot.model.SalePostResponse

class SearchViewModel(

): ViewModel() {
    private val _searchItem = mutableStateOf("")
    val searchItem = _searchItem

    val setSearchItem: (String) -> Unit = {
        _searchItem.value = it
    }

    private var _searchPostList = mutableStateListOf<SalePostResponse>()
    val searchPostList = _searchPostList

    suspend fun setSearchPostList(){
        try {
            val response = PostUtilApiService.searchSalePost(_searchItem.value)
            when(response.code()){
                200 -> {
                    response.body()?.let {
                        val tempPostList = mutableListOf<SalePostResponse>()
                        it.hits.hits.forEach { post ->
                            tempPostList.add(
                                SalePostResponse(
                                    salePostId = post._id.toLong(),
                                    category = post._source.category,
                                    title = post._source.title,
                                    nickname = post._source.nickname,
                                    content = post._source.content,
                                    isPostState = post._source.ispoststate,
                                    price = post._source.price,
                                    chatNum = post._source.chatnum,
                                    likeNum = post._source.likenum,
                                    salesImg = post._source.salesimg,
                                    location = post._source.locate,
                                    updatedAt = post._source.updatedAt,
                                    useremail = post._source.useremail,
                                    viewCount = post._source.viewcount
                                )
                            )
                        }
                        Log.i("SEARCHSCREEN", "getSalePostList success  : ${tempPostList[0].title}")
                        _searchPostList.addAll(tempPostList)
                    }
                }
                else -> {
                    Log.e("SEARCHSCREEN", "getSalePostList request(${response.code()}) failed : ${response.body()}")
                }
            }
        } catch (e: Exception){
            Log.e("SEARCHSCREEN", "getSalePostList failed : $e")
        }
    }
}