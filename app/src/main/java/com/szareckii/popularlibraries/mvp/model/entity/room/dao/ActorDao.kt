package com.szareckii.popularlibraries.mvp.model.entity.room.dao

import androidx.room.*
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomMovieActor

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actor: RoomMovieActor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg actors: RoomMovieActor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actors: List<RoomMovieActor>)

    @Update
    fun update(actor: RoomMovieActor)

    @Update
    fun update(vararg actors: RoomMovieActor)

    @Update
    fun update(actors: List<RoomMovieActor>)

    @Delete
    fun delete(actor: RoomMovieActor)

    @Delete
    fun delete(vararg actors: RoomMovieActor)

    @Delete
    fun delete(actors: List<RoomMovieActor>)

    @Query("SELECT * FROM RoomMovieActor")
    fun getAll(): List<RoomMovieActor>

    @Query("SELECT * FROM RoomMovieActor WHERE id = :id")
    fun findForMovie(id: String): List<RoomMovieActor>

}