package com.szareckii.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
        (
        foreignKeys = [ForeignKey(
                entity = RoomMovie::class,
                parentColumns = ["id"],
                childColumns = ["idMovie"],
                onDelete = ForeignKey.CASCADE
        )]
)
class RoomMovieActor(
        @PrimaryKey var id: String,
        var idMovie: String,
        var image: String,
        var name: String,
        var asCharacter: String
)
