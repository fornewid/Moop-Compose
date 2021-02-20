package soup.movie.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import soup.movie.ui.MainDestinations.MOVIE_DETAIL_ID_KEY
import soup.movie.ui.detail.MovieDetails
import soup.movie.ui.home.Home

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val MOVIE_DETAIL_ROUTE = "detail"
    const val MOVIE_DETAIL_ID_KEY = "movieId"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.HOME_ROUTE) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            Home(selectMovie = actions.selectMovie)
        }
        composable(
            "${MainDestinations.MOVIE_DETAIL_ROUTE}/{$MOVIE_DETAIL_ID_KEY}",
            arguments = listOf(navArgument(MOVIE_DETAIL_ID_KEY) { type = NavType.StringType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            MovieDetails(
                movieId = arguments.getString(MOVIE_DETAIL_ID_KEY).orEmpty(),
                upPress = actions.upPress
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val selectMovie: (String) -> Unit = { movieId: String ->
        navController.navigate("${MainDestinations.MOVIE_DETAIL_ROUTE}/$movieId")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}
