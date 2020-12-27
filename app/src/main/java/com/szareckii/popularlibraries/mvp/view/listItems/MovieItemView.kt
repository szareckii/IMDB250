package com.szareckii.popularlibraries.mvp.view.listItems

interface MovieItemView: IItemView {
    fun setTitle(text: String)
    fun setRank(text: String)
    fun setRating(text: String)
    fun loadImage(url: String)
}