package soup.movie.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Providers
import androidx.core.view.WindowCompat
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import soup.movie.ui.theme.MoopComposeTheme
import soup.movie.ui.utils.LocalBackDispatcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: edge-to-edge
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Providers(LocalBackDispatcher provides onBackPressedDispatcher) {
                ProvideWindowInsets {
                    MoopComposeTheme {
                        Main()
                    }
                }
            }
        }
    }
}
