package com.szareckii.popularlibraries.mvp.model.cache

import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IMovieActorsCache {

    fun putActors(movie: Movie, actors: List<Actor>): Completable
    fun getActors(movie: Movie): Single<List<Actor>>

}

