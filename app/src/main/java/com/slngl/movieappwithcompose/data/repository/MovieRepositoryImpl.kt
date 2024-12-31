package com.slngl.movieappwithcompose.data.repository

import com.slngl.movieappwithcompose.data.remote.OmdbApiService
import com.slngl.movieappwithcompose.data.remote.dto.MovieDetailDto
import com.slngl.movieappwithcompose.data.remote.dto.MoviesDto
import com.slngl.movieappwithcompose.domain.repository.MovieRepository
import javax.inject.Inject
import javax.inject.Named

class MovieRepositoryImpl @Inject constructor(
    private val apiService: OmdbApiService,
    @Named("omdb_api_key") private val apiKey: String,
) : MovieRepository {

    override suspend fun getMovies(searchString: String): MoviesDto {
        return apiService.getMovies(searchString, apiKey)
    }

    override suspend fun getMovieDetailByID(imdbID: String): MovieDetailDto {
        return apiService.getMovieDetailByID(imdbID, apiKey)
    }
}
