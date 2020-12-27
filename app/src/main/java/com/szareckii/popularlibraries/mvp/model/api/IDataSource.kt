package com.szareckii.popularlibraries.mvp.model.api

import com.szareckii.popularlibraries.mvp.model.entity.MovieActorsList
import com.szareckii.popularlibraries.mvp.model.entity.MoviesList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface IDataSource {

    @GET("Top250Movies/k_r19m1l92")
    fun getMoviesList(): Single<MoviesList>

    @GET
    fun getMovieInfo(@Url url: String): Single<MovieActorsList>

}