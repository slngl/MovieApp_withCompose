package com.slngl.movieappwithcompose.presentation.movie_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slngl.movieappwithcompose.domain.usecase.get_movie_detail.GetMovieDetailUseCase
import com.slngl.movieappwithcompose.util.Constants.IMDB_ID
import com.slngl.movieappwithcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel
    @Inject
    constructor(
        private val getMovieDetailUseCase: GetMovieDetailUseCase,
        private val stateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _state = mutableStateOf(MovieDetailState())
        val state = _state

        init {
            stateHandle.get<String>(IMDB_ID)?.let {
                getMovieDetail(it)
            }
        }

        private fun getMovieDetail(imdbID: String) {
            getMovieDetailUseCase
                .executeGetMovieDetailByID(imdbID)
                .onEach {
                    when (it) {
                        is Resource.Success -> {
                            _state.value = MovieDetailState(movieDetail = it.data)
                        }

                        is Resource.Error -> {
                            _state.value = MovieDetailState(error = it.message ?: "error!")
                        }

                        is Resource.Loading -> {
                            _state.value = MovieDetailState(isLoading = true)
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
