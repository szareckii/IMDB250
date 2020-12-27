package com.szareckii.popularlibraries.mvp.model.entity.room.dao

import androidx.room.*
import com.szareckii.popularlibraries.mvp.model.entity.room.RoomMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: RoomMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomMovie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: List<RoomMovie>)

    @Update
    fun update(user: RoomMovie)

    @Update
    fun update(vararg movies: RoomMovie)

    @Update
    fun update(movies: List<RoomMovie>)

    @Delete
    fun delete(movie: RoomMovie)

    @Delete
    fun delete(vararg movies: RoomMovie)

    @Delete
    fun delete(movies: List<RoomMovie>)

    @Query("SELECT * FROM RoomMovie")
    fun getAll(): List<RoomMovie>

    @Query("SELECT * FROM RoomMovie WHERE title = :title LIMIT 1")
    fun findByLogin(title: String): RoomMovie?

}
