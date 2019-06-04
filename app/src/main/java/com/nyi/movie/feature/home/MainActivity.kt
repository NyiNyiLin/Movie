package com.nyi.movie.feature.home

import android.os.Bundle
import android.util.Log

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aungkyawpaing.mmphonenumber.MyanmarPhoneNumberUtils
import com.google.android.material.snackbar.Snackbar
import com.nyi.appbase.core.mvp.MvpActivity
import com.nyi.appbase.core.recyclerview.RecyclerViewItemClickListener
import com.nyi.appbase.helper.AsyncViewResource
import com.nyi.appbase.helper.showLongToast
import com.nyi.domainn.exception.GenericErrorMessageFactory
import com.nyi.domainn.model.Movie
import com.nyi.domainn.model.MovieId
import com.nyi.movie.R
import com.nyi.movie.sticky.MovieData
import com.nyi.movie.sticky.MovieHeaderData
import com.nyi.movie.sticky.StickyRecycleAdapter
import com.nyi.network.exception.NetworkExceptionMessageFactory

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class MainActivity : MvpActivity<MainView, MainViewModel>(), MainView, RecyclerViewItemClickListener{

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

        //rvMovieList.adapter = movieListAdapter
        val arrayList = ArrayList<MovieData>()
        arrayList.add(MovieData(movieId = 1, name = "asd1", description = "asd", backdrop = "as"))
        arrayList.add(MovieData(movieId = 1, name = "asd2", description = "asd", backdrop = "as"))
        arrayList.add(MovieData(movieId = 1, name = "asd3", description = "asd", backdrop = "as"))
        arrayList.add(MovieData(name = "asd4", movieId = 1, description = "asd", backdrop = "as"))
        arrayList.add(MovieData(name = "asd5", movieId = 1, description = "asd", backdrop = "as"))
        arrayList.add(MovieData(name = "asd6", movieId = 1, description = "asd", backdrop = "as"))
        arrayList.add(MovieData(name = "asd7", movieId = 1, description = "asd", backdrop = "as"))

        val headerList = ArrayList<String>()
        headerList.add("header 1")
        headerList.add("header 2")

        //todo test sticky
        val adapter = StickyRecycleAdapter(arrayList, headerList)
        val headerdata = MovieHeaderData(R.layout.item_header)
        adapter.setHeaderAndData(arrayList, headerdata)
        adapter.setHeaderAndData(arrayList, headerdata)
        rvMovieList.adapter = adapter
        rvMovieList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

//        val stickyAdapte = MovieStickListRecyclerAdapter(arrayList, this)
//        rvMovieList.adapter = stickyAdapte
//        rvMovieList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        tvMovieDummyTitle.text = resources.getText(R.string.zawgyi_text)

        val phone = "09974009820"
        val result = MyanmarPhoneNumberUtils.isValidMyanmarPhoneNumber(phone)
        Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
        Log.i("movie", phone + " " + result.toString())

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
        showLongToast(t.message.toString())
        //showLongToast(genericErrorMessageFactory.getErrorMessage(t))
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
                    showError(it)
                }
            }
        })
    }

    override fun onItemClick(view: View, position: Int) {

    }

    override fun onItemLongClick(view: View, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}
