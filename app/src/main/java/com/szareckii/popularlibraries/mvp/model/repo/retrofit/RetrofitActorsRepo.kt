package com.szareckii.popularlibraries.mvp.model.repo.retrofit

import com.szareckii.popularlibraries.mvp.model.api.IDataSource
import com.szareckii.popularlibraries.mvp.model.cache.IMovieActorsCache
import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.mvp.model.repo.IActorsRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class  RetrofitActorsRepo(private val api: IDataSource, private val networkStatus: INetworkStatus, private val cacheActors: IMovieActorsCache):
    IActorsRepo {

    override fun getActorsList(movie: Movie): Single<List<Actor>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline)
            {
                val url = "https://imdb-api.com/ru/API/Title/k_r19m1l92/" + movie.id + "/FullActor"
                println(url)
                    url.let { apiUrl ->
                        api.getMovieInfo(apiUrl).flatMap { movieActorsList ->
                            movieActorsList.actorList?.let { actors ->
                                cacheActors.putActors(movie, actors).andThen(Single.just(actors))
                            }
                        }
                } ?: Single.error<List<Actor>>(RuntimeException("User has no repos url")).subscribeOn(
                    Schedulers.io())
            }
            else {
                val str = "https://imdb-api.com/ru/API/Title/k_r19m1l92/" + movie.id + "/FullActor"
                api.getMovieInfo(str).flatMap { movieActorsList ->
                    movieActorsList.actorList?.let {
                        cacheActors.getActors(movie)
                      }
                  }
              }
    }.subscribeOn(Schedulers.io())
}

