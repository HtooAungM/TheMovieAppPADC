package com.example.movieapp.persistence.typecoverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.movieapp.data.vos.CollectionVO

class CollectionTypeConverter {
    @TypeConverter
    fun toString(collectionVO: CollectionVO?): String{
        return Gson().toJson(collectionVO)
    }
    @TypeConverter
    fun toCollectionVO(commentListJsonStr: String): CollectionVO?{
        val collectionVOType = object :TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(commentListJsonStr,collectionVOType)
    }
}