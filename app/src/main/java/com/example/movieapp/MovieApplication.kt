package com.example.movieapp

import android.app.Application
import com.example.movieapp.data.models.MovieModelImpl

class MovieApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)
    }
}