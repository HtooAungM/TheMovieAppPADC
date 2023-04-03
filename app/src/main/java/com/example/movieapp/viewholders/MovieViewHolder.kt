package com.example.movieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.databinding.ViewHolderMovieBinding
import com.example.movieapp.delegates.MovieViewHolderDelegate
import com.example.movieapp.utils.IMAGE_BASE_URL

class MovieViewHolder(itemView: View, private val mDelegate: MovieViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var binding:ViewHolderMovieBinding
    private var mMovieVO: MovieVO? = null

    init {
        binding = ViewHolderMovieBinding.bind(itemView)
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
//                mDelegate.onTapMovieFromBanner(movie.id)
                movie.id?.let { movieId -> mDelegate.onTapMovie(movieId) }
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.posterPath}")
            .into(binding.ivMovieImage)
        binding.tvMovieName.text = movie.title
        binding.tvMovieRating.text = movie.voteAverage?.toString()
        binding.rbMovieRating.rating = movie.getRatingBasedOnFiveStars()
    }
}