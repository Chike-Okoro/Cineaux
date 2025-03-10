package com.ceocoding.cineaux.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.navigation.RootScreen
import com.ceocoding.cineaux.presentation.components.BottomMenuData
import com.ceocoding.cineaux.presentation.components.BottomNavItem
import com.ceocoding.cineaux.presentation.home.HomeViewModel
import com.ceocoding.cineaux.ui.theme.AshColor
import com.ceocoding.cineaux.ui.theme.CyanColor
import com.ceocoding.cineaux.ui.theme.DarkBlue

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    parentNavController: NavHostController,
    navController: NavController
) {

    val trendingFilmsState = viewModel.trendingFilmsState.value
    val nowPlayingFilmsState = viewModel.nowPlayingFilmsState.value
    val popularFilmsState = viewModel.popularFilmsState.value
    val topRatedFilmsState = viewModel.topRatedFilmsState.value
    val upcomingFilmsState = viewModel.upcomingFilmsState.value

    val tabs = listOf("Now Playing", "Upcoming", "Top rated", "Popular")
    var tabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(16.dp)
    ) {
        Text(text = "What do you want to watch?")
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(AshColor)
                .clickable { navController.navigate(BottomMenuData.Search.route) }
                .padding(horizontal = 15.dp, vertical = 12.dp)
        ){
            Text(
                text = stringResource(id = R.string.search),
                color = Color.LightGray
            )
            Image(
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "",
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TrendingFilmSection(state = trendingFilmsState, onError = {viewModel.refreshAll()}, navController = parentNavController)
        Column(modifier = Modifier.fillMaxWidth()) {
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = DarkBlue,
                edgePadding = 0.dp
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                        text = { Text(text = title)}
                    )
                }
            }
            when(tabIndex){
                0 -> OtherFilmSections(state = nowPlayingFilmsState, onError = {viewModel.refreshAll()}, navController = parentNavController )
                1 -> OtherFilmSections(state = upcomingFilmsState, onError = {viewModel.refreshAll()}, navController = parentNavController)
                2 -> OtherFilmSections(state = topRatedFilmsState, onError = {viewModel.refreshAll()}, navController = parentNavController)
                3 -> OtherFilmSections(state = popularFilmsState, onError = {viewModel.refreshAll()}, navController = parentNavController)
            }
        }
    }
}