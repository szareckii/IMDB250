package com.szareckii.popularlibraries.di.movie.module

import com.szareckii.popularlibraries.di.MovieScope
import com.szareckii.popularlibraries.di.movie.IMovieScopeContainer
import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IMoviesCache
import com.szareckii.popularlibraries.mvp.model.cache.room.RoomMoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IMoviesRepo
import com.szareckii.popularlibraries.mvp.model.repo.retrofit.RetrofitMoviesRepo
import com.szareckii.popularlibraries.ui.App
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun moviesCache(database: Database): IMoviesCache = RoomMoviesCache(database)


    @MovieScope
    @Provides
    fun moviesRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IMoviesCache) : IMoviesRepo =
            RetrofitMoviesRepo(api, networkStatus, cache)

    @MovieScope
    @Provides
    fun scopeContainer(app: App): IMovieScopeContainer = app
}