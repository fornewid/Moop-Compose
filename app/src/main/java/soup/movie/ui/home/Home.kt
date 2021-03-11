package soup.movie.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.launch
import soup.movie.compose.R
import soup.movie.model.Movie
import soup.movie.ui.utils.movies
import soup.movie.ui.widget.Pager
import soup.movie.ui.widget.PagerState

@Composable
fun Home(
    openDrawer: suspend () -> Unit,
    selectMovie: (String) -> Unit
) {
    val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.NOW) }
    val tabs = HomeTabs.values()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(selectedTab.title).toUpperCase())
                },
                modifier = Modifier.statusBarsPadding(),
                navigationIcon = {
                    Image(
                        Icons.Default.Menu,
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        colorFilter = ColorFilter.tint(LocalContentColor.current.copy(alpha = LocalContentAlpha.current)),
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                coroutineScope.launch {
                                    openDrawer()
                                }
                            }
                    )
                }
            )
        },
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
        HomePager(
            items = listOf(movies, movies, movies),
            currentPage = selectedTab.ordinal,
            modifier = Modifier.padding(innerPadding),
            onPageSelected = { position -> setSelectedTab(HomeTabs.values()[position]) },
            selectMovie = selectMovie
        )
    }
}

@Composable
fun HomePager(
    items: List<List<Movie>>,
    currentPage: Int,
    modifier: Modifier = Modifier,
    pagerState: PagerState = remember { PagerState() },
    onPageSelected: (Int) -> Unit,
    selectMovie: (String) -> Unit,
) {
    if (pagerState.currentPage != currentPage) {
        pagerState.currentPage = currentPage
    }
    pagerState.maxPage = (items.size - 1).coerceAtLeast(0)

    Pager(
        state = pagerState,
        modifier = modifier.background(color = MaterialTheme.colors.primarySurface),
        onPageSelected = onPageSelected
    ) {
        MovieList(
            movies = items[page],
            selectMovie = selectMovie,
            modifier = Modifier.fillMaxHeight()
        )
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
