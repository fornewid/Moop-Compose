package soup.movie.ui

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import soup.movie.ui.utils.LocalBackDispatcher

@Composable
fun MoopApp(backDispatcher: OnBackPressedDispatcher) {
    Providers(LocalBackDispatcher provides backDispatcher) {
        ProvideWindowInsets {
            NavGraph()
        }
    }
}
