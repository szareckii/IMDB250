package com.szareckii.popularlibraries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import com.szareckii.popularlibraries.databinding.FragmentActorBinding
import com.szareckii.popularlibraries.mvp.model.entity.Actor
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.mvp.presenter.ActorPresenter
import com.szareckii.popularlibraries.mvp.view.ActorView
import com.szareckii.popularlibraries.ui.App
import com.szareckii.popularlibraries.ui.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ActorFragment : MvpAppCompatFragment(), ActorView, BackButtonListener {

    companion object {
        private const val ACTOR_ARG = "actor"
        private const val MOVIE_ARG = "movie"
        fun newInstance(user: Movie, actor: Actor) = ActorFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MOVIE_ARG, user)
                putParcelable(ACTOR_ARG, actor)
            }
        }
    }

    val presenter: ActorPresenter by moxyPresenter {

        val movie = arguments?.get(MOVIE_ARG) as Movie
        val actor = arguments?.getParcelable<Actor>(ACTOR_ARG) as Actor

        ActorPresenter(movie, actor).apply {
            App.instance.actorSubcomponent?.inject(this)
        }
    }

    private var _binding: FragmentActorBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): LinearLayout? {
        _binding = FragmentActorBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun setMovieTitle(text: String) {
        _binding?.titleMovie?.text = text
    }

    override fun setActorName(text: String) {
        _binding?.nameActor?.text = text
    }

    override fun setAsCharacter(text: String) {
        _binding?.asCharacter?.text = text
    }

    override fun backPressed() = presenter.backClick()
}