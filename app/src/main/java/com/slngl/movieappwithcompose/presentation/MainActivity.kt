package com.slngl.movieappwithcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.slngl.movieappwithcompose.presentation.movie_detail.views.MovieDetailScreen
import com.slngl.movieappwithcompose.presentation.movies.views.MoviesScreen
import com.slngl.movieappwithcompose.presentation.theme.MovieAppWithComposeTheme
import com.slngl.movieappwithcompose.util.Constants.IMDB_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppWithComposeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.MoviesScreen.route,
                ) {
                    composable(route = Screen.MoviesScreen.route) {
                        MoviesScreen(navController)
                    }
                    composable(route = Screen.MovieDetailScreen.route + "/{${IMDB_ID}}") {
                        MovieDetailScreen()
                    }
                }
            }
        }
    }
}
