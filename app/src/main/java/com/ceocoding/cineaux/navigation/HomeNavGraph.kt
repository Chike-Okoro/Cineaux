package com.ceocoding.cineaux.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ceocoding.cineaux.presentation.components.BottomMenuData
import com.ceocoding.cineaux.presentation.home.components.HomeScreen
import com.ceocoding.cineaux.presentation.search.SearchScreen
import com.ceocoding.cineaux.presentation.watch_list.WatchListScreen



fun NavGraphBuilder.bottomNavigation(parentNavController: NavHostController, navController: NavController){
    composable(BottomMenuData.Home.route){
        HomeScreen(parentNavController = parentNavController, navController = navController)
    }
    composable(route = BottomMenuData.Search.route){
        SearchScreen(navController = navController, parentNavController = parentNavController)
    }
    composable(route = BottomMenuData.WatchList.route){
        WatchListScreen(navController = navController, parentNavController = parentNavController)
    }
}

@Composable
fun SetupNavGraphBottomBar(navController: NavHostController, parentNavController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = BottomMenuData.Home.route
    ){
        bottomNavigation(navController = navController, parentNavController = parentNavController)
    }
}