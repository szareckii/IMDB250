package com.szareckii.popularlibraries.mvp.model.repo

import com.szareckii.popularlibraries.mvp.model.entity.Movie
import io.reactivex.rxjava3.core.Single

interface IMoviesRepo {

    fun getMoviesList(): Single<List<Movie>>
}