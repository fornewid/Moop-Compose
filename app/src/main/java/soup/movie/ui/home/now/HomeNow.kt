package soup.movie.ui.home.now

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import soup.movie.ui.home.HomeContents

@Composable
fun HomeNow(
    selectMovie: (String) -> Unit,
    viewModel: HomeNowViewModel = viewModel()
) {
    val movies by viewModel.movies.observeAsState(emptyList())
    HomeContents(movies, selectMovie, Modifier.fillMaxSize())
}
