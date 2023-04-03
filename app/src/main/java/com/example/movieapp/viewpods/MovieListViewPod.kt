package com.example.movieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.databinding.ViewPodMovieListBinding
import com.example.movieapp.delegates.MovieViewHolderDelegate

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    lateinit var binding: ViewPodMovieListBinding
    lateinit var mMovieAdapter: MovieAdapter
    lateinit var mDelegate: MovieViewHolderDelegate
    override fun onFinishInflate() {
//        setMovieRecyclerView()
        super.onFinishInflate()
        binding = ViewPodMovieListBinding.bind(this)
    }
    fun setData(movieList:List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }
    fun setUpMovieListViewPod(delegate: MovieViewHolderDelegate){
        setUpDelegate(delegate)
        setMovieRecyclerView()
    }
    private fun setUpDelegate(delegate : MovieViewHolderDelegate){
        this.mDelegate=delegate
    }
    private fun setMovieRecyclerView(){
        mMovieAdapter = MovieAdapter(mDelegate)
        binding.rvMovieList.adapter = mMovieAdapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

}


