package com.example.movieapp.persistence.typecoverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.movieapp.data.vos.ProductionCountryVO

class ProtectionCountriesTypeConverter {
    @TypeConverter
    fun toString(productionCountries: List<ProductionCountryVO>?): String{
        return Gson().toJson(productionCountries)
    }
    @TypeConverter
    fun toCollectionVO(commentListJsonStr: String):List<ProductionCountryVO>?{
        val productionCountriesVOType = object : TypeToken<List<ProductionCountryVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,productionCountriesVOType)
    }
}