package com.losman.nyfilm.di

import com.losman.nyfilm.retrofit.NYService
import com.losman.nyfilm.view.filmlist.FilmListPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FilmListActivityModule {
    @Provides
    @Singleton
    fun provideLoginPresenter(apiService: NYService): FilmListPresenter {
        return FilmListPresenter(apiService)
    }
}