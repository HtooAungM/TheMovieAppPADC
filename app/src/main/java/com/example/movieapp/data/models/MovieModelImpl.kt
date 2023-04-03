package com.example.movieapp.data.models



import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.example.movieapp.data.vos.*
import com.example.movieapp.network.responses.GetMovieListResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
object MovieModelImpl : BasedModel(),MovieModel {

//    private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl

    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        //Database
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(NOW_PLAYING) ?: listOf())

//        mMovieApi.getNowPlayingMovies(onSuccess = {
//            it.forEach { movie -> movie.type = NOW_PLAYING }
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//            onSuccess(it)
//        }, onFailure = onFailure)
        mMovieApi.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())//io thread = > one of background thread
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({//Next Event
                it.results?.forEach{movie->movie.type = NOW_PLAYING}
                mMovieDatabase?.movieDao()?.insertMovies(it.results?: listOf())
//                onSuccess(it.results?: listOf())
            },{//Error event
                onFailure(it.localizedMessage?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)

    }

    @SuppressLint("CheckResult")
    override fun getPopularMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        //Database
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(POPULAR) ?: listOf())

//        mMovieDataAgent.getPopularMovies(onSuccess = {
//            it.forEach { movie -> movie.type = POPULAR }
//            mMovieDatabase?.movieDao()?.insertMovies(it)
//            onSuccess(it)
//        }, onFailure = onFailure)
        mMovieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach{movie->movie.type = POPULAR}
                mMovieDatabase?.movieDao()?.insertMovies(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(POPULAR)
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {
        //Database
//        onSuccess(mMovieDatabase?.movieDao()?.getMovieByType(TOP_RATED) ?: listOf())
        mMovieApi.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.results?.forEach{movie->movie.type = TOP_RATED}
                mMovieDatabase?.movieDao()?.insertMovies(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieByType(TOP_RATED)
    }

    @SuppressLint("CheckResult")
    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getGenres()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.genres?: listOf())
            },{
                onFailure(it.localizedMessage?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
//        mMovieDataAgent.getMoviesByGenre(
//            genreId = genreId,
//            onSuccess = onSuccess,
//            onFailure = onFailure
//        )
        mMovieApi.getMoviesByGenre(genreId = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it.results?: listOf())
            },{
                onFailure(it.localizedMessage?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetail(movieId: String, onFailure: (String) -> Unit): LiveData<MovieVO>? {
//        val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
//        movieFromDatabase?.let {
//            onSuccess(it)
//        }
        mMovieApi.getMovieDetail(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    val movieFromDatabase = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                    it.type = movieFromDatabase?.type
                    mMovieDatabase?.movieDao()?.insertSingleMovie(it)
            },{
                onFailure(it.localizedMessage?: "")
            })
        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    @SuppressLint("CheckResult")
    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieApi.getCreditsByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(Pair(it.cast?: listOf(),it.crew?: listOf()))
            },{
                onFailure(it.localizedMessage?: "")
            })
    }

    @SuppressLint("CheckResult")
    override fun searchMovie(query: String): Observable<List<MovieVO>> {
        return mMovieApi
            .searchMovie(query = query)
            .map{it.results?: listOf()}
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }

}