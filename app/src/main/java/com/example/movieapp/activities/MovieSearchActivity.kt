package com.example.movieapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.adapters.MovieAdapter
import com.example.movieapp.data.models.MovieModelImpl
import com.example.movieapp.databinding.ActivityMovieSearchBinding
import com.example.movieapp.delegates.MovieViewHolderDelegate
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MovieSearchActivity:AppCompatActivity(),MovieViewHolderDelegate {
    companion object{
        fun newIntent(context: Context): Intent{
            return Intent(context, MovieSearchActivity::class.java)
        }
    }
    private var mMovieModel = MovieModelImpl
    lateinit var binding: ActivityMovieSearchBinding
    private lateinit var mMovieadapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpListeners()
    }

    private fun setUpListeners(){
        binding.tieSearch.textChanges()
            .debounce (500L ,TimeUnit.MILLISECONDS)
            .flatMap { mMovieModel.searchMovie(it.toString()) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mMovieadapter.setNewData(it)
            },{
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
            }
            )
    }
    private fun setUpRecyclerView(){
        mMovieadapter = MovieAdapter(this)
        binding.rvMovies.adapter = mMovieadapter
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onTapMovie(movieId: Int) {

    }
}