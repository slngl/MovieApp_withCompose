package com.slngl.movieappwithcompose.presentation.movie_detail.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.slngl.movieappwithcompose.presentation.movie_detail.MovieDetailViewModel

@Composable
fun MovieDetailScreen(movieDetailViewModel: MovieDetailViewModel = hiltViewModel()) {
    val state = movieDetailViewModel.state.value

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {
        state.movieDetail?.let { movieDetail ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp),
            ) {
                MoviePoster(posterUrl = movieDetail.Poster)
                MovieTitle(movieDetail.Title)
                MovieDetailText(label = "Year", value = movieDetail.Year)
                MovieDetailText(label = "Actors", value = movieDetail.Actors)
                MovieDetailText(label = "Language", value = movieDetail.Language)
                MovieDetailText(label = "Country", value = movieDetail.Country)
                MovieDetailText(label = "Director", value = movieDetail.Director)
            }
        }
    }
}

@Composable
fun MovieTitle(title: String) {
    Text(
        text = title,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
        style =
            MaterialTheme.typography.titleLarge.copy(
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                letterSpacing = 2.sp,
                lineHeight = 36.sp,
            ),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
    )
}

@Composable
fun MoviePoster(posterUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = posterUrl),
        contentDescription = "Movie Poster",
        modifier =
            Modifier
                .size(300.dp)
                .padding(12.dp)
                .clip(RectangleShape)
                .background(MaterialTheme.colorScheme.surface),
    )
}

@Composable
fun MovieDetailText(
    label: String,
    value: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 4.dp),
    ) {
        Text(
            text = "$label: $value",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
