package com.ceocoding.cineaux.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ceocoding.cineaux.navigation.RootScreen
import com.ceocoding.cineaux.presentation.home.HomeState
import com.ceocoding.cineaux.util.ShimmerListItem


@Composable
fun TrendingFilmSection(
    state: HomeState,
    modifier: Modifier = Modifier,
    onError: () -> Unit,
    navController: NavHostController
){
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
    ){
        LazyRow(modifier = Modifier.fillMaxWidth()){
            items(state.films){ film ->
                TrendingFilmCard(film = film, onClick = {navController.navigate(RootScreen.DetailsScreen.route + "/${film.id}")})
            }
        }
        if(state.isLoading){
            ShimmerListItem(isLoading = true)
        }
        if (state.error.isNotBlank()){
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clickable { onError() }
                    .align(Alignment.Center)
            )
        }
    }

}

