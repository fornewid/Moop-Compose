package soup.movie.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.chrisbanes.accompanist.insets.navigationBarsHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import soup.movie.compose.R
import soup.movie.model.Movie
import soup.movie.ui.theme.MoopComposeTheme
import soup.movie.ui.utils.movies
import soup.movie.ui.widget.Pager
import soup.movie.ui.widget.PagerState

@Composable
fun Home(selectMovie: (String) -> Unit) {
    MoopComposeTheme {
        val (selectedTab, setSelectedTab) = remember { mutableStateOf(HomeTabs.NOW) }
        val tabs = HomeTabs.values()
        val state = rememberScaffoldState()
        Scaffold(
            scaffoldState = state,
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(selectedTab.title).toUpperCase()) },
                    modifier = Modifier.statusBarsPadding(),
                    navigationIcon = {
                        Image(
                            Icons.Default.Menu,
                            contentDescription = null,
                            contentScale = ContentScale.Inside,
                            colorFilter = ColorFilter.tint(LocalContentColor.current.copy(alpha = LocalContentAlpha.current)),
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(onClick = {
                                    state.drawerState.open()
                                })
                        )
                    }
                )
            },
            drawerContent = {
                DrawerMenu(
                    text = "Search movie",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable(onClick = {
                            state.drawerState.close {
                                //TODO:
                                selectMovie(movies.first().id)
                            }
                        })
                )
                DrawerMenu(
                    text = "Theater Map",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable(onClick = {
                            state.drawerState.close {
                                //TODO:
                                selectMovie(movies.first().id)
                            }
                        })
                )
                Divider()
                DrawerMenu(
                    text = "Settings",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .clickable(onClick = {
                            state.drawerState.close {
                                //TODO:
                                selectMovie(movies.first().id)
                            }
                        })
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
            val modifier = Modifier.padding(innerPadding)
            HomePager(
                items = listOf(movies, movies, movies),
                currentPage = selectedTab.ordinal,
                modifier = modifier,
                onPageSelected = { position -> setSelectedTab(HomeTabs.values()[position]) },
                selectMovie = selectMovie
            )
        }
    }
}

@Composable
fun DrawerMenu(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center)
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
        modifier = modifier,
        onPageSelected = onPageSelected
    ) {
        MovieList(
            movies = items[page],
            selectMovie = selectMovie,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
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
