package com.example.movieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.MovieVO

class MovieDetailViewModel:ViewModel() {
    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var movieDetailLiveData: LiveData<MovieVO>? = null
    val castLiveData = MutableLiveData<List<ActorVO>>()
    val crewLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitData(movieId:Int){
        movieDetailLiveData = mMovieModel.getMovieDetail(movieId.toString()){
            mErrorLiveData.postValue(it)
        }
        mMovieModel.getCreditsByMovie(movieId = movieId.toString(),
        onSuccess = {
            castLiveData.postValue(it.first ?: listOf())
            crewLiveData.postValue(it.second ?: listOf())
        },
        onFailure = {
            mErrorLiveData.postValue(it)
        })
    }
}