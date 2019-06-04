package com.nyi.movie.sticky

import com.saber.stickyheader.stickyData.StickyMainData

data class MovieData(
    var movieId: Int,
    var name : String,
    var description : String,
    var backdrop : String
) : StickyMainData