package com.example.newhabitbread.data

import com.google.gson.annotations.SerializedName

data class GoogleOAuthRequest(
    @SerializedName("idToken")
    val idToken: String
)