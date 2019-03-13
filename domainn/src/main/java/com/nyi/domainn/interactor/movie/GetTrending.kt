package com.nyi.domainn.interactor.movie


import com.nyi.domainn.rxusecase.SingleUseCase
import com.nyi.domainn.executor.PostExecutionThread
import com.nyi.domainn.executor.ThreadExecutor
import com.nyi.domainn.model.Movie
import com.nyi.domainn.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTrending @Inject constructor(
    private val movieRespository: MovieRepository,
    postExecutionThread: PostExecutionThread,
    threadExecutor: ThreadExecutor
) :
    SingleUseCase<List<Movie>, GetTrending.Params>(postExecutionThread, threadExecutor) {

    override fun provideSingle(params: GetTrending.Params): Single<List<Movie>> {
        return movieRespository.getTrendingMovie(params.mediaType, params.time)
    }

    data class Params(
        val mediaType : String,
        val time : String
    )
}