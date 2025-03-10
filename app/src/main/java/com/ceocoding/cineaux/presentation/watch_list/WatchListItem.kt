package com.ceocoding.cineaux.presentation.watch_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.domain.model.FilmDetail
import com.ceocoding.cineaux.navigation.RootScreen
import com.ceocoding.cineaux.presentation.details.components.roundToNextHalf
import com.ceocoding.cineaux.ui.theme.LightOrange
import com.ceocoding.cineaux.util.Constants

@Composable
fun WatchListItem(
    filmDetail: FilmDetail,
    modifier: Modifier = Modifier,
    parentNavController: NavHostController
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .padding(8.dp)
            .clickable {parentNavController.navigate(RootScreen.DetailsScreen.route + "/${filmDetail.id}")},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(Constants.BASE_BACKDROP_IMAGE_URL + filmDetail.backdropPath)
                    .crossfade(true)
                    .error(R.drawable.image_unavailable)
                    .fallback(R.drawable.image_unavailable)
                    .build(),
                contentDescription = "ImageCard",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 95.dp, height = 120.dp)
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = filmDetail.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(1.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
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
            Text(text = filmDetail.releaseDate)
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDismissItem(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    swippable: @Composable () -> Unit
) {
    val dismissState = rememberDismissState(initialValue = DismissValue.Default,
        confirmStateChange = {
            if(it == DismissValue.DismissedToStart){
                onDismiss()
            }
            true
        }
    )

    SwipeToDismiss(
        state = dismissState,
        modifier = modifier,
        background = {
            if(dismissState.dismissDirection == DismissDirection.EndToStart){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                        .padding(8.dp)
                ) {
                    Column(modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            modifier = Modifier.align(CenterHorizontally),
                            tint = Color(0xFFFF6F6F)
                        )
                    }

                }
            }

        },
        dismissContent = {
            swippable()
        },
        directions = setOf(DismissDirection.EndToStart)
    )

}