package com.nyi.movie.sticky

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nyi.movie.R
import com.saber.stickyheader.stickyView.StickHeaderRecyclerView
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_movie_list.view.*

class StickyRecycleAdapter(val dataList : ArrayList<MovieData>, val headerList : ArrayList<String>) : StickHeaderRecyclerView<MovieData, MovieHeaderData>(){


    override fun bindHeaderData(header: View?, headerPosition: Int) {
        header?.tv_item_header?.text = headerList.get(headerPosition)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i("movieee", position.toString())
        if(holder is HeaderVH){
            holder.bindData(headerList.get(position))
        }else if(holder is DataVH){
            holder.bindData(dataList.get(position))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType){
            MovieHeaderData.HEADER_TYPE_1 ->{
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                return HeaderVH(itemView)
            }else ->{
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
                return DataVH(itemView)
            }
        }
    }



    class HeaderVH(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bindData(headerString : String){
            itemView.tv_item_header.text = headerString
        }
    }

    class DataVH(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindData(movieData: MovieData){
            itemView.tvName.text = movieData.name
            itemView.tvDescription.text = movieData.description
        }
    }
}