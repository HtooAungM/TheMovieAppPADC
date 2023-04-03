package com.example.movieapp.network.dataagents

//import com.example.movieapp.data.vos.ActorVO
//import com.example.movieapp.data.vos.GenreVO
//import com.example.movieapp.data.vos.MovieVO
//import com.example.movieapp.network.TheMovieApi
//import com.example.movieapp.utils.BASE_URL
//import com.example.movieapp.network.responses.GetActorResponse
//import com.example.movieapp.network.responses.GetCreditByMovieResponse
//import com.example.movieapp.network.responses.GetGenreResponse
//import com.example.movieapp.network.responses.GetMovieListResponse
//import okhttp3.OkHttpClient
//import retrofit2.*
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object RetrofitDataAgentImpl : MovieDataAgent {
//    private var mMovieApi: TheMovieApi? = null
//
//    init {
//        val mOkHttpClient = OkHttpClient.Builder()
//            .connectTimeout(15, TimeUnit.SECONDS)
//            .readTimeout(15, TimeUnit.SECONDS)
//            .writeTimeout(15, TimeUnit.SECONDS)
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(mOkHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        mMovieApi = retrofit.create(TheMovieApi::class.java)
//    }
//
//    override fun getNowPlayingMovies(
//        onSuccess:(List<MovieVO>)->Unit,
//        onFailure:(String)->Unit
//    ) {
//        mMovieApi?.getNowPlayingMovies()?.enqueue(object : Callback<GetMovieListResponse>{
//            override fun onResponse(
//                call: Call<GetMovieListResponse>,
//                response: Response<GetMovieListResponse>
//            ) {
//                if(response.isSuccessful){
//                    val movieList = response.body()?.results?: listOf()  //response.body()->GetMovieListResponse
//                    onSuccess(movieList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GetMovieListResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//        })
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
//        mMovieApi?.getPopularMovies()?.enqueue(object : Callback<GetMovieListResponse>{
//            override fun onResponse(
//                call: Call<GetMovieListResponse>,
//                response: Response<GetMovieListResponse>
//            ) {
//                if(response.isSuccessful){
//                    val movieList = response.body()?.results?: listOf()
//                    onSuccess(movieList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GetMovieListResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//        })
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mMovieApi?.getTopRatedMovies()?.enqueue(object : Callback<GetMovieListResponse>{
//            override fun onResponse(
//                call: Call<GetMovieListResponse>,
//                response: Response<GetMovieListResponse>
//            ) {
//                if(response.isSuccessful){
//                    val movieList = response.body()?.results?: listOf()
//                    onSuccess(movieList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GetMovieListResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//        })
//    }
//
//    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
//        mMovieApi?.getGenres()?.enqueue(object :Callback<GetGenreResponse>{
//            override fun onResponse(call: Call<GetGenreResponse>, response: Response<GetGenreResponse>) {
//                if(response.isSuccessful){
//                    val movieList = response.body()?.genres?: listOf()
//                    onSuccess(movieList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GetGenreResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//        })
//    }
//
//    override fun getMoviesByGenre(
//        genreId: String,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mMovieApi?.getMoviesByGenre(genreId = genreId)?.enqueue(object :Callback<GetMovieListResponse>{
//            override fun onResponse(call: Call<GetMovieListResponse>, response: Response<GetMovieListResponse>) {
//                if(response.isSuccessful){
//                    val movieList = response.body()?.results?: listOf()
//                    onSuccess(movieList)
//                }else {
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GetMovieListResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//
//        })
//    }
//
//    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//        mMovieApi?.getActors()?.enqueue(object:Callback<GetActorResponse>{
//            override fun onResponse(
//                call: Call<GetActorResponse>,
//                response: Response<GetActorResponse>
//            ) {
//                onSuccess(response.body()?.results?: listOf())
//            }
//
//            override fun onFailure(call: Call<GetActorResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//
//            }
//
//        })
//    }
//
//    override fun getMovieDetail(
//        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mMovieApi?.getMovieDetail(movieId = movieId)?.enqueue(object :Callback<MovieVO>{
//            override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
//                if(response.isSuccessful){
//                    response.body()?.let {
//                        onSuccess(it)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<MovieVO>, t: Throwable) {
//                onFailure(t.message?:"")
//            }
//
//        })
//    }
//
//    override fun getCreditsByMovie(
//        movieId: String,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mMovieApi?.getCreditsByMovie(movieId=movieId)?.enqueue(object :Callback<GetCreditByMovieResponse>{
//            override fun onResponse(
//                call: Call<GetCreditByMovieResponse>,
//                response: Response<GetCreditByMovieResponse>
//            ) {
//                if(response.isSuccessful){
//                    response.body()?.let {
//                        onSuccess(Pair(it.cast?: listOf(),it.crew?: listOf()))
//                    }
////                    val res = Pair(response.body()?.cast?: listOf(),response.body()?.crew?: listOf())
////                    onSuccess(res)
//                }
//            }
//
//            override fun onFailure(call: Call<GetCreditByMovieResponse>, t: Throwable) {
//                onFailure(t.message?:"")
//
//            }
//
//        })
//    }
//}