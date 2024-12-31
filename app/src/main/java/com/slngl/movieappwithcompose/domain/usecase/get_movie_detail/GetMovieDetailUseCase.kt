package com.slngl.movieappwithcompose.domain.usecase.get_movie_detail

import com.slngl.movieappwithcompose.data.remote.dto.toMovieDetail
import com.slngl.movieappwithcompose.domain.model.MovieDetail
import com.slngl.movieappwithcompose.domain.repository.MovieRepository
import com.slngl.movieappwithcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {
    fun executeGetMovieDetailByID(imdbID: String): Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetailByID(imdbID)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        } catch (e: IOException) {
            emit(Resource.Error("No internet connection!"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "HttpError"))
        }
    }
}