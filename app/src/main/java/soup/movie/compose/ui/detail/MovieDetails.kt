package soup.movie.compose.ui.detail

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import soup.movie.compose.ui.home.MovieItem
import soup.movie.compose.ui.theme.MoopComposeTheme
import soup.movie.compose.ui.utils.movies

@Composable
fun MovieDetails(movieId: String, upPress: () -> Unit) {
    MoopComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            val movie = movies.find { it.id == movieId }
            if (movie != null) {
                MovieItem(movie, selectMovie = { upPress() })
            }
        }
    }
}
