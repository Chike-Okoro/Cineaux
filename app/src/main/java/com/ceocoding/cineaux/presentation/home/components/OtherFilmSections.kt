package com.ceocoding.cineaux.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.ceocoding.cineaux.util.ShimmerVerticalGrid

@Composable
fun OtherFilmSections(
    modifier: Modifier = Modifier,
    state: HomeState,
    onError: () -> Unit,
    navController: NavHostController
) {
    Box(modifier = Modifier.fillMaxSize()){
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = modifier.fillMaxHeight()
        ){
            items(state.films){ film ->
                FilmCard(film = film, modifier = Modifier.clickable { navController.navigate(RootScreen.DetailsScreen.route + "/${film.id}") })
            }
        }
        if (state.isLoading){
            ShimmerVerticalGrid(isLoading = true)
        }
        if(state.error.isNotBlank()){
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