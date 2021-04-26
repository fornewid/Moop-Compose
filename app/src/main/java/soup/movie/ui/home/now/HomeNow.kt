package soup.movie.ui.home.now

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import soup.movie.ui.home.HomeContents
import soup.movie.ui.utils.nowMovies

@Composable
fun HomeNow(selectMovie: (String) -> Unit) {
    HomeContents(nowMovies, selectMovie, Modifier.fillMaxSize())
}
