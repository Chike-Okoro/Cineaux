package com.ceocoding.cineaux.presentation.details.components

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.presentation.details.DetailsViewModel
import com.ceocoding.cineaux.presentation.watch_list.WatchListViewModel
import com.ceocoding.cineaux.ui.theme.CyanColor
import com.ceocoding.cineaux.ui.theme.DarkBlue
import com.ceocoding.cineaux.ui.theme.LightOrange
import com.ceocoding.cineaux.util.Constants

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
    navController: NavController,
    watchListViewModel: WatchListViewModel = hiltViewModel()
) {
    val detailsState = viewModel.state.value
    val castState = viewModel.castState.value
    val context = LocalContext.current
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBlue)
    ) {
        val tabs = listOf("About Movie", "Cast")
        var tabIndex by remember { mutableStateOf(0) }
        detailsState.filmDetail?.let { filmDetail ->
            val isInWatchList = watchListViewModel.getOrCreateStateForFilm(filmDetail.id).value
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val (
                    backButton,
                    detailText,
                    saveIcon,
                    backDropImage,
                    posterImage,
                    ratingRow,
                    filmTitleBox,
                    genreRow
                ) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .constrainAs(backButton) {
                            top.linkTo(parent.top, margin = 16.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                        }
                        .clickable { navController.popBackStack() }
                )

                Text(
                    text = "Details",
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.constrainAs(detailText){
                        top.linkTo(backButton.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(backButton.bottom)
                    }
                )
                IconButton(
                    onClick = {
                        if(isInWatchList){
                            watchListViewModel.removeFromWatchList(filmDetail)
                            Toast.makeText(context, "Removed From WatchList", Toast.LENGTH_SHORT).show()
                        }else{
                            watchListViewModel.addToWatchList(filmDetail)
                            Toast.makeText(context, "Added to WatchList", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.constrainAs(saveIcon){
                        top.linkTo(detailText.top)
                        end.linkTo(parent.end, margin = 16.dp)
                        bottom.linkTo(detailText.bottom)
                    }
                ) {
                    Icon(
                        imageVector = if(isInWatchList) Icons.Default.Cancel else Icons.Default.Bookmark,
                        contentDescription = null
                    )
                }
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(Constants.BASE_BACKDROP_IMAGE_URL + filmDetail.backdropPath)
                        .crossfade(true)
                        .error(R.drawable.image_unavailable)
                        .fallback(R.drawable.image_unavailable)
                        .build(),
                    contentDescription = filmDetail.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                        .fillMaxHeight(0.33f)
                        .constrainAs(backDropImage) {
                            top.linkTo(detailText.bottom, margin = 20.dp)
                        },
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply {
                        setToScale(0.5f,0.5f,0.5f,1f)
                    })
                )

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(Constants.BASE_POSTER_IMAGE_URL + filmDetail.posterPath)
                        .crossfade(true)
                        .error(R.drawable.image_unavailable)
                        .fallback(R.drawable.image_unavailable)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .width(115.dp)
                        .height(175.2.dp)
                        .constrainAs(posterImage) {
                            top.linkTo(backDropImage.bottom)
                            bottom.linkTo(backDropImage.bottom)
                            start.linkTo(parent.start)
                        }
                )
                
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(7.dp))
                        .background(Color(0f, 0f, 0f, 0.5f))
                        .padding(5.dp)
                        .constrainAs(ratingRow) {
                            end.linkTo(parent.end, margin = 8.dp)
                            bottom.linkTo(backDropImage.bottom, margin = 8.dp)
                        },
                    horizontalArrangement = Arrangement.spacedBy(1.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rating_star),
                        contentDescription = null,
                    )
                    val originalDouble = filmDetail.voteAverage
                    val formattedDouble = String.format("%.1f", if (originalDouble % 1 == 0.0) originalDouble else originalDouble.roundToNextHalf())
                    Text(
                        text = formattedDouble,
                        color = LightOrange
                    )
                }

                Column(
                    modifier = Modifier
                        .constrainAs(filmTitleBox){
                            start.linkTo(posterImage.end, margin = 12.dp)
                            bottom.linkTo(posterImage.bottom, margin = 10.dp)
                            end.linkTo(parent.end, margin = 12.dp)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = filmDetail.title,
                        modifier = Modifier
                            .padding(top = 2.dp, start = 4.dp, bottom = 4.dp)
                            .fillMaxWidth(0.5f),
                        maxLines = 2,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = filmDetail.releaseDate,
                        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.LightGray
                    )
                }
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(genreRow) {
                            top.linkTo(posterImage.bottom, margin = 5.dp)
                            start.linkTo(parent.start, margin = 5.dp)
                        }
                ){
                    filmDetail.genres.forEach { genre ->
                        item {
                            GenreChip(genre = genre)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            {
                TabRow(
                    selectedTabIndex = tabIndex,
                    backgroundColor = DarkBlue
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
                    0 -> DescriptionScreen(description = filmDetail.overview)
                    1 -> CastScreen(castState = castState)
                }
            }

        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when{
            detailsState.isLoading -> CircularProgressIndicator()
            detailsState.error.isNotBlank() -> {
                Text(
                    text = detailsState.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
        }

    }
}

fun Double.roundToNextHalf(): Double {
    return (this * 2).toInt() / 2.0
}