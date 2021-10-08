package com.losman.nyfilm.view.filmlist

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.losman.nyfilm.App
import com.losman.nyfilm.R
import com.losman.nyfilm.databinding.ActivityFilmlistBinding
import com.losman.nyfilm.model.Film
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class FilmListActivity : MvpAppCompatActivity(), FilmListView, PagingListener {
    @Inject
    lateinit var presenterProvider: Provider<FilmListPresenter>
    lateinit var binding: ActivityFilmlistBinding
    private val presenter: FilmListPresenter by moxyPresenter { presenterProvider.get() }
    lateinit var adapter: FilmListAdapter
    var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filmlist)
        adapter = FilmListAdapter(this)
        binding.filmList.adapter = adapter
        binding.filmList.layoutManager = LinearLayoutManager(this)
        //presenter.getFilmList(0)
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun updateList(filmList: List<Film>, page: Int) {
        hideLoading()
        currentPage = page
        adapter.update(filmList)
        adapter.setPage(page)
    }

    override fun onPrevClick() {
        showLoading()
        binding.filmList.smoothScrollToPosition(0)
        presenter.getFilmList(currentPage - 1)
    }

    override fun onNextClick() {
        showLoading()
        binding.filmList.smoothScrollToPosition(0)
        presenter.getFilmList(currentPage + 1)
    }

    private fun hideLoading() {
        binding.filmList.visibility = View.VISIBLE
        binding.loading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.filmList.visibility = View.GONE
        binding.loading.visibility = View.VISIBLE
    }
}