package com.example.rateflix

import android.app.Application
import android.content.Context

class RateflixApplication : Application() {

    object Globals {
        var appContext: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        Globals.appContext = applicationContext
    }
}