package com.example.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.example.movieapp.mvp.views.MainView
import com.example.movieapp.mvp.views.MovieDetailView

interface MovieDetailPresenter:IBasePresenter {
    fun onInitView(view:MovieDetailView)
    fun onUiReadyInMovieDetail(owner:LifecycleOwner,movieId:Int)
    fun onTapBack()
}