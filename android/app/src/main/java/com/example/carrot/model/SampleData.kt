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
}