package com.publicissapient.configinitializer

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ConfigInitializerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}