package com.szareckii.popularlibraries.di

import com.szareckii.popularlibraries.di.modules.*
import com.szareckii.popularlibraries.di.movie.MovieSubcomponent
import com.szareckii.popularlibraries.mvp.presenter.MainPresenter
import com.szareckii.popularlibraries.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ApiModule::class,
    NavigationModule::class,
    ImageModule::class
])
interface AppComponent {
    fun movieSubcomponent(): MovieSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}
