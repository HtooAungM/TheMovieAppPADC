package com.example.movieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner

interface IBasePresenter {
    fun onUiReady(owner: LifecycleOwner)
}