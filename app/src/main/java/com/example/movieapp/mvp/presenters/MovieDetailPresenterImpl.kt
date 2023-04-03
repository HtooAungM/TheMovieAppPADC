package com.example.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.mvp.views.MainView
import com.example.movieapp.mvp.views.MovieDetailView

class MovieDetailPresenterImpl : ViewModel(), MovieDetailPresenter {
    //View
    var mView: MovieDetailView? = null

    //Model
    private val mMovieModel: MovieModel = MovieModelImpl
    override fun onInitView(view: MovieDetailView) {
        mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int) {
        //Movie Detail
        mMovieModel.getMovieDetail(movieId = movieId.toString()) {
            mView?.showError(it)
        }?.observe(owner) {
            mView?.showMovieDetails(it)
        }

        //Credits
        mMovieModel.getCreditsByMovie(movieId = movieId.toString(), onSuccess = {
            mView?.showCreditsByMovie(cast = it.first, crew = it.second)
        }, onFailure = {
            mView?.showError(it)
        })
    }

    override fun onTapBack() {
        mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}