package soup.movie.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import soup.movie.compose.R
import soup.movie.ui.home.favorite.HomeFavorite
import soup.movie.ui.home.now.HomeNow
import soup.movie.ui.home.plan.HomePlan

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Home(
    openDrawer: suspend () -> Unit,
    selectMovie: (String) -> Unit
) {
    val tabs = HomeTabs.values()
    val pagerState = rememberPagerState(pageCount = tabs.size)
    val selectedTab = tabs[pagerState.currentPage]
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
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(tab.ordinal)
                            }
                        },
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = LocalContentColor.current,
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            state = pagerState,
            offscreenLimit = tabs.size,
            modifier = Modifier
                .padding(innerPadding)
                .background(color = MaterialTheme.colors.primarySurface)
        ) { page ->
            when (tabs[page]) {
                HomeTabs.NOW -> HomeNow(selectMovie)
                HomeTabs.PLAN -> HomePlan(selectMovie)
                HomeTabs.FAVORITE -> HomeFavorite(selectMovie)
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
