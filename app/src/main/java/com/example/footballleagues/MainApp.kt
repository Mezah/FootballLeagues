package com.example.footballleagues

import android.app.Application
import com.example.footballleagues.di.apiModule
import com.example.footballleagues.di.appModule
import com.example.footballleagues.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        val modulesList = listOf(appModule, apiModule, roomModule)
        startKoin {
            androidContext(this@MainApp)
            modules(modulesList)
        }
    }
}