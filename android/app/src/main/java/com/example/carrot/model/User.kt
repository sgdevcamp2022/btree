package com.example.carrot.model

import androidx.annotation.DrawableRes
import com.example.carrot.R

data class User(
    val id: Long = 100,
    val name: String,
    val location: String = "신매동",
    val manner: Float = 36.5f,
    @DrawableRes val profileImage: Int = R.drawable.default_profile
)

//data class User(
//    val id: Long,
//    val name: String,
//    val location: String,
//    val manner: Float,
//    @DrawableRes val profileImage: Int
//)
