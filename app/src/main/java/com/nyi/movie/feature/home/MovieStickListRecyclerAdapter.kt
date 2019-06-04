package com.nyi.movie.feature.home

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.nyi.appbase.core.recyclerview.BaseRecyclerViewAdapter
import com.nyi.appbase.core.recyclerview.BaseViewHolder
import com.nyi.appbase.core.recyclerview.RecyclerViewItemClickListener
import com.nyi.appbase.core.recyclerview.diffCallBackWith
import com.nyi.appbase.helper.inflater
import com.nyi.domainn.model.Movie
import com.nyi.movie.R
import com.nyi.movie.helper.StickyItemDecoration
import com.snakydesign.livedataextensions.concat
import kotlinx.android.synthetic.main.item_movie_list.view.*



class MovieStickListRecyclerAdapter (val movieList : List<Movie>, val recyclerViewItemClickListener : RecyclerViewItemClickListener) :
    RecyclerView.Adapter<MovieStickListRecyclerAdapter.MovieViewHolder>(), StickyItemDecoration.StickyHeaderInterface {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList.get(position))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        when(viewType){
            0 -> {
                val view = parent.inflater().inflate(R.layout.item_movie_list, parent, false)
                return MovieViewHolder(view, recyclerViewItemClickListener)
            }
            1 -> {
                val view = parent.inflater().inflate(R.layout.item_movie_list, parent, false)
                return MovieViewHolder(view, recyclerViewItemClickListener)
            }
            else -> {
                val view = parent.inflater().inflate(R.layout.item_movie_list, parent, false)
                return MovieViewHolder(view, recyclerViewItemClickListener)
            }

        }


    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(StickyItemDecoration(this))
    }

    override fun getItemViewType(position: Int): Int {

        return movieList.get(position).headerType

    }

    override fun bindHeaderData(header: View, headerPosition: Int) {

    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.item_movie_list
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var itemPositionn = itemPosition
        var headerPosition = 0
        do {
            if (this.isHeader(itemPosition)) {
                headerPosition = itemPosition
                break
            }
            itemPositionn -= 1
        } while (itemPosition >= 0)
        return headerPosition
    }

    override fun isHeader(itemPosition: Int): Boolean {
        if(movieList.get(itemPosition).headerType == 0){
            return false
        }else{
            return true
        }
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
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/original/" + item.backdrop)
                .placeholder(R.mipmap.ic_launcher)
                .transform()
                .into(itemView.ivImage)
        }


    }
}