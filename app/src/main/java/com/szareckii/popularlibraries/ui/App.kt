package com.szareckii.popularlibraries.ui

import android.app.Application
import com.szareckii.popularlibraries.di.AppComponent
import com.szareckii.popularlibraries.di.DaggerAppComponent
import com.szareckii.popularlibraries.di.modules.AppModule
import com.szareckii.popularlibraries.di.movie.MovieSubcomponent
import com.szareckii.popularlibraries.di.actor.ActorSubcomponent
import com.szareckii.popularlibraries.di.actor.IActorScopeContainer
import com.szareckii.popularlibraries.di.movie.IMovieScopeContainer

class App: Application(), IMovieScopeContainer, IActorScopeContainer {
    companion object{
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var movieSubcomponent: MovieSubcomponent? = null
        private set

    var actorSubcomponent: ActorSubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initMovieSubcomponent() = appComponent.movieSubcomponent().also {
        movieSubcomponent = it
    }

    fun initActorSubcomponent() = movieSubcomponent?.actorSubcomponent().also {
          actorSubcomponent = it
    }

    override fun releaseMovieScope() {
        movieSubcomponent = null
    }

    override fun releaseActorScope() {
        actorSubcomponent = null
    }
}