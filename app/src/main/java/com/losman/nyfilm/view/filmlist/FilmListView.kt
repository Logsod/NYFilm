package com.losman.nyfilm.view.filmlist

import com.losman.nyfilm.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface FilmListView : MvpView {
    @Skip
    fun showToast(message: String)

    //функция для обновления списка фильмов
    @AddToEndSingle
    fun updateList(filmList : List<Film>, page: Int)
}