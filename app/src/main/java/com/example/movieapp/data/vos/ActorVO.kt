package com.example.movieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ActorVO(
    @SerializedName("adult")
    val adult:Boolean?,
    @SerializedName("gender")
    val gender:Int?,
    @SerializedName("id")
    val id:Int?,
    @SerializedName("known_for")
    val knownFor:List<MovieVO>,
    @SerializedName("known_for_department")
    val knownForDepartment:String?,
    @SerializedName("name")
    val name:String?,
    @SerializedName("popularity")
    val popularity:Double?,
    @SerializedName("profile_path")
    val profilePath:String?,
    @SerializedName("original_name")
    val originalName:String?,
    @SerializedName("credit_id")
    val creditId:String?,
    @SerializedName("department")
    val department:String?,
    @SerializedName("job")
    val job:String?,
    @SerializedName("cast_id")
    val castId:Int?,
    @SerializedName("character")
    val character:String?,
    @SerializedName("order")
    val order:Int?,
)