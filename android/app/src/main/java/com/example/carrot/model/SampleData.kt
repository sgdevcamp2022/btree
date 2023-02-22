package com.example.carrot.model

object SampleData {

    val sampleUser = listOf<User>(
        User(
            name = "김상수"
        ),
        User(
            name = "신승용"
        )
    )

    val sampleSalePost = listOf<SalePost>(
        SalePost(
            postId = 1,
            contactNum = 0,
            likeNum = 0,
        ),
        SalePost(
            postId = 2,
            contactNum = 2,
            likeNum = 0,
        ),
        SalePost(
            postId = 3,
            contactNum = 0,
            likeNum = 2,
        ),
        SalePost(
            postId = 4,
            contactNum = 2,
            likeNum = 2,
            writer = sampleUser[1]
        ),
        SalePost(
            postId = 5,
            contactNum = 10,
            likeNum = 0,
        ),
        SalePost(
            postId = 6,
            contactNum = 0,
            likeNum = 10,
            writer = sampleUser[1]
        ),
        SalePost(
            postId = 7,
            contactNum = 10,
            likeNum = 10,
        ),
        SalePost(
            postId = 8,
            contactNum = 10,
            likeNum = 10,
        ),
        SalePost(
            postId = 9,
            contactNum = 10,
            likeNum = 10,
        ),
        SalePost(
            postId = 10,
            contactNum = 10,
            likeNum = 10,
        )
    )

    val sampleComPost = listOf<ComPost>(
        ComPost(
            postId = 1,
            likesNum = 0,
            commentNum = 0
        ),
        ComPost(
            postId = 2,
            likesNum = 2,
            commentNum = 0
        ),
        ComPost(
            postId = 3,
            likesNum = 0,
            commentNum = 3
        ),
        ComPost(
            postId = 4,
            likesNum = 4,
            commentNum = 4
        ),
        ComPost(
            postId = 5,
            likesNum = 10,
            commentNum = 0
        ),ComPost(
            postId = 6,
            likesNum = 0,
            commentNum = 10
        ),
        ComPost(
            postId = 7,
            likesNum = 7,
            commentNum = 70
        ),
        ComPost(
            postId = 8,
            likesNum = 80,
            commentNum = 8
        ),
        ComPost(
            postId = 9,
            likesNum = 90,
            commentNum = 90
        )
    )

}