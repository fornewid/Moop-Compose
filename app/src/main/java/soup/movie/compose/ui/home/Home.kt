package soup.movie.compose.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import soup.movie.compose.R
import soup.movie.compose.ui.theme.MoopComposeTheme
import soup.movie.compose.ui.utils.movies

@Composable
fun Home(selectMovie: (String) -> Unit) {
    MoopComposeTheme {
        val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.NOW) }
        val tabs = HomeTabs.values()
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            bottomBar = {
                BottomNavigation(
                    Modifier.navigationBarsHeight(additional = 56.dp)
                ) {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                            label = { Text(stringResource(tab.title).toUpperCase()) },
                            selected = tab == selectedTab,
                            onClick = { setSelectedTab(tab) },
                            selectedContentColor = MaterialTheme.colors.secondary,
                            unselectedContentColor = LocalContentColor.current,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            }
        ) { innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            when (selectedTab) {
                HomeTabs.NOW -> MovieList(movies, selectMovie, modifier)
                HomeTabs.PLAN -> MovieList(movies, selectMovie, modifier)
                HomeTabs.FAVORITE -> MovieList(movies, selectMovie, modifier)
            }
        }
    }
}

private enum class HomeTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    NOW(R.string.menu_now, R.drawable.ic_home_now),
    PLAN(R.string.menu_plan, R.drawable.ic_home_plan),
    FAVORITE(R.string.menu_favorite, R.drawable.ic_home_favorite)
}
