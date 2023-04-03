package com.example.movieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.databinding.ViewItemBannerBinding
import com.example.movieapp.delegates.BannerViewHolderDelegate
import com.example.movieapp.utils.IMAGE_BASE_URL


class BannerViewHolder(itemView: View, private val mDelegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {
    private var binding: ViewItemBannerBinding
    private var mMovie: MovieVO? = null
    init {
        binding = ViewItemBannerBinding.bind(itemView)
        itemView.setOnClickListener {
            mMovie?.let{movie->
//                mDelegate.onTapMovieFromBanner(movie.id)
                movie.id?.let { movieId -> mDelegate.onTapMovieFromBanner(movieId) }
            }
        }
    }

    fun bindData(movie: MovieVO){
        mMovie = movie
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${movie.backdropPath}")
            .into(binding.ivBannerImage)
        binding.tvBannerMovieName.text = movie.title
    }
}