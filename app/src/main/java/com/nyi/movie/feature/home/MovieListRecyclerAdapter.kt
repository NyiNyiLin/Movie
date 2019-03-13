package com.nyi.movie.feature.home

import android.view.View
import android.view.ViewGroup
import com.nyi.appbase.core.recyclerview.BaseRecyclerViewAdapter
import com.nyi.appbase.core.recyclerview.BaseViewHolder
import com.nyi.appbase.core.recyclerview.RecyclerViewItemClickListener
import com.nyi.appbase.core.recyclerview.diffCallBackWith
import com.nyi.appbase.helper.inflater
import com.nyi.domainn.model.Movie
import com.nyi.movie.R
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieListRecyclerAdapter :
    BaseRecyclerViewAdapter<Movie, MovieListRecyclerAdapter.MovieViewHolder>(
        diffCallback = diffCallBackWith(
        areItemTheSame = { item1, item2 ->
            item1.movieId == item2.movieId
        },
        areContentsTheSame = { item1, item2 ->
            item1 == item2
        }
    )) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent.inflater().inflate(R.layout.item_movie_list, parent, false)
        return MovieViewHolder(view, recyclerViewItemClickListener)
    }

    class MovieViewHolder(
        itemVew : View,
        recyclerViewItemClickListener: RecyclerViewItemClickListener?
        ) : BaseViewHolder<Movie>(itemVew, recyclerViewItemClickListener){

        init {

        }

        override fun bind(item: Movie) {
            itemView.tvName.text = item.name
            itemView.tvDescription.text = item.description
        }
    }
}