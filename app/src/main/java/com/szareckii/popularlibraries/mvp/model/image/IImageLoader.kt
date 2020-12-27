package com.szareckii.popularlibraries.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}