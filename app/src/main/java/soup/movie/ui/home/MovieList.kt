package soup.movie.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import soup.movie.model.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(
    movies: List<Movie>,
    selectMovie: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = modifier.statusBarsPadding(),
        state = rememberLazyListState(),
        contentPadding = PaddingValues(all = 4.dp)
    ) {
        items(movies) { data ->
            MovieItem(data, selectMovie)
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    selectMovie: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        GlideImage(
            data = movie.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clickable { selectMovie(movie.id) }
                .aspectRatio(27 / 40f)
                .fillMaxSize()
        )
    }
}
