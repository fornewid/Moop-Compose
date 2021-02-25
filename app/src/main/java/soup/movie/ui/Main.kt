package soup.movie.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun Main() {
    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()
    val actions = remember(navController, scaffoldState) {
        MainActions(navController, scaffoldState)
    }
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            DrawerMenu(
                text = "Search movie",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                            actions.goToSearch()
                        }
                    }
            )
            DrawerMenu(
                text = "Theater Map",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                            actions.goToTheaterMap()
                        }
                    }
            )
            Divider()
            DrawerMenu(
                text = "Settings",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clickable {
                        coroutineScope.launch {
                            scaffoldState.drawerState.close()
                            actions.goToSettings()
                        }
                    }
            )
        }
    ) {
        NavGraph(navController, actions)
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
