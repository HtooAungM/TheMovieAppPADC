package com.example.movieapp.network

import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.utils.*
import com.example.movieapp.network.responses.GetActorResponse
import com.example.movieapp.network.responses.GetCreditByMovieResponse
import com.example.movieapp.network.responses.GetGenreResponse
import com.example.movieapp.network.responses.GetMovieListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_SHOWING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1
    ):Observable<GetMovieListResponse>

    @GET(API_GET_POPULAR)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1
    ):Observable<GetMovieListResponse>

    @GET(API_GET_TOP_RATED)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1
    ):Observable<GetMovieListResponse>

    @GET(API_GET_MOVIE_GENRE)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ):Observable<GetGenreResponse>
    @GET(API_GET_MOVIE_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_GENRE_ID) genreId:String?
    ):Observable<GetMovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apiKey: String? = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page: Int = 1
    ):Observable<GetActorResponse>

    @GET("$API_GET_MOVIE_DETAIL/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId:String?,
        @Query(PARAM_API_KEY) apiKey:String? = MOVIE_API_KEY
    ):Observable<MovieVO>
    @GET("$API_GET_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId:String?,
        @Query(PARAM_API_KEY) apiKey:String? = MOVIE_API_KEY
    ):Observable<GetCreditByMovieResponse>

    @GET(API_SEARCH_MOVIE)
    fun searchMovie(
        @Query(PARAM_API_KEY) apiKey:String? = MOVIE_API_KEY,
        @Query(PARAM_QUERY) query:String?
    ):Observable<GetMovieListResponse>
}