package com.slngl.movieappwithcompose.presentation.movies

import com.slngl.movieappwithcompose.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val searchString: String = "anna",
)
