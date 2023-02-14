package com.example.carrot.model

import androidx.annotation.DrawableRes
import com.example.carrot.R

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