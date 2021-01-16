package com.szareckii.popularlibraries.di.actor

import com.szareckii.popularlibraries.di.ActorScope
import com.szareckii.popularlibraries.di.actor.module.ActorModule
import com.szareckii.popularlibraries.mvp.presenter.ActorPresenter
import com.szareckii.popularlibraries.mvp.presenter.MoviePresenter
import com.szareckii.popularlibraries.ui.adapter.ActorsRvAdapter
import dagger.Subcomponent

@ActorScope
@Subcomponent(
        modules = [
            ActorModule::class
        ]
)
interface ActorSubcomponent {
    fun inject(moviePresenter: MoviePresenter)
    fun inject(actorPresenter: ActorPresenter)
    fun inject(actorsRvAdapter: ActorsRvAdapter)
}