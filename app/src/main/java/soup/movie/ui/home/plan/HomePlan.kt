package soup.movie.ui.home.plan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import soup.movie.ui.home.HomeContents
import soup.movie.ui.utils.planMovies

@Composable
fun HomePlan(selectMovie: (String) -> Unit) {
    HomeContents(planMovies, selectMovie, Modifier.fillMaxSize())
}
