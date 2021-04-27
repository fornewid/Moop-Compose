package soup.movie.ui.home.now

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import soup.movie.model.Movie
import soup.movie.ui.utils.nowMovies

class HomeNowViewModel : ViewModel() {

    private val _movies = MutableLiveData(nowMovies)
    val movies: LiveData<List<Movie>> get() = _movies
}
