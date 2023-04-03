package com.example.movieapp.persistence.typecoverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdTypeConverter {
    @TypeConverter
    fun toString(genreIds: List<Int>?): String{
        return Gson().toJson(genreIds)
    }
    @TypeConverter
    fun toCollectionVO(commentListJsonStr: String): List<Int>?{
        val genreIdVOType = object : TypeToken<List<Int>?>(){}.type
        return Gson().fromJson(commentListJsonStr,genreIdVOType)
    }
}