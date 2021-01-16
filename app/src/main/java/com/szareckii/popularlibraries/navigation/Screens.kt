package com.szareckii.popularlibraries.navigation

import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.ui.fragment.ActorFragment
import com.szareckii.popularlibraries.ui.fragment.MovieFragment
import com.szareckii.popularlibraries.ui.fragment.MoviesFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class MoviesScreen(): SupportAppScreen() {
        override fun getFragment() = MoviesFragment.newInstance()
    }

    class MovieScreen(private val movie: Movie): SupportAppScreen() {
        override fun getFragment() = MovieFragment.newInstance(movie)
    }

    class ActorScreen(private val movie: Movie, private val actor: Actor): SupportAppScreen() {
        override fun getFragment() = ActorFragment.newInstance(movie, actor)
    }
}