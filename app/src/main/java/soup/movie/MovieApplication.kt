package soup.movie

import android.app.Application
import soup.movie.compose.BuildConfig
import timber.log.Timber

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
