package com.example.movieapp.persistence.typecoverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.movieapp.data.vos.ProductionCompanyVO

class ProductionCompaniesTypeConverter {
    @TypeConverter
    fun toString(productionCompanies: List<ProductionCompanyVO>?): String{
        return Gson().toJson(productionCompanies)
    }
    @TypeConverter
    fun toCollectionVO(commentListJsonStr: String): List<ProductionCompanyVO>?{
        val productionCompaniesVOType = object : TypeToken<List<ProductionCompanyVO>?>(){}.type
        return Gson().fromJson(commentListJsonStr,productionCompaniesVOType)
    }
}

