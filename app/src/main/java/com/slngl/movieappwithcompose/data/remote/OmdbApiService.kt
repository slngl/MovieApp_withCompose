package com.slngl.movieappwithcompose.data.remote

import com.slngl.movieappwithcompose.data.remote.dto.MovieDetailDto
import com.slngl.movieappwithcompose.data.remote.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApiService {

    //    https://www.omdbapi.com/?apikey=[your_api_key]&s=black
    @GET("/")
    suspend fun getMovies(
        @Query("s") query: String,
        @Query("apikey") apiKey: String
    ): MoviesDto

    //    https://www.omdbapi.com/?apikey=[your_api_key]&i=tt0372784
    @GET("/")
    suspend fun getMovieDetailByID(
        @Query("i") id: String,
        @Query("apikey") apiKey: String
    ): MovieDetailDto

}