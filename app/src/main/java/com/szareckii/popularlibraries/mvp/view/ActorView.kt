package com.szareckii.popularlibraries.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ActorView: MvpView {
    fun setMovieTitle(text: String)
    fun setActorName(text: String)
    fun setAsCharacter(text: String)
}