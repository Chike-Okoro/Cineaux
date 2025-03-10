package com.ceocoding.cineaux.presentation.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.navigation.RootScreen
import com.ceocoding.cineaux.presentation.search.components.SearchTextField
import com.ceocoding.cineaux.presentation.search.components.SearchedFilmItem
import com.ceocoding.cineaux.ui.theme.CyanColor
import com.ceocoding.cineaux.ui.theme.DarkBlue
import com.ceocoding.cineaux.util.UiEvent

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    parentNavController: NavHostController
) {
    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboardController){
        viewModel.uiEvent.collect{ event ->
            when (event) {
                is UiEvent.ShowToast -> {
                    Toast.makeText(context, event.message.asString(context), Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBlue)
            .padding(16.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (backButton, search) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .constrainAs(backButton) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .clickable { navController.popBackStack() }
            )

            Text(
                text = "Search",
                style = MaterialTheme.typography.h1,
                modifier = Modifier.constrainAs(search){
                    top.linkTo(backButton.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(backButton.bottom)
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        SearchTextField(
            text = state.query,
            onValueChange = {viewModel.onEvent(SearchEvent.OnQueryChange(it))},
            shouldShowHint = state.isHintVisible,
            onSearch = {
                keyboardController?.hide()
                viewModel.onEvent(SearchEvent.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 100.dp)
        ){
            items(state.films){ film ->
                SearchedFilmItem(filmData = film, onClick = {parentNavController.navigate(RootScreen.DetailsScreen.route + "/${film.id}")})
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        when {
            state.isSearching -> CircularProgressIndicator()
            state.films.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}