package com.szareckii.popularlibraries.mvp.model.cache.room

import com.szareckii.popularlibraries.mvp.model.cache.IMovieActorsCache
import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomMovieActor
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomActorsCache(val db: Database): IMovieActorsCache {

    override fun putActors(movie: Movie, actors: List<Actor>): Completable = Completable.fromAction {


        val roomMovie = movie.title?.let { db.movieDao.findByLogin(it) } ?: throw java.lang.RuntimeException("No such users is database")
        val roomActors = actors.map {
            RoomMovieActor(
                    it.id ?: "",
                    roomMovie.id,
                    it.image ?: "",
                    it.name ?: "",
                    it.asCharacter ?: ""
            )
        }
        db.actorDao.insert(roomActors)
    }.subscribeOn(Schedulers.io())

    override fun getActors(movie: Movie): Single<List<Actor>> = Single.fromCallable {
        val roomMovie = movie.title?.let { db.movieDao.findByLogin(it) } ?: throw RuntimeException("No such users is database")
        return@fromCallable  db.actorDao.findForMovie(roomMovie.id).map {
            Actor(it.id, it.image, it.name, it.asCharacter)
        }
    }.subscribeOn(Schedulers.io())
}
