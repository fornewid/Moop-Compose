package soup.movie.compose

import android.app.Application
import timber.log.Timber

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
