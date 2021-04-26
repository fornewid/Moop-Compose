package soup.movie.ui.home.favorite

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import soup.movie.ui.home.HomeContents
import soup.movie.ui.utils.favoriteMovies

@Composable
fun HomeFavorite(selectMovie: (String) -> Unit) {
    HomeContents(favoriteMovies, selectMovie, Modifier.fillMaxSize())
}
