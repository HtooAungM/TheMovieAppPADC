package com.example.movieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.databinding.ViewHolderShowCaseBinding
import com.example.movieapp.delegates.ShowcaseViewHolderDelegate
import com.example.movieapp.utils.IMAGE_BASE_URL

class ShowCaseViewHolder(itemView: View, private val mDelegate: ShowcaseViewHolderDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var binding: ViewHolderShowCaseBinding
    private var mMovieVO: MovieVO? = null

    init {
        binding = ViewHolderShowCaseBinding.bind(itemView)
        itemView.setOnClickListener {
            mMovieVO?.let { movie ->
//                mDelegate.onTapMovieFromBanner(movie.id)
                movie.id?.let { movieId -> mDelegate.onTapMovieFromShowcase(movieId) }
            }
        }
    }

    fun bindData(movie: MovieVO) {
        mMovieVO = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.backdropPath}")
            .into(binding.ivShowCases)
        binding.tvShowCaseMovieName.text = movie.title
        binding.tvShowCaseMovieDate.text = movie.releaseDate

    }
}