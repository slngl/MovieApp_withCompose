package com.slngl.movieappwithcompose.presentation.movie_detail

import com.slngl.movieappwithcompose.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null,
    var error: String = "",
)
