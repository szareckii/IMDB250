package com.szareckii.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieActorsList (
        @Expose val plot: String? = null,
        @Expose val actorList: List<Actor>? = null
): Parcelable