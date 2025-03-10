package com.ceocoding.cineaux.navigation

sealed class RootScreen(val route: String){
    object SplashScreen: RootScreen("splash_screen")
    object HomeScreen: RootScreen("home_screen")
    object DetailsScreen: RootScreen("details_screen")
}