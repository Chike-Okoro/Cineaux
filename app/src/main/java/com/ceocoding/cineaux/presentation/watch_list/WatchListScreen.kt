package com.ceocoding.cineaux.presentation.watch_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.ui.theme.AshColor
import com.ceocoding.cineaux.ui.theme.DarkBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WatchListScreen(
    viewModel: WatchListViewModel = hiltViewModel(),
    navController: NavController,
    parentNavController: NavHostController,
) {
    val fullWatchList = viewModel.fullWatchList.collectAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 16.dp)
                .fillMaxWidth(0.68f)
        ){
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .clickable { navController.popBackStack() }
            )
            Text(
                text = "Watch list",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )

        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ){
            items(fullWatchList.value, key = {it.id} ){ filmDetail ->
                SwipeToDismissItem(
                    modifier = Modifier.animateItemPlacement(),
                    onDismiss = { viewModel.removeFromWatchList(filmDetail)}
                ) {
                    WatchListItem(filmDetail = filmDetail, parentNavController = parentNavController)
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when{
            fullWatchList.value.isEmpty() -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.no_films_image),
                        contentDescription = null,
                        modifier = Modifier.size(150.dp)
                    )
                    Text(
                        text = "There is no movie yet",
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Discover and save your favourite movies",
                        color = AshColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}