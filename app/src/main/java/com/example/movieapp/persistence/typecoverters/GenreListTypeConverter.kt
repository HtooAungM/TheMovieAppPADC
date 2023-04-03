package com.example.movieapp.persistence.typecoverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.movieapp.data.vos.GenreVO

class GenreListTypeConverter {
    @TypeConverter
    fun toString(genreList: List<GenreVO>?): String{
        return Gson().toJson(genreList)
    }
    @TypeConverter
    fun toCollectionVO(commentListJsonStr: String): List<GenreVO>?{
        val genreListVOType = object : TypeToken<List<GenreVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,genreListVOType)
    }
}