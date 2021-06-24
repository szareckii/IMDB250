package com.szareckii.popularlibraries

import android.os.Parcelable
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.annotations.Expose
import com.szareckii.popularlibraries.mvp.model.entity.Movie
import com.szareckii.popularlibraries.ui.fragment.MovieFragment
import kotlinx.android.parcel.Parcelize
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieFragmentEspressoTest {

    private lateinit var scenario: FragmentScenario<MovieFragment>

    @Before
    fun setup() {
        //Запускаем Fragment в корне Activity
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_testBundle() {
        //Можно передавать аргументы во Фрагмент, но это необязательно
        var i = 1
        i++
        val fragmentArgs = bundleOf("MOVIE_ARG" to getFakeResponse())
//        val fragmentArgs = bundleOf("TOTAL_COUNT_EXTRA" to 10)

        //Запускаем Fragment с аргументами
        val scenario = launchFragmentInContainer<MovieFragment>(fragmentArgs)
//        //Возможность менять стейт Фрагмента
        scenario.moveToState(Lifecycle.State.RESUMED)
//
        val assertion = matches(withText("Побег из Шоушенка"))
        onView(withId(R.id.title_movie)).check(assertion)
    }


    private fun getFakeResponse(): Movie {
        val list: MutableList<Movie> = mutableListOf()
        for (index in 1..100) {
            list.add(
                Movie(
                    "1",
                    "9.9",
                    "Побег из Щоушенка",
                    "image_url",
                    "2000",
                    "9.9"
                )
            )
        }
        return list[0]
    }


    //     private val testMovie = Movie(
    val list: MutableList<Movie> = mutableListOf()

    private val testMovie = mutableListOf(
        Movie(
             "1",
             "9.9",
             "Побег из Щоушенка",
             "image_url",
             "2000",
             "9.9"
             )
    )
}