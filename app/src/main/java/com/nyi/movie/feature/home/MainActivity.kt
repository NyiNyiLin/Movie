package com.nyi.movie.feature.home

import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nyi.appbase.core.mvp.MvpActivity
import com.nyi.appbase.helper.AsyncViewResource
import com.nyi.appbase.helper.showLongToast
import com.nyi.domainn.exception.GenericErrorMessageFactory
import com.nyi.domainn.model.Movie
import com.nyi.movie.R
import com.nyi.network.exception.NetworkExceptionMessageFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : MvpActivity<MainView, MainViewModel>(), MainView {

    override val viewModel: MainViewModel by contractedViewModels()

    override val layoutResId: Int
        get() = R.layout.activity_main

    private val movieListAdapter = MovieListRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        rvMovieList.adapter = movieListAdapter
        rvMovieList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.refreshMovieList()
    }


    override fun showError(t : Throwable) {
        showLongToast(genericErrorMessageFactory.getErrorMessage(t))
    }

    override fun subscribeToMovieList(acceptMovieList: LiveData<AsyncViewResource<List<Movie>>>) {
        acceptMovieList.observe(this, Observer {
            when (it) {
                is AsyncViewResource.Loading -> {

                }
                is AsyncViewResource.Success -> {
                    movieListAdapter.submitList(it.value)
                }
                is Error -> {

                }
            }
        })
    }




}
