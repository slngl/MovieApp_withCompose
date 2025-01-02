package com.slngl.movieappwithcompose.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.slngl.movieappwithcompose.domain.usecase.get_movies.GetMoviesUseCase
import com.slngl.movieappwithcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
    @Inject
    constructor(
        private val getMoviesUseCase: GetMoviesUseCase,
    ) : ViewModel() {
        private val _state = mutableStateOf<MoviesState>(MoviesState())
        val state: State<MoviesState> = _state

        private var job: Job? = null

        init {
            getMovies(_state.value.searchString)
        }

        private fun getMovies(search: String) {
            job?.cancel()
            job =
                getMoviesUseCase
                    .executeGetMovies(search)
                    .onEach {
                        when (it) {
                            is Resource.Success -> {
                                _state.value = MoviesState(movies = it.data ?: emptyList())
                            }

                            is Resource.Error -> {
                                _state.value = MoviesState(error = it.message ?: "Error!")
                            }

                            is Resource.Loading -> {
                                _state.value = MoviesState(isLoading = true)
                            }
                        }
                    }.launchIn(viewModelScope)
        }

        fun onEvent(event: MoviesEvent) {
            when (event) {
                is MoviesEvent.Search -> {
                    getMovies(event.searchString)
                }
            }
        }
    }
