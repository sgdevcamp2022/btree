package com.example.carrot.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    val took: Int,
    val timed_out: Boolean,
    val _shards: Shards,
    val hits: PostHits
)

data class Total(
    val value: Int,
    val relation: String
)

data class Shards(
    val total: Int,
    val successful: Int,
    val skipped: Int,
    val failed: Int
)

data class PostHits(
    val total: Total,
    val max_score: Float,
    val hits: List<PostHit>
)

data class PostHit(
    val _index: String,
    val _id: String,
    val _score: Float,
    val _source: PostSource
)

data class PostSource(
    val content: String,
    val nickname: String,
    val price: Int,
    val salesimg: String,
    val title: String,
    @SerializedName("@timestamp")
    val updatedAt: String,
    val chatnum: Int,
    val category: String,
    val locate: String,
    val viewcount: Int,
    val ispoststate: String,
    val updatetime: String,
    val useremail: String,
    val likenum: Int
)