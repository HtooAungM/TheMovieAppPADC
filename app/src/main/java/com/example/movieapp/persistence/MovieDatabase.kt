package com.example.movieapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.vos.MovieVO
import com.example.movieapp.persistence.daos.MovieDao

@Database(entities = [MovieVO::class], version = 2, exportSchema = false)
abstract class MovieDatabase:RoomDatabase() {
    companion object{
        const val DB_NAME = "THE_MOVIE_DB"
        var dbInstance: MovieDatabase? = null
        fun getDBInstance(context: Context): MovieDatabase?{
            when(dbInstance){
                null->{
                    dbInstance = Room.databaseBuilder(context, MovieDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }
    abstract fun movieDao(): MovieDao
}