package com.ceocoding.cineaux.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ceocoding.cineaux.presentation.details.components.DetailsScreen
import com.ceocoding.cineaux.presentation.root.HomeNavScreen


@Composable
fun RootNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = RootScreen.HomeScreen.route,
    ){
        composable(route = RootScreen.HomeScreen.route){
            HomeNavScreen(parentNavController = navController)
        }
        composable(
            route = RootScreen.DetailsScreen.route + "/{movie_id}",
            arguments = listOf(navArgument("movie_id") {type = NavType.IntType})
        ){
            DetailsScreen(navController = navController)
        }
    }

}



