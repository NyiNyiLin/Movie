package com.nyi.movie.feature.home

import androidx.lifecycle.LiveData
import com.nyi.appbase.core.mvp.Viewable
import com.nyi.appbase.helper.AsyncViewResource
import com.nyi.domainn.model.Movie

interface MainView : Viewable {

    fun subscribeToMovieList(acceptMovieList : LiveData<AsyncViewResource<List<Movie>>>)

    fun showError(t : Throwable)
}