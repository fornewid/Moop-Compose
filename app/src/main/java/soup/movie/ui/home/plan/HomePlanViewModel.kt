package soup.movie.ui.home.plan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import soup.movie.model.Movie
import soup.movie.ui.utils.planMovies

class HomePlanViewModel : ViewModel() {

    private val _movies = MutableLiveData(planMovies)
    val movies: LiveData<List<Movie>> get() = _movies
}
