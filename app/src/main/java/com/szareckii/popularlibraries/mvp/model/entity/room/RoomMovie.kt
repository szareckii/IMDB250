package com.szareckii.popularlibraries.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomMovie(
    @PrimaryKey var id: String,
    var rank: String,
    var title: String,
    var image: String,
    var year: String,
    var imDbRating: String
)