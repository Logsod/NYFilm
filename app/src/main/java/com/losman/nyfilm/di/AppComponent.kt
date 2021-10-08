package com.losman.nyfilm.di

import com.losman.nyfilm.view.filmlist.FilmListActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        MainModule::class,
        FilmListActivityModule::class
    ]
)
interface AppComponent {
    fun inject(activity: FilmListActivity)
}