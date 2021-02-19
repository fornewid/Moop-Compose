package soup.movie.compose.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.chrisbanes.accompanist.glide.GlideImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import soup.movie.compose.model.Movie

@Composable
fun Movies(
    movies: List<Movie>,
    selectMovie: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.statusBarsPadding()
    ) {
        items(movies) { data ->
            Movie(data, selectMovie)
        }
    }
}

@Composable
fun Movie(
    movie: Movie,
    selectMovie: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(4.dp),
        color = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.medium
    ) {
        ConstraintLayout(
            modifier = Modifier.clickable(
                onClick = { selectMovie(movie.id) }
            )
        ) {
            val (image, name) = createRefs()
            GlideImage(
                data = movie.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .aspectRatio(4f / 3f)
                    .constrainAs(image) {
                        centerHorizontallyTo(parent)
                        top.linkTo(parent.top)
                    }
            )
            Text(
                text = movie.name,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .constrainAs(name) {
                        centerHorizontallyTo(parent)
                        top.linkTo(image.bottom)
                    }
            )
        }
    }
}
