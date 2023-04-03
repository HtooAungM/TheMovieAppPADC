package com.example.movieapp.mvp.presenters

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.models.MovieModel
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.mvp.views.MainView

class MainPresenterImpl:ViewModel(),MainPresenter {
    //View
    var mView:MainView? = null
    //Model
    private val mMovieModel:MovieModel = MovieModelImpl
    //States
    private var mGenres:List<GenreVO?> = listOf()
    override fun initView(view: MainView) {
        mView = view
    }

    override fun onTapGenre(genrePosition: Int) {
        mGenres.getOrNull(genrePosition)?.id?.let { genreId->
            mMovieModel.getMoviesByGenre(genreId.toString(),
            onSuccess = {
                mView?.showMoviesByGenres(it)
            },
            onFailure = {
                mView?.showError(it)
            })
        }
    }

    override fun onUiReady(owner: LifecycleOwner) {
        //NowPlaying
        mMovieModel.getNowPlayingMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showNowPlayingMovies(it)
        }
        //Popular Movies
        mMovieModel.getPopularMovies {
            mView?.showError(it)
        }?.observe(owner){
            mView?.showPopularMovies(it)
        }
        //TopRated Movies
        mMovieModel.getTopRatedMovies {
            mMovieModel.getTopRatedMovies {
                mView?.showError(it)
            }?.observe(owner){
                mView?.showTopRatedMovies(it)
            }
        }
        //Genres and getMovie for First Genre
        mMovieModel.getGenres(
            onSuccess = {
                mGenres = it
                mView?.showGenres(it)
                it.firstOrNull()?.id?.let {genreId->
                    onTapGenre(genreId)
                }
            },
            onFailure = {
                mView?.showError(it)
            }
        )
        //Actors
        mMovieModel.getActors(
            onSuccess = {
                mView?.showActors(it)
            },
            onFailure = {
                mView?.showError(it)
            }
        )
    }

    override fun onTapMovieFromBanner(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailScreen(movieId)
    }
}