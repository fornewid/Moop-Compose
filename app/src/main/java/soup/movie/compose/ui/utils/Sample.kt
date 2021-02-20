package soup.movie.compose.ui.utils

import soup.movie.compose.model.Movie

private val rangeForRandom = (0..100000)

fun randomSampleImageUrl(seed: Int = rangeForRandom.random()): String {
    return "https://picsum.photos/seed/$seed/300/300"
}

val movies = (1..100).map {
    Movie(
        id = it.toString(),
        name = "name $it",
        imageUrl = randomSampleImageUrl()
    )
}
