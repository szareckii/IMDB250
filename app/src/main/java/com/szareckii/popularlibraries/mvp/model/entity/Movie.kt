package com.szareckii.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        @Expose val id: String? = null,
        @Expose val rank: String? = null,
        @Expose val title: String? = null,
        @Expose val image: String? = null,
        @Expose val year: String? = null,
        @Expose val imDbRating: String? = null
): Parcelable