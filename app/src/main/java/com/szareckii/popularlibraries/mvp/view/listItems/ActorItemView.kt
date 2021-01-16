package com.szareckii.popularlibraries.mvp.view.listItems

interface ActorItemView: IItemView {
    fun setName(text: String)
    fun loadImage(url: String)
}