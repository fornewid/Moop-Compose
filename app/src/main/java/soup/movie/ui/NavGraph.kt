package soup.movie.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import soup.movie.compose.R
import soup.movie.ui.MainDestinations.MOVIE_DETAIL_ID_KEY
import soup.movie.ui.detail.MovieDetails
import soup.movie.ui.home.Home
import soup.movie.ui.search.Search
import soup.movie.ui.settings.Settings
import soup.movie.ui.settings.theme.ThemeSettings
import soup.movie.ui.theater.edit.TheaterEdit
import soup.movie.ui.theater.edit.TheaterSort
import soup.movie.ui.theatermap.TheaterMap

object MainDestinations {
    const val HOME_ROUTE = "home"
    const val MOVIE_DETAIL_ROUTE = "detail"
    const val MOVIE_DETAIL_ID_KEY = "movieId"
    const val SEARCH_ROUTE = "search"
    const val THEATER_MAP_ROUTE = "theaterMap"
    const val SETTINGS_ROUTE = "settings"
    const val THEME_SETTINGS_ROUTE = "themeSettings"
    const val THEATER_SORT_ROUTE = "theaterSort"
    const val THEATER_EDIT_ROUTE = "theaterEdit"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    actions: MainActions,
    startDestination: String = MainDestinations.HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.HOME_ROUTE) {
            Home(
                openDrawer = actions.openDrawer,
                selectMovie = actions.selectMovie
            )
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
        composable(MainDestinations.SEARCH_ROUTE) {
            Search(upPress = actions.upPress)
        }
        composable(MainDestinations.THEATER_MAP_ROUTE) {
            TheaterMap(upPress = actions.upPress)
        }
        composable(MainDestinations.SETTINGS_ROUTE) {
            Settings(
                goToThemeSettings = actions.goToThemeSettings,
                goToTheaterSort = actions.goToTheaterSort,
                upPress = actions.upPress
            )
        }
        composable(MainDestinations.THEME_SETTINGS_ROUTE) {
            ThemeSettings(upPress = actions.upPress)
        }
        composable(MainDestinations.THEATER_SORT_ROUTE) {
            TheaterSort(
                goToTheaterEdit = actions.goToTheaterEdit,
                upPress = actions.upPress
            )
        }
        composable(MainDestinations.THEATER_EDIT_ROUTE) {
            TheaterEdit(upPress = actions.upPress)
        }
    }
}

class MainActions(navController: NavHostController, scaffoldState: ScaffoldState) {
    val openDrawer: suspend () -> Unit = {
        scaffoldState.drawerState.open()
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
    val selectMovie: (String) -> Unit = { movieId: String ->
        navController.navigate("${MainDestinations.MOVIE_DETAIL_ROUTE}/$movieId")
    }
    val goToSearch: () -> Unit = {
        navController.navigate(MainDestinations.SEARCH_ROUTE) {
            popUpTo(MainDestinations.HOME_ROUTE) {
                inclusive = false
            }
            anim {
                enter = R.animator.fragment_open_enter
                exit = R.animator.fragment_open_exit
            }
        }
    }
    val goToTheaterMap: () -> Unit = {
        navController.navigate(MainDestinations.THEATER_MAP_ROUTE) {
            popUpTo(MainDestinations.HOME_ROUTE) {
                inclusive = false
            }
            anim {
                enter = R.animator.fragment_open_enter
                exit = R.animator.fragment_open_exit
            }
        }
    }
    val goToSettings: () -> Unit = {
        navController.navigate(MainDestinations.SETTINGS_ROUTE) {
            popUpTo(MainDestinations.HOME_ROUTE) {
                inclusive = false
            }
            anim {
                enter = R.animator.fragment_open_enter
                exit = R.animator.fragment_open_exit
            }
        }
    }
    val goToThemeSettings: () -> Unit = {
        navController.navigate(MainDestinations.THEME_SETTINGS_ROUTE) {
            anim {
                enter = R.animator.fragment_open_enter
                exit = R.animator.fragment_open_exit
            }
        }
    }
    val goToTheaterSort: () -> Unit = {
        navController.navigate(MainDestinations.THEATER_SORT_ROUTE) {
            anim {
                enter = R.animator.fragment_open_enter
                exit = R.animator.fragment_open_exit
            }
        }
    }
    val goToTheaterEdit: () -> Unit = {
        navController.navigate(MainDestinations.THEATER_EDIT_ROUTE) {
            anim {
                enter = R.animator.fragment_open_enter
                exit = R.animator.fragment_open_exit
            }
        }
    }
}
