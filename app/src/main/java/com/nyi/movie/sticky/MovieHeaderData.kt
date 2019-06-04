package com.nyi.movie.sticky

import androidx.annotation.LayoutRes
import com.saber.stickyheader.stickyData.HeaderData

data class MovieHeaderData (
    @LayoutRes val layoutResource : Int
) : HeaderData {

    companion object{
        const val HEADER_TYPE_1 = 1
    }


    override fun getHeaderLayout(): Int {
        return layoutResource
    }

    override fun getHeaderType(): Int {
        return HEADER_TYPE_1
    }
}