package com.losman.nyfilm.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.losman.nyfilm.retrofit.NYService
import com.losman.nyfilm.retrofit.RetrofitApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class MainModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideNotiService(): NYService {
        return RetrofitApi().getServiceApi()
    }


}