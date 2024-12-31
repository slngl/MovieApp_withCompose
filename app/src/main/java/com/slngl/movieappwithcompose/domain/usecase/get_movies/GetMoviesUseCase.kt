package com.slngl.movieappwithcompose.domain.usecase.get_movies

import com.slngl.movieappwithcompose.data.remote.dto.toMovieList
import com.slngl.movieappwithcompose.domain.model.Movie
import com.slngl.movieappwithcompose.domain.repository.MovieRepository
import com.slngl.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeGetMovies(searchString: String): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val movieList = repository.getMovies(searchString)
            if (movieList.Response.equals("True")) {
                emit(Resource.Success(movieList.toMovieList()))
            } else {
                emit(Resource.Error("No movie found!"))
            }
        } catch (e: IOException) {
            emit(Resource.Error("No internet connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HttpError"))
        }
    }

}