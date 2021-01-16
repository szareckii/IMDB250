package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.szareckii.popularlibraries.R
import com.szareckii.popularlibraries.databinding.FragmentMoviesBinding
import com.szareckii.popularlibraries.mvp.presenter.MoviesPresenter
import com.szareckii.popularlibraries.mvp.view.MoviesView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import com.szareckii.popularlibraries.ui.adapter.MoviesRvAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MoviesFragment : MvpAppCompatFragment(), MoviesView, BackButtonListener {

    companion object {
        fun newInstance() = MoviesFragment()
    }

    val presenter: MoviesPresenter by moxyPresenter {
        App.instance.initMovieSubcomponent()
        MoviesPresenter().apply {
            App.instance.movieSubcomponent?.inject(this)
        }
    }

    private var adapter: MoviesRvAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): RelativeLayout? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun init() {
        _binding?.rvUsers?.layoutManager = LinearLayoutManager(requireContext())
        adapter = MoviesRvAdapter(presenter.movieListPresenter).apply {
            App.instance.movieSubcomponent?.inject(this)
        }
        _binding?.rvUsers?.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)?.let {
            dividerItemDecoration.setDrawable(
                    it
            )
        }
        _binding?.rvUsers?.addItemDecoration(dividerItemDecoration)
    }

    private var _binding: FragmentMoviesBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun updateMoviesList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backClick()

}