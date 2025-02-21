package com.example.movieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.example.movieapp.mvvm.MovieDetailViewModel
import com.example.movieapp.utils.IMAGE_BASE_URL
import com.example.movieapp.viewpods.ActorListViewPod


class MovieDetailsActivity : AppCompatActivity() {
    //viewPods
    lateinit var binding: ActivityMovieDetailsBinding
    lateinit var actorsViewPod: ActorListViewPod
    lateinit var creatorsViewPod: ActorListViewPod

    //ViewModel
    private lateinit var mViewModel: MovieDetailViewModel//MVVM

    companion object {
        private const val MOVIE_EXTRA_ID = "MOVIE_EXTRA_ID"
        fun newIntent(context: Context, movieId: Int): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_EXTRA_ID, movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent?.getIntExtra(MOVIE_EXTRA_ID, 0)
        movieId?.let {
            setUpViewModel(it)//MVVM
    }
        setUpViewPods()
        setUpListeners()
        observeLiveData()//MVVM
    }

    private fun observeLiveData(){
        mViewModel.movieDetailLiveData?.observe(this){
            it?.let { movie->bindData(movie) }
        }
        mViewModel.castLiveData.observe(this,actorsViewPod::setData)
        mViewModel.crewLiveData.observe(this,creatorsViewPod::setData)
    }
    private fun setUpViewModel(movieId: Int){//MVVM
        mViewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
        mViewModel.getInitData(movieId)
    }

    private fun bindData(movie: MovieVO) {
        Glide.with(this)
            .load("${IMAGE_BASE_URL}${movie.backdropPath}")
            .into(binding.ivMovieDetail)
        binding.tvMovieName.text = movie.title ?: ""
        binding.tvReleaseYear.text = movie.releaseDate?.substring(0, 4)
        binding.tvRating.text = movie.voteAverage?.toString() ?: ""
        movie.voteCount?.let {
            binding.tvNumberOfVotes.text = "$it VOTES"
        }
        binding.rbMovieDetailRating.rating = movie.getRatingBasedOnFiveStars()
        bindGenres(movie, movie.genres ?: listOf())
        binding.tvStoryLineOverView.text = movie.overview ?: ""
        binding.tvOriginalTitleText.text = movie.title ?: ""
        binding.tvTypeTitleText.text = movie.getGenresAsCommaSeparatedString()
        binding.tvProductionTitleText.text = movie.getProductionCountryAsCommaSeparatedString()
        binding.tvPremiereTitleText.text = movie.releaseDate ?: ""
        binding.tvDescriptionTitleText.text = movie.overview ?: ""
    }

    private fun bindGenres(movie: MovieVO, genres: List<GenreVO>) {
        movie.genres?.count()?.let {
            binding.tvGenreFirst.text = genres.firstOrNull()?.name ?: ""
            binding.tvGenreSecond.text = genres.getOrNull(1)?.name ?: ""
            binding.tvGenreThird.text = genres.getOrNull(2)?.name ?: ""
            if (it < 3) {
                binding.tvGenreThird.visibility = View.GONE
            } else if (it < 2) {
                binding.tvGenreSecond.visibility = View.GONE
            }
        }
    }

    private fun setUpListeners() {
        binding.ivBtnBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun setUpViewPods() {
        actorsViewPod = binding.vpActors.root
        actorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_actors),
            moreTitleText = ""
        )
        creatorsViewPod = binding.vpCreators.root
        creatorsViewPod.setUpActorViewPod(
            backgroundColorReference = R.color.colorPrimary,
            titleText = getString(R.string.lbl_creators),
            moreTitleText = getString(R.string.lbl_more_creators)
        )
    }


}