package com.losman.nyfilm.view.filmlist

import android.util.Log
import com.losman.nyfilm.model.FilmMapper
import com.losman.nyfilm.retrofit.NYService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.DisposableHandle
import moxy.MvpPresenter
import javax.inject.Inject

class FilmListPresenter @Inject constructor(
    private var apiService: NYService,
) : MvpPresenter<FilmListView>() {
    override fun onFirstViewAttach() {
        getFilmList(0)
    }
    //функция для получения списка фильмов
    fun getFilmList(page: Int) {

        apiService.getFilmList(page * 20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                FilmMapper().fromResponse(it)
            }
            .subscribe({
                viewState.updateList(it, page)
            }, {
                viewState.showToast(it.message ?: "network problem")
            })
        val d : Disposable

    }
}