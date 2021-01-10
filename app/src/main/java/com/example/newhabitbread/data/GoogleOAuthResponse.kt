package com.example.newhabitbread.data

import com.google.gson.annotations.SerializedName

data class GoogleOAuthResponse(
    @SerializedName("accessToken")
    val idToken: String,
    @SerializedName("isNewUser")
    val isNewUser: Boolean
)