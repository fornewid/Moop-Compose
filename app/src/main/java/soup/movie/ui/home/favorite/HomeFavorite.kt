package soup.movie.ui.home.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import soup.movie.ui.home.HomeContents

@Composable
fun HomeFavorite(
    selectMovie: (String) -> Unit,
    viewModel: HomeFavoriteViewModel = viewModel()
) {
    val movies by viewModel.movies.observeAsState(emptyList())
    HomeContents(movies, selectMovie, Modifier.fillMaxSize())
}
