package soup.movie.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun Settings(
    goToThemeSettings: () -> Unit,
    goToTheaterSort: () -> Unit,
    upPress: () -> Unit
) {
    MoopComposeTheme {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Text(
                    text = "Settings",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .clickable {
                            upPress()
                        }
                )
                Text(
                    text = "Go To Theme Settings",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            goToThemeSettings()
                        }
                )
                Text(
                    text = "Go To Theater Sort",
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .clickable {
                            goToTheaterSort()
                        }
                )
            }
        }
    }
}
