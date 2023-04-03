package com.example.movieapp.persistence.typecoverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.movieapp.data.vos.SpokenLanguageVO

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguages:List<SpokenLanguageVO>?): String{
        return Gson().toJson(spokenLanguages)
    }
    @TypeConverter
    fun toCollectionVO(commentListJsonStr: String):List<SpokenLanguageVO>?{
        val spokenLanguagesVOType = object : TypeToken<List<SpokenLanguageVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,spokenLanguagesVOType)
    }
}