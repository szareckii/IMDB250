package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.view.ActorView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class ActorPresenter(val movie: Movie, private val actor: Actor): MvpPresenter<ActorView>() {

    @Inject lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setMovieTitle(movie.title ?: "")
        viewState.setActorName(actor.name ?: "")
        viewState.setAsCharacter(actor.asCharacter ?: "")
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}