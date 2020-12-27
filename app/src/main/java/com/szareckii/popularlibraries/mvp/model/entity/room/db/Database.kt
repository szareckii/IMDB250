package com.szareckii.popularlibraries.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.szareckii.popularlibraries.mvp.model.entity.room.*
import com.szareckii.popularlibraries.mvp.model.entity.room.dao.ActorDao
import com.szareckii.popularlibraries.mvp.model.entity.room.dao.ImageDao
import com.szareckii.popularlibraries.mvp.model.entity.room.dao.MovieDao

@androidx.room.Database(entities = [RoomMovie::class, RoomCachedImage::class, RoomMovieActor::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val actorDao: ActorDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: Database? = null
    }
}