package com.szareckii.popularlibraries.mvp.model.repo.retrofit

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IMoviesCache
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IMoviesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitMoviesRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val cacheMovies: IMoviesCache):
        IMoviesRepo {

    override fun getMoviesList(): Single<List<Movie>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline)
        {
            api.getMoviesList().flatMap { moviesList ->
                moviesList.items?.let { movies ->
                    cacheMovies.putMovies(movies).andThen(Single.just(movies))
                }
            }
        }
        else {
            api.getMoviesList().flatMap { moviesList ->
                moviesList.items?.let {
                    cacheMovies.getMovies()
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}

