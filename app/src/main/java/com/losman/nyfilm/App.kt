package com.losman.nyfilm

import android.app.Application
import com.losman.nyfilm.di.AppComponent
import com.losman.nyfilm.di.DaggerAppComponent
import com.losman.nyfilm.di.MainModule

class App : Application() {
    lateinit var appComponent : AppComponent
    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .mainModule(MainModule(this))
            .build()

        super.onCreate()
    }
}