package com.example.movieapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.data.vos.MovieVO

class MainViewModel : ViewModel() {
    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var nowPlayingMoviesLiveData: LiveData<List<MovieVO>>? = null
    var popularMoviesLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMoviesLiveData: LiveData<List<MovieVO>>? = null
    val genresLiveData = MutableLiveData<List<GenreVO>>()
    val moviesByGenresLiveData = MutableLiveData<List<MovieVO>>()
    val actorLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitialData() {
        nowPlayingMoviesLiveData = mMovieModel.getNowPlayingMovies { mErrorLiveData.postValue(it) }
        popularMoviesLiveData = mMovieModel.getPopularMovies { mErrorLiveData.postValue(it) }
        topRatedMoviesLiveData = mMovieModel.getTopRatedMovies { mErrorLiveData.postValue(it) }

        mMovieModel.getGenres(
            onSuccess = {
                genresLiveData.postValue(it)
                getMoviesByGenre(0)
            }, onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
        mMovieModel.getActors(
            onSuccess = {
                actorLiveData.postValue(it)
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )

    }

    fun getMoviesByGenre(genrePosition: Int) {
//        Log.d("genres","${genresLiveData.value}")
        genresLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMoviesByGenre(it.toString(),
                onSuccess = {moviesByGenre->
                    moviesByGenresLiveData.postValue(moviesByGenre)
                },
                onFailure = {error->
                    mErrorLiveData.postValue(error)
                })
        }
    }
}