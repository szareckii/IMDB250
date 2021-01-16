package com.szareckii.popularlibraries.di.modules

import android.widget.ImageView
import com.szareckii.popularlibraries.mvp.model.cache.image.IImageCache
import com.szareckii.popularlibraries.mvp.model.cache.image.room.RoomImageCache
import com.szareckii.popularlibraries.mvp.model.entity.room.db.Database
import com.szareckii.popularlibraries.mvp.model.image.IImageLoader
import com.szareckii.popularlibraries.mvp.model.network.INetworkStatus
import com.szareckii.popularlibraries.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageCache(database: Database, @Named("cacheDir") cacheDir: File): IImageCache = RoomImageCache(database, cacheDir)

    @Singleton
    @Provides
    fun imageLoader(imageCache: IImageCache, networkStatus: INetworkStatus): IImageLoader<ImageView> =
            GlideImageLoader(imageCache, networkStatus)
}