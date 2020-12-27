package com.szareckii.popularlibraries.mvp.presenter

import com.szareckii.popularlibraries.di.movie.IMovieScopeContainer
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.model.repo.IMoviesRepo
import com.szareckii.popularlibraries.mvp.presenter.list.IMovieListPresenter
import com.szareckii.popularlibraries.mvp.view.MoviesView
import com.szareckii.popularlibraries.mvp.view.listItems.MovieItemView
import com.szareckii.popularlibraries.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MoviesPresenter(): MvpPresenter<MoviesView>() {

    @Inject lateinit var moviesRepo: IMoviesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var scope: IMovieScopeContainer
    @Inject lateinit var uiScheduler: Scheduler

    class MovieListPresenter: IMovieListPresenter{
        override var itemClickListener: ((MovieItemView) -> Unit)? = null

        val movies = mutableListOf<Movie>()

        override fun bindView(view: MovieItemView) {
            val movie = movies[view.pos]
            movie.title?.let { view.setTitle(it) }
            movie.rank?.let { view.setRank(it) }
            movie.image?.let { view.loadImage(it) }
            movie.imDbRating?.let { view.setRating(it) }
        }

        override fun getCount() = movies.size
    }

    val movieListPresenter = MovieListPresenter()
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        movieListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.MovieScreen(movieListPresenter.movies[itemView.pos]))
        }
    }

    private fun loadData() {
         moviesRepo.getMoviesList()
            .observeOn(uiScheduler)
            .subscribe({ movies ->
                movieListPresenter.movies.clear()
                movieListPresenter.movies.addAll(movies)
                viewState.updateMoviesList()
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
        scope.releaseMovieScope()
        compositeDisposable.dispose()
    }
}