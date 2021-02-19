package soup.movie.compose.ui.detail

import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.glide.GlideImage
import soup.movie.compose.ui.theme.MoopComposeTheme
import soup.movie.compose.ui.utils.randomSampleImageUrl

@Composable
fun MovieDetails(movieId: String, upPress: () -> Unit) {
    MoopComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Greeting(movieId, upPress)
        }
    }
}

@Composable
fun Greeting(name: String, upPress: () -> Unit) {
    LazyColumn {
        item {
            Text(text = "Hello $name!")
        }
        item {
            Button(onClick = upPress) {
                Card {
                    GlideImage(
                        data = randomSampleImageUrl(),
                        contentDescription = null,
                        modifier = Modifier.preferredSize(128.dp)
                    )
                }
            }
        }
    }
}
