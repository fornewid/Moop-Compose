package soup.movie.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: edge-to-edge
        //WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            MoopApp(onBackPressedDispatcher)
        }
    }
}
