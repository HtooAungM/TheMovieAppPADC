package com.example.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.example.movieapp.data.vos.GenreVO

data class GetGenreResponse(
    @SerializedName("genres")
    val genres:List<GenreVO>?
)