package com.example.movieapp.mvp.presenters

import com.example.movieapp.delegates.BannerViewHolderDelegate
import com.example.movieapp.delegates.MovieViewHolderDelegate
import com.example.movieapp.delegates.ShowcaseViewHolderDelegate
import com.example.movieapp.mvp.views.MainView

interface MainPresenter:IBasePresenter,BannerViewHolderDelegate,ShowcaseViewHolderDelegate,MovieViewHolderDelegate {
    fun initView(view:MainView)
    fun onTapGenre(genrePosition: Int)
}