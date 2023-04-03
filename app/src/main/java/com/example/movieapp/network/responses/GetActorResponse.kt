package com.example.movieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.example.movieapp.data.vos.ActorVO

data class GetActorResponse(
    @SerializedName("results")
    val results:List<ActorVO>?
)