package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.di.actor.IActorScopeContainer
import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.repo.IActorsRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IActorListPresenter
import com.szareckii.popularlibraries.mvp.view.MovieView
import com.szareckii.popularlibraries.mvp.view.listItems.ActorItemView
import com.szareckii.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MoviePresenter(val movie: Movie): MvpPresenter<MovieView>() {

    @Inject lateinit var actorsRepo: IActorsRepo
    @Inject lateinit var router: Router
    @Inject lateinit var scope: IActorScopeContainer
    @Inject lateinit var uiScheduler: Scheduler


    class ActorListPresenter: IActorListPresenter {
        override var itemClickListener: ((ActorItemView) -> Unit)? = null

        val actors = mutableListOf<Actor>()

        override fun bindView(view: ActorItemView) {
            val actor = actors[view.pos]
            actor.name?.let { view.setName(it) }
            actor.image?.let { view.loadImage(it) }
        }

        override fun getCount() = actors.size
    }

    val actorListPresenter = ActorListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        movie.title?.let { viewState.setMovieName(it) }

        actorListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.ActorScreen(movie, actorListPresenter.actors[itemView.pos]))
        }
    }

    private fun loadData() {
        actorsRepo.getActorsList(movie)
            .observeOn(uiScheduler)
            .subscribe({ actors ->
                actorListPresenter.actors.clear()
                actorListPresenter.actors.addAll(actors)
                viewState.updateMovieActorList()
            }, {
                println("Error: ${it.message}")

            }).addTo(compositeDisposable)
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.releaseActorScope()
        compositeDisposable.dispose()
    }
}