package com.example.movieapp.network.dataagents

import android.os.AsyncTask
import com.google.gson.Gson
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.utils.API_GET_NOW_SHOWING
import com.example.movieapp.utils.BASE_URL
import com.example.movieapp.utils.MOVIE_API_KEY
import com.example.movieapp.network.responses.GetMovieListResponse
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object OkhttpDataAgentImpl: MovieDataAgent {
    private val mClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15,TimeUnit.SECONDS)
        .readTimeout(15,TimeUnit.SECONDS)
        .writeTimeout(15,TimeUnit.SECONDS)
        .build()
    override fun getNowPlayingMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    ) {
        GetNowPlayingMovieOkHttpTask(mClient).execute()
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    class GetNowPlayingMovieOkHttpTask(private val mOkHttpClient: OkHttpClient):AsyncTask<Void,Void, GetMovieListResponse>(){

        override fun doInBackground(vararg params: Void?): GetMovieListResponse? {
            val request = okhttp3.Request.Builder()
                .url("""$BASE_URL$API_GET_NOW_SHOWING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
                .build()
            try{
                val response = mOkHttpClient.newCall(request).execute()
                if(response.isSuccessful){
                    response.body?.let{
                        val responseString = it.string()
                        val response = Gson().fromJson<GetMovieListResponse>(
                            responseString, GetMovieListResponse::class.java
                        )
                        return response
                    }
                }
            } catch (e:Exception){
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: GetMovieListResponse?) {
            super.onPostExecute(result)
        }
    }

}