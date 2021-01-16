package com.szareckii.popularlibraries.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Actor(
        @Expose val id: String? = null,
        @Expose val image: String? = null,
        @Expose val name: String? = null,
        @Expose val asCharacter: String? = null
): Parcelable