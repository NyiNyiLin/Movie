package com.nyi.domainn.model

data class Movie(
    var movieId: MovieId,
    var name : String,
    var description : String,
    var backdrop : String,
    var headerType : Int
){
    constructor() : this(MovieId(0), "", "", "", 0)
}

inline class MovieId(val value: Int)