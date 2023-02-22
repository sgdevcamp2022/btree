package com.example.carrot.model

import com.google.gson.annotations.SerializedName

data class ComPost(
    val postId: Long,
    val writer: User = SampleData.sampleUser[0],
    val title: String = "안드로이드 잘하시는분 구합니다",
    val category: String = "개발",
    val location: String = "개신동",
    val createdAt: String = "20",
    val likesNum: Int,
    val commentNum: Int,
    val views: Int = 100,
    val content: String = """
        안드로이드 컴포즈는 너무 어려운거 같습니다. 코틀린도 잘할려면 더 노력이 필요해보입니다.
        안드로이드 컴포즈는 너무 어려운거 같습니다. 코틀린도 잘할려면 더 노력이 필요해보입니다.
        안드로이드 컴포즈는 너무 어려운거 같습니다. 코틀린도 잘할려면 더 노력이 필요해보입니다.
        안드로이드 컴포즈는 너무 어려운거 같습니다. 코틀린도 잘할려면 더 노력이 필요해보입니다.
    """.trimIndent()
)

data class ComPostResponse(
    @SerializedName("boardpostid")
    val comPostId: Long,
    val title: String,
    val nickname: String,
    @SerializedName("useremail")
    val userEmail: String,
    val content: String,
    val commentNum: Int,
    val likesNum: Int,
    val viewcount: Int,
    @SerializedName("locate")
    val location: String,
    @SerializedName("contentimg")
    val contentImg: String,
    @SerializedName("updatetime")
    val updatedAt: String,
)

data class ComPostRequest(
    val title: String,
    val content: String,
    @SerializedName("contentimg")
    val contentImg: String,
    val nickname: String,
    @SerializedName("useremail")
    val userEmail: String,
    @SerializedName("locate")
    val location: String,
    val gpsauth: Boolean
)

data class ComPostUserModel(
    val gpsauth: Boolean,
    val locate: String,
    val nickname: String,
    val useremail: String
)

data class CommentResponse(
    @SerializedName("boardcommentid")
    val boardCommentId: Long,
    @SerializedName("boardpostid")
    val boardPostId: Long,
    @SerializedName("commenttime")
    val commentTime: String,
    val content : String,
    val username: String
)


data class CommentRequest(
    @SerializedName("boardpostid")
    val boardPostId: Long,
    val content: String,
    val username: String
)
//data class ComPost(
//    val postId: Long,
//    val writer: User,
//    val title: String,
//    val category: String,
//    val location: String,
//    val createdAt: String,
//    val likesNum: Int,
//    val commentNum: Int,
//    val views: Int,
//    val content: String
//)
