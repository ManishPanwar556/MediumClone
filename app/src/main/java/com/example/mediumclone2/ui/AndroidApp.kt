package com.example.mediumclone2.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidApp:Application() {
    override fun onCreate() {
        super.onCreate()
    }
}