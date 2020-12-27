package com.szareckii.popularlibraries.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.mvp.model.image.IImageLoader
import com.szareckii.popularlibraries.mvp.presenter.list.IMovieListPresenter
import com.szareckii.popularlibraries.mvp.view.listItems.MovieItemView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_movie.*
import javax.inject.Inject

class MoviesRvAdapter(val presenter: IMovieListPresenter): RecyclerView.Adapter<MoviesRvAdapter.ViewHolder>() {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        holder.containerView.setOnClickListener{ presenter.itemClickListener?.invoke(holder)}
        presenter.bindView(holder)
    }

    override fun getItemCount() =  presenter.getCount()

    inner class ViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), MovieItemView, LayoutContainer{

        override var pos = -1

        override fun setTitle(text: String) {
            tv_title.text = text
        }

        override fun loadImage(url: String) {
            imageLoader.loadInto(url, iv_image)
        }

        override fun setRank(text: String) {
            tv_rank.text = text
        }

        override fun setRating(text: String) {
            tv_rating.text = text
        }
    }
}