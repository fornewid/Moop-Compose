package soup.movie.ui.detail

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import soup.movie.ui.home.MovieItem
import soup.movie.ui.theme.MoopComposeTheme
import soup.movie.ui.utils.movies

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
