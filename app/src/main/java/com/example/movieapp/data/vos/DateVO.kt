package com.example.movieapp.data.vos

import com.google.gson.annotations.SerializedName

data class DateVO(
    @SerializedName("maximum")
    val maximum:String?,
    @SerializedName("minimum")
    val minimum:String?
)