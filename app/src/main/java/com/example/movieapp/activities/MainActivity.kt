package com.example.movieapp.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapters.BannerAdapter
import com.example.movieapp.adapters.ShowCaseAdapter
import com.example.movieapp.data.vos.ActorVO
import com.example.movieapp.data.vos.GenreVO
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.delegates.BannerViewHolderDelegate
import com.example.movieapp.delegates.MovieViewHolderDelegate
import com.example.movieapp.delegates.ShowcaseViewHolderDelegate
import com.google.android.material.tabs.TabLayout
import com.example.movieapp.mvvm.MainViewModel
import com.example.movieapp.viewpods.ActorListViewPod
import com.example.movieapp.viewpods.MovieListViewPod

class MainActivity : AppCompatActivity(),BannerViewHolderDelegate,ShowcaseViewHolderDelegate,MovieViewHolderDelegate {


    //ViewModel
    private lateinit var mViewModel: MainViewModel//MVVM

    private lateinit var binding: ActivityMainBinding
    lateinit var mShowCaseAdapter: ShowCaseAdapter
    lateinit var mBannerAdapter: BannerAdapter

    //viewPods
    lateinit var mBestPopularMovieListViewPod: MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    private lateinit var mActorList: ActorListViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()//MVVM
        setUpBannerViewPager()
        setUpShowCaseRecyclerView()
        setUpListeners()
        setUpViewPods()

        //Observe LiveData
        observeLiveData()//MVVM
    }

    private fun setUpViewModel() {
        mViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mViewModel.getInitialData()
    }

    private fun observeLiveData() {//MVVM
        mViewModel.nowPlayingMoviesLiveData?.observe(this,mBannerAdapter::setNewData)
        mViewModel.popularMoviesLiveData?.observe(this,mBestPopularMovieListViewPod::setData)
        mViewModel.topRatedMoviesLiveData?.observe(this,mShowCaseAdapter::setNewData)
        mViewModel.genresLiveData.observe(this,this::setUpGenreTabLayout)
        mViewModel.moviesByGenresLiveData.observe(this,mMovieByGenreViewPod::setData)
        mViewModel.actorLiveData.observe(this,mActorList::setData)
    }


    private fun setUpViewPods() {
        mBestPopularMovieListViewPod = binding.vpBestPopularMovieList.root
        mBestPopularMovieListViewPod.setUpMovieListViewPod(this)

        mMovieByGenreViewPod = binding.vpMovieByGenre.root
        mMovieByGenreViewPod.setUpMovieListViewPod(this)

        mActorList = binding.vpActorList.root
    }

    private fun setUpShowCaseRecyclerView() {
        mShowCaseAdapter = ShowCaseAdapter(this)
        binding.rvShowCases.adapter = mShowCaseAdapter
        binding.rvShowCases.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun setUpListeners() {

        //Genre Tab Layout
        binding.tlGenre.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                mGenres?.get(tab?.position ?: 0)?.id?.let {
//                    getMoviesByGenre(it)
//                }
                mViewModel.getMoviesByGenre(tab?.position ?: 0)//MVVM
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })

        binding.ivSearch.setOnClickListener {
            startActivity(MovieSearchActivity.newIntent(this))
        }
    }

    private fun setUpGenreTabLayout(genreList: List<GenreVO>) {
        genreList.forEach {
            binding.tlGenre.newTab().apply {
                text = it.name
                binding.tlGenre.addTab(this)
            }
        }
    }


    private fun setUpBannerViewPager() {
        mBannerAdapter = BannerAdapter(this)
        binding.viewPagerBanner.adapter = mBannerAdapter
        binding.dotsIndicatorBanner.attachTo(binding.viewPagerBanner)
    }


    //MVVM
    override fun onTapMovieFromBanner(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this,movieId))
    }

    override fun onTapMovie(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this,movieId))
    }

    override fun onTapMovieFromShowcase(movieId: Int) {
        startActivity(MovieDetailsActivity.newIntent(this,movieId))
    }
}