package com.ceocoding.cineaux.presentation.components

import com.ceocoding.cineaux.R

sealed class BottomMenuData(val route: String, val icon: Int, val name: String){

    object Home: BottomMenuData(
        route = "home",
        icon = R.drawable.home_icon,
        name = "Home"
    )

    object Search: BottomMenuData(
        route = "search",
        icon = R.drawable.bottom_search_icon,
        name = "Search"
    )

    object WatchList: BottomMenuData(
        route = "watch_list",
        icon = R.drawable.watch_list_icon,
        name = "Watch list"
    )
}
