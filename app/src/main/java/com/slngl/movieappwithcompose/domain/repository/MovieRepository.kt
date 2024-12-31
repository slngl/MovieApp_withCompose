package com.slngl.movieappwithcompose.domain.repository

import com.slngl.movieappwithcompose.data.remote.dto.MovieDetailDto
import com.slngl.movieappwithcompose.data.remote.dto.MoviesDto

interface MovieRepository {

    suspend fun getMovies(searchString: String): MoviesDto

    suspend fun getMovieDetailByID(imdbID: String): MovieDetailDto
}
