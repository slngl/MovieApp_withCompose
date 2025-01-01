package com.slngl.movieappwithcompose.di

import com.slngl.movieappwithcompose.BuildConfig
import com.slngl.movieappwithcompose.data.remote.OmdbApiService
import com.slngl.movieappwithcompose.data.repository.MovieRepositoryImpl
import com.slngl.movieappwithcompose.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("omdb_api_key")
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        api: OmdbApiService,
        @Named("omdb_api_key") apiKey: String
    ): MovieRepository =
        MovieRepositoryImpl(apiService = api, apiKey = apiKey)

}