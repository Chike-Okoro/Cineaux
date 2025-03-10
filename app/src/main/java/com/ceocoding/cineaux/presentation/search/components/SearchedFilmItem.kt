package com.ceocoding.cineaux.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.domain.model.FilmData
import com.ceocoding.cineaux.presentation.details.components.roundToNextHalf
import com.ceocoding.cineaux.ui.theme.AshColor
import com.ceocoding.cineaux.ui.theme.LightOrange
import com.ceocoding.cineaux.util.Constants

@Composable
fun SearchedFilmItem(
    filmData: FilmData,
    modifier: Modifier = Modifier,
    onClick: (FilmData) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .padding(8.dp)
            .clickable { onClick(filmData) }
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.padding(end = 8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(Constants.BASE_BACKDROP_IMAGE_URL + filmData.backdropPath)
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
                text = filmData.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.h5
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
                val originalDouble = filmData.voteAverage
                val formattedDouble = String.format("%.1f", if (originalDouble % 1 == 0.0) originalDouble else originalDouble.roundToNextHalf())
                Text(
                    text = formattedDouble,
                    color = LightOrange,
                    style = MaterialTheme.typography.h5
                )
            }
            Text(text = filmData.releaseDate, style = MaterialTheme.typography.h5)
            LazyRow(modifier = Modifier.fillMaxWidth()){
                filmData.genres.forEach { genre ->
                    item {
                        Box(
                            modifier = Modifier
                                .padding(end = 9.dp)
                                .widthIn(min = 80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(AshColor)
                                .padding(vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = genre.type,
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Thin,
                                modifier = Modifier.padding(horizontal = 2.dp),
                                style = MaterialTheme.typography.h6
                            )
                        }
                    }
                }
            }
        }
    }
}