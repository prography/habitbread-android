package com.example.newhabitbread.data

import com.google.gson.annotations.SerializedName

data class RankResponse(
    @SerializedName("user")
    val user : Ranking?,
    @SerializedName("userTotalCount")
    val userTotalCount: Int,
    @SerializedName("rankings")
    val rankings : List<Ranking>
)