package com.example.movieapp.mvp.views

import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.data.vos.MovieVO

interface MainView:BaseView {
    fun showNowPlayingMovies(nowPlayingMovies: List<MovieVO>)
    fun showPopularMovies(popularMovies: List<MovieVO>)
    fun showTopRatedMovies(topRatedMovies: List<MovieVO>)
    fun showGenres(genreList: List<GenreVO>)
    fun showMoviesByGenres(movieByGenres: List<MovieVO>)
    fun showActors(actors:List<ActorVO>)
    fun navigateToMovieDetailScreen(movieId:Int)
}