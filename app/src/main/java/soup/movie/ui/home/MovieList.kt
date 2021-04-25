package soup.movie.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import soup.movie.model.Movie

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieList(
    movies: List<Movie>,
    selectMovie: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
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
        Image(
            painter = rememberGlidePainter(
                request = movie.imageUrl,
                fadeIn = true,
                fadeInDurationMs = 300
            ),
            contentDescription = null,
            modifier = Modifier
                .clickable { selectMovie(movie.id) }
                .aspectRatio(27 / 40f)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
