package com.example.carrot.model

import androidx.annotation.DrawableRes
import com.example.carrot.R
import com.google.gson.annotations.SerializedName

data class SalePost(
    val postId: Long,
    val title: String = "아이폰 14 프로 싸게 싸게 팝니다~",
    val category: String = "apple",
    val location: String = "신매동",
    val createdAt: Int = 20,
    val price: Int = 1400000,
    val contactNum: Int,
    val likeNum: Int,
    val content: String = """
        아이폰 뜯고 나서 10일 사용했습니다 급처합니다
        아이폰 뜯고 나서 10일 사용했습니다 급처합니다 아이폰 뜯고 나서 10일 사용했습니다 급처합니다
        아이폰 뜯고 나서 10일 사용했습니다 급처합니다
        아이폰 뜯고 나서 10일 사용했습니다 급처합니다
        아이폰 뜯고 나서 10일 사용했습니다 급처합니다
        아이폰 뜯고 나서 10일 사용했습니다 급처합니다
        아이폰 뜯고 나서 10일 사용했습니다 급처합니다
        """.trimIndent(),
    val writer: User = SampleData.sampleUser[0],
    @DrawableRes val titleImage: Int = R.drawable.testpic
)

data class SalePostResponse(
    @SerializedName("salespostid")
    val salePostId: Long,
    val category: String,
    val title: String,
    val username: String,
    val content: String,
    val isPostState: String,
    val price: Int,
    val chatNum: Int,
    val likeNum: Int,
    @SerializedName("locate")
    val location: String,
    @SerializedName("salesimg")
    val salesImg: String,
    @SerializedName("updatetime")
    val updatedAt: String,
)

data class SalePostRequest(
    val category: String,
    val title: String,
    val content: String,
    val price: Int,
    @SerializedName("salesimg")
    val salesImg: String,
    val isPostState: String
)

data class ImageResponse(
    val path: String
)
//data class SalePost(
//    val id: Long,
//    val title: String,
//    val category: String,
//    val location: String,
//    val createdAt: Int,
//    val price: Int,
//    val contactNum: Int,
//    val likeNum: Int,
//    val content: String,
//    val writer: User,
//    @DrawableRes val titleImage: Int
//)