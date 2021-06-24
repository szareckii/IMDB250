package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.databinding.FragmentMovieBinding
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.presenter.MoviePresenter
import com.szareckii.popularlibraries.mvp.view.MovieView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.ActorsRvAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MovieFragment : MvpAppCompatFragment(), MovieView, BackButtonListener {

    companion object {
        private const val MOVIE_ARG = "movie"
        fun newInstance(movie: Movie) = MovieFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE_ARG, movie)
            }
//             arguments = bundleOf(MOVIE_ARG to movie)
        }
    }

    private var adapter: ActorsRvAdapter? = null

    val presenter: MoviePresenter by moxyPresenter {
        App.instance.initActorSubcomponent()

        val user = arguments?.getParcelable<Movie>(MOVIE_ARG) as Movie
        MoviePresenter(user).apply {
            App.instance.actorSubcomponent?.inject(this)
        }
    }

    private var _binding: FragmentMovieBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): LinearLayout? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        _binding?.rvActor?.layoutManager = LinearLayoutManager(requireContext())
        adapter = ActorsRvAdapter(presenter.actorListPresenter).apply {
            App.instance.actorSubcomponent?.inject(this)
        }

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)?.let {
            dividerItemDecoration.setDrawable(
                it
            )
        }
        _binding?.rvActor?.addItemDecoration(dividerItemDecoration)

        _binding?.rvActor?.adapter = adapter
    }

    override fun setMovieName(text: String) {
        _binding?.titleMovie?.text = text
    }

    override fun updateMovieActorList() {
        adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed() = presenter.backClick()

}