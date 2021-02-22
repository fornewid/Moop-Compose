package soup.movie.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import soup.movie.ui.theme.MoopComposeTheme

@Composable
fun Search(upPress: () -> Unit) {
    MoopComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Text(
                text = "Search",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
                    .clickable {
                        upPress()
                    }
            )
        }
    }
}
