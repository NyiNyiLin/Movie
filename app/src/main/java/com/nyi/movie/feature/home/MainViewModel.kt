package com.nyi.movie.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nyi.appbase.core.mvp.BaseViewModel
import com.nyi.appbase.helper.AsyncViewResource
import com.nyi.domainn.interactor.movie.GetTrending
import com.nyi.domainn.model.Movie
import com.nyi.domainn.model.MovieId
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTrending: GetTrending
    )
    : BaseViewModel<MainView>(){

    private val acceptMovieList = MutableLiveData<AsyncViewResource<List<Movie>>>()

    override fun attachView(viewable: MainView) {
        super.attachView(viewable)
        view?.subscribeToMovieList(acceptMovieList)
    }

    fun refreshMovieList(){

        getTrending.execute(GetTrending.Params("movie", "day")).subscribeBy (
            onSuccess= {
                acceptMovieList.postValue(AsyncViewResource.Success(it))
            },
            onError = {
                view?.showError(it)
            }



        ).addTo(compositeDisposable)


    }

}