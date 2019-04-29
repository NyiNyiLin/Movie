package com.nyi.data.repositoryimpl

import androidx.paging.PagedList
import com.nyi.data.datasource.MovieCacheDataSource
import com.nyi.data.datasource.MovieNetworkDataSource
import com.nyi.domainn.model.Movie
import javax.inject.Inject

class RepoBoundaryCallback @Inject constructor(
  private val query: String,
  private val movieCacheDataSource: MovieCacheDataSource,
  private val movieNetworkDataSource: MovieNetworkDataSource
) : PagedList.BoundaryCallback<Movie>() {

  // keep the last requested page.
  // When the request is successful, increment the page number.
  private var lastRequestedPage = 1

  // avoid triggering multiple requests in the same time
  private var isRequestInProgress = false

  companion object {
    private const val NETWORK_PAGE_SIZE = 50
    private const val DATABASE_PAGE_SIZE = 20
  }

  override fun onZeroItemsLoaded() {
    requestAndSaveData(query)
  }


  override fun onItemAtEndLoaded(itemAtEnd: Movie) {
    requestAndSaveData(query)
  }

  private fun requestAndSaveData(query: String) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        movieNetworkDataSource.getTrendingMovie("name", "time")

    }
}