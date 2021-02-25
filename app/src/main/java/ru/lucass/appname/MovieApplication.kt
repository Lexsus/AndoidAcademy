package ru.lucass.appname

import android.app.Application

class MovieApplication : Application()   {

    init {
        instance = this
    }

    companion object {
        private var instance: MovieApplication? = null

        fun getInstance() : MovieApplication {
            return instance!!
        }
    }
}