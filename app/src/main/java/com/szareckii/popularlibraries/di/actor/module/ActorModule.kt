package com.szareckii.popularlibraries.di.actor.module

import com.szareckii.popularlibraries.di.ActorScope
import com.szareckii.popularlibraries.di.actor.IActorScopeContainer
import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IMovieActorsCache
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomActorsCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IActorsRepo
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitActorsRepo
import com.szareckii.popularlibraries.ui.App
import dagger.Module
import dagger.Provides

@Module
class ActorModule {

    @ActorScope
    @Provides
    fun actorsCache(database: Database): IMovieActorsCache = RoomActorsCache(database)

    @ActorScope
    @Provides
    fun actorsRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IMovieActorsCache) : IActorsRepo =
            RetrofitActorsRepo(api, networkStatus, cache)

    @ActorScope
    @Provides
    fun scopeContainer(app: App): IActorScopeContainer = app

}