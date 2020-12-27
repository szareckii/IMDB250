package com.szareckii.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MovieView: MvpView {
    fun init()
    fun updateMovieActorList()
    fun setMovieName(text: String)
}
