package soup.movie.ui.utils

import soup.movie.model.Movie

private val rangeForRandom = (0..100000)

private fun randomSampleImageUrl(seed: Int = rangeForRandom.random()): String {
    return "https://picsum.photos/seed/$seed/300/300"
}

val movies = (1..100).map {
    Movie(
        id = it.toString(),
        name = "name $it",
        imageUrl = randomSampleImageUrl()
    )
}
val nowMovies = movies.take(50)
val planMovies = movies.takeLast(50)
val favoriteMovies = movies.filterIndexed { index, _ -> index % 2 == 0 }
