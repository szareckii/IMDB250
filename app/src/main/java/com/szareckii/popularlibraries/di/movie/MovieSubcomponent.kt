package com.szareckii.popularlibraries.di.movie

import com.szareckii.popularlibraries.di.MovieScope
import com.szareckii.popularlibraries.di.actor.ActorSubcomponent
import com.szareckii.popularlibraries.di.movie.module.MovieModule
import com.szareckii.popularlibraries.mvp.presenter.MoviesPresenter
import com.szareckii.popularlibraries.ui.adapter.MoviesRvAdapter
import dagger.Subcomponent

@MovieScope
@Subcomponent(
        modules = [
            MovieModule::class
        ]
)
interface MovieSubcomponent {
    fun actorSubcomponent(): ActorSubcomponent

    fun inject(moviesPresenter: MoviesPresenter)
    fun inject(moviesRvAdapter: MoviesRvAdapter)
}