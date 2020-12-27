package com.szareckii.popularlibraries.mvp.model.cache

import com.szareckii.popularlibraries.mvp.model.entity.Movie
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IMoviesCache {
    fun putMovies(movies: List<Movie>): Completable
    fun getMovies(): Single<List<Movie>>
}