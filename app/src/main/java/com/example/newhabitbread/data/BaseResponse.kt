package com.example.newhabitbread.data

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("message")
    val message: String
)