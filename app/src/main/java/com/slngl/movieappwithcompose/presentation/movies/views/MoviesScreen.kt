package com.slngl.movieappwithcompose.presentation.movies.views

import MovieListRow
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.slngl.movieappwithcompose.presentation.Screen
import com.slngl.movieappwithcompose.presentation.movies.MoviesEvent
import com.slngl.movieappwithcompose.presentation.movies.MoviesViewModel

@Composable
fun MoviesScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
    ) {
        Column {
            MoviesSearchBar(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                hint = "Search...",
                onSearch = {
                    viewModel.onEvent(MoviesEvent.Search(it))
                },
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies) { movie ->
                    MovieListRow(movie) {
                        navController.navigate(Screen.MovieDetailScreen.route + "/${movie.imdbID}")
                    }
                }
            }
        }
    }
}

@Composable
fun MoviesSearchBar(
    modifier: Modifier = Modifier,
    hint: String = "Search...",
    onSearch: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        TextField(
            value = text,
            onValueChange = { newText ->
                text = newText
            },
            keyboardActions =
                KeyboardActions(
                    onDone = { onSearch(text) },
                ),
            maxLines = 1,
            singleLine = true,
            placeholder = {
                Text(
                    text = hint,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            textStyle =
                TextStyle(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                ),
            shape = RoundedCornerShape(30.dp),
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    }.padding(horizontal = 50.dp, vertical = 12.dp),
        )

        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = if (isFocused || text.isNotEmpty()) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier =
                Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp),
        )

        if (text.isNotEmpty()) {
            IconButton(
                onClick = { text = "" },
                modifier =
                    Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Search",
                    tint = Color.Gray,
                )
            }
        }
    }
}
