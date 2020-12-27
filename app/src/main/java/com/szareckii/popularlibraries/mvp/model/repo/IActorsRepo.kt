package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import io.reactivex.rxjava3.core.Single

interface IActorsRepo {

    fun getActorsList(movie: Movie): Single<List<Actor>>
}

