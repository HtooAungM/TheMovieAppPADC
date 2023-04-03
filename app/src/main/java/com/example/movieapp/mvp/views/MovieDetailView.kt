package com.example.movieapp.mvp.views

import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.MovieVO

interface MovieDetailView:BaseView {
    fun showMovieDetails(movie: MovieVO)
    fun showCreditsByMovie(cast: List<ActorVO>, crew: List<ActorVO>)
    fun navigateBack()
}