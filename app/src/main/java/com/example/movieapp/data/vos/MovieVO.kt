package com.example.movieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.movieapp.persistence.typecoverters.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
@TypeConverters(
    CollectionTypeConverter::class,
    GenreIdTypeConverter::class,
    GenreListTypeConverter::class,
    ProductionCompaniesTypeConverter::class,
    ProtectionCountriesTypeConverter::class,
    SpokenLanguageTypeConverter::class
)
data class MovieVO(
    @SerializedName("adult")
    @ColumnInfo(name = "adult")
    val adult: Boolean?,
    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,
    @SerializedName("genre_ids")
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>?,
    @SerializedName("id")
    @PrimaryKey
    val id: Int?,
    @SerializedName("original_language")
    @ColumnInfo(name = "original_language")
    val originalLanguage: String?,
    @SerializedName("original_title")
    @ColumnInfo(name = "original_title")
    val originalTitle: String?,
    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    val overview: String?,
    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    val popularity: Double?,
    @SerializedName("poster_path")
    @ColumnInfo(name = "poster_path")
    val posterPath: String?,
    @SerializedName("release_date")
    @ColumnInfo(name = "release_date")
    val releaseDate: String?,
    @SerializedName("title")
    @ColumnInfo(name = "title")
    val title: String?,
    @SerializedName("video")
    @ColumnInfo(name = "video")
    val video: Boolean?,
    @SerializedName("vote_average")
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double?,
    @SerializedName("vote_count")
    @ColumnInfo(name = "vote_count")
    val voteCount: Int?,
    @SerializedName("production_companies")
    @ColumnInfo(name = "production_companies")
    val productionCompanies:List<ProductionCompanyVO>?,
    @SerializedName("production_countries")
    @ColumnInfo(name = "production_countries")
    val productionCountries:List<ProductionCountryVO>?,
    @SerializedName("spoken_languages")
    @ColumnInfo(name = "spoken_languages")
    val spokenLanguages:List<SpokenLanguageVO>?,
    @SerializedName("genres")
    @ColumnInfo(name = "genres")
    val genres:List<GenreVO>?,
    @SerializedName("belongs_to_collection")
    @ColumnInfo(name = "belongs_to_collection")
    val belongsToCollection: CollectionVO?,

    @ColumnInfo(name = "type")
    var type:String?
){
    fun getGenresAsCommaSeparatedString():String{
        return genres?.map{ it.name }?.joinToString ("," )?:""
    }
    fun getProductionCountryAsCommaSeparatedString():String{
        return productionCountries?.map{ it.name }?.joinToString ("," )?:""
    }
    fun getRatingBasedOnFiveStars():Float{
        return voteAverage?.div(2)?.toFloat()?:0.0f
    }
}
const val NOW_PLAYING = "NOW_PLAYING"
const val TOP_RATED = "TOP_RATED"
const val POPULAR = "POPULAR"
