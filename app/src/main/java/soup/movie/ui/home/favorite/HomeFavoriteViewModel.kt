package soup.movie.ui.home.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import soup.movie.model.Movie
import soup.movie.ui.utils.favoriteMovies

class HomeFavoriteViewModel : ViewModel() {

    private val _movies = MutableLiveData(favoriteMovies)
    val movies: LiveData<List<Movie>> get() = _movies
}
