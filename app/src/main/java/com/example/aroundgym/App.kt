package com.example.aroundgym

import android.app.Application
import com.example.aroundgym.di.networkModule
import com.example.aroundgym.di.repositoryModule
import com.example.aroundgym.di.sourceModule
import com.example.aroundgym.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}