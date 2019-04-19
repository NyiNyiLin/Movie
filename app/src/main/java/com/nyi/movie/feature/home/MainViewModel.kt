package com.nyi.movie.feature.home

import android.provider.Settings
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nyi.appbase.core.mvp.BaseViewModel
import com.nyi.appbase.helper.AsyncViewResource
import com.nyi.domainn.interactor.movie.GetTrending
import com.nyi.domainn.model.Movie
import com.nyi.domainn.model.MovieId
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
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

        /*getTrending.execute(GetTrending.Params("movie", "day")).subscribeBy (
            onSuccess= {
                acceptMovieList.postValue(AsyncViewResource.Success(it))
            },
            onError = {
                view?.showError(it)
            }



        ).addTo(compositeDisposable)*/

        GlobalScope.launch(Dispatchers.IO) {

            try{

                val movieList = getTrending.getTrendingMovie(GetTrending.Params("movie", "day"))

                withContext(Dispatchers.Main){
                    acceptMovieList.postValue(AsyncViewResource.Success(movieList))
                }

            }catch (t : Throwable){
                withContext(Dispatchers.Main){
                    view?.showError(t)
                    //acceptMovieList.postValue(AsyncViewResource.Error(t))
                }
            }

        }

    }

}