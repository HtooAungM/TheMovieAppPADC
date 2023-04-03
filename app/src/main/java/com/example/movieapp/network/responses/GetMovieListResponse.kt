package com.example.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.example.movieapp.data.vos.DateVO
import com.example.movieapp.data.vos.MovieVO

data class GetMovieListResponse(
    @SerializedName("dates")
    val date: DateVO?,
    @SerializedName("page")
    val page:Int?,
    @SerializedName("results")
    val results:List<MovieVO>?
)