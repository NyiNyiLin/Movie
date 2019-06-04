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
import com.nyi.network.exception.NetworkException
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import java.lang.Exception
import java.net.UnknownHostException
import javax.inject.Inject
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

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


        scope.launch(Dispatchers.IO) {

            try{

                val time = measureTimeMillis {

                    val time2 = measureTimeMillis {
                        val localMovie = getTrending.getLocalTrendingMovie(GetTrending.Params("movie", "day"))

                        withContext(Dispatchers.Main){
                            //acceptMovieList.postValue(AsyncViewResource.Success(localMovie))
                        }

                    }

                    withContext(Dispatchers.Main){
                        view?.showError(Throwable("local " + time2.toString()))
                    }


                    val movieList = getTrending.getTrendingMovie(GetTrending.Params("movie", "day"))

                    withContext(Dispatchers.Main){
                        acceptMovieList.postValue(AsyncViewResource.Success(movieList))
                    }

                }

                withContext(Dispatchers.Main){
                    view?.showError(Throwable("network " + time.toString()))
                }

            }catch (e : Exception){
                withContext(Dispatchers.Main){
                    if(e is UnknownHostException){
                        view?.showError(Throwable("No host"))
                    }else if(e is NetworkException){
                        view?.showError(Throwable("No Network"))
                    }else{

                    }

                    //view?.showError(t)
                    //acceptMovieList.postValue(AsyncViewResource.Error(t))


                }
            }

        }

    }

}