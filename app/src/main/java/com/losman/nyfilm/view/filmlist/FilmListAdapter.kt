package com.losman.nyfilm.view.filmlist

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.losman.nyfilm.R
import com.losman.nyfilm.databinding.RecyclerViewFilmRowBinding
import com.losman.nyfilm.databinding.RecyclerViewPagingBinding
import com.losman.nyfilm.model.Film


class FilmListAdapter(private val listener: PagingListener) :
    RecyclerView.Adapter<FilmListAdapter.BiddableViewHolder>() {
    private var data: List<Any> = listOf()
    private var currentPage = 0

    @SuppressLint("NotifyDataSetChanged")
    fun update(newData: List<Film>) {
        data = newData
        notifyDataSetChanged()

    }

    fun setPage(page: Int) {
        currentPage = page
    }

    class BiddableViewHolder(
        private val binding: ViewDataBinding,
        private val listener: PagingListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemData: Any) {
            when (binding) {
                is RecyclerViewFilmRowBinding -> {
                    val film = itemData as Film
                    binding.title.text = film.title
                    binding.description.text = film.description
                    
                    val options = RequestOptions()
                    options.centerCrop()

                    if (film.image.isNotEmpty()) {
                        Glide.with(binding.root.context)
                            .load(film.image)
                            .apply(options)
                            .listener(object : RequestListener<Drawable> {
                                override fun onLoadFailed(
                                    e: GlideException?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    binding.imageLoading.visibility = View.GONE
                                    return false
                                }

                                override fun onResourceReady(
                                    resource: Drawable?,
                                    model: Any?,
                                    target: Target<Drawable>?,
                                    dataSource: DataSource?,
                                    isFirstResource: Boolean
                                ): Boolean {
                                    binding.imageLoading.visibility = View.GONE
                                    return false
                                }


                            })
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .timeout(4000)
                            .into(binding.image)
                    }
                }
                is RecyclerViewPagingBinding -> {
                    if(itemData != 0)
                        binding.pagingPrev.visibility = View.VISIBLE
                    else
                        binding.pagingPrev.visibility = View.GONE

                    binding.pagingPrev.setOnClickListener { listener.onPrevClick() }
                    binding.pagingNext.setOnClickListener { listener.onNextClick() }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BiddableViewHolder {

        if (viewType == 0) {
            val binding: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler_view_film_row,
                parent,
                false
            )
            return BiddableViewHolder(binding, listener)
        } else {
            val binding: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler_view_paging,
                parent,
                false
            )
            return BiddableViewHolder(binding, listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == data.size)
            return 1
        return 0
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    override fun onBindViewHolder(holder: BiddableViewHolder, position: Int) {
        if (position < data.size)
            holder.bind(data[position])
        else
            holder.bind(currentPage)
    }
}