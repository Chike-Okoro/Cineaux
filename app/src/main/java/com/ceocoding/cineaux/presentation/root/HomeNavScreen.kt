package com.ceocoding.cineaux.presentation.root

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ceocoding.cineaux.ui.theme.AshColor
import com.ceocoding.cineaux.ui.theme.CyanColor
import com.ceocoding.cineaux.ui.theme.DarkBlue
import com.ceocoding.cineaux.navigation.SetupNavGraphBottomBar
import com.ceocoding.cineaux.presentation.components.BottomMenuData
import com.ceocoding.cineaux.presentation.components.BottomNavItem

@Composable
fun HomeNavScreen(
    navController: NavHostController = rememberNavController(),
    parentNavController: NavHostController
) {

    val items = listOf(
        BottomMenuData.Home,
        BottomMenuData.Search,
        BottomMenuData.WatchList
    )
    Scaffold(
        bottomBar = {
            BottomNavBar(
                navController = navController,
                items = items,
                onItemClick = {
                    navController.navigate(it.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                    }
                }
            )

        }
    ) {
        SetupNavGraphBottomBar(navController = navController, parentNavController = parentNavController)
    }

}

@Composable
private fun BottomNavBar(
    navController: NavHostController,
    items: List<BottomMenuData>,
    modifier: Modifier = Modifier,
    onItemClick: (BottomMenuData) -> Unit
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination?.route

    BottomNavigation(
        modifier = modifier,
        backgroundColor = DarkBlue,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = currentDestination == item.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = CyanColor,
                unselectedContentColor = AshColor,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.name,
                            tint = if(selected) CyanColor else AshColor
                        )
                        if(selected){
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = CyanColor
                            )
                        }
                    }
                }
            )
        }



    }

}