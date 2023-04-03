package com.example.movieapp.network.dataagents

import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.utils.API_GET_NOW_SHOWING
import com.example.movieapp.utils.BASE_URL
import com.example.movieapp.utils.MOVIE_API_KEY
import com.example.movieapp.network.responses.GetMovieListResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl: MovieDataAgent {
    override fun getNowPlayingMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    ) {
        GetNowPlayingMovieTask().execute()
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

    class GetNowPlayingMovieTask():AsyncTask<Void,Void, GetMovieListResponse?>(){ //Void1 -> input Void2->progress
        override fun doInBackground(vararg params: Void?): GetMovieListResponse? {
            val url:URL
            var reader:BufferedReader? = null
            val stringBuilder:StringBuilder

            try{
                url = URL("""$BASE_URL$API_GET_NOW_SHOWING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")

                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.readTimeout = 15*1000
                connection.doInput = true
                connection.doOutput = false
                connection.connect()

                reader = BufferedReader(
                    InputStreamReader(connection.inputStream)
                )

                stringBuilder = StringBuilder()
                for(line in reader.readLines()){
                    stringBuilder.append(line + "\n")
                }

                val responseString = stringBuilder.toString()
                Log.d("Now Playing Movies",responseString)

                val getMovieListResponse = Gson().fromJson(responseString, GetMovieListResponse::class.java)
                return getMovieListResponse
            }catch (e:Exception){
                e.printStackTrace()
                Log.e("NewsError",e.message?:"")

            }finally {
                if(reader!=null){
                    try {
                        reader.close()
                    }catch (ioe:IOException){
                        ioe.printStackTrace()
                    }
                }
            }
            return null
        }

        override fun onPostExecute(result: GetMovieListResponse?) {//Main thread
            super.onPostExecute(result)
        }
    }
}