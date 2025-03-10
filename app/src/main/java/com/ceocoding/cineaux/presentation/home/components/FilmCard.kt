package com.ceocoding.cineaux.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.data.remote.dto.FilmDataDto
import com.ceocoding.cineaux.domain.model.FilmData
import com.ceocoding.cineaux.util.Constants

@Composable
fun FilmCard(
    film: FilmData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            elevation = 5.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constants.BASE_BACKDROP_IMAGE_URL + film.backdropPath)
                    .crossfade(true)
                    .error(R.drawable.image_unavailable)
                    .fallback(R.drawable.image_unavailable)
                    .build(),
                contentDescription = "ImageCard",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 150.dp, height = 180.dp)
            )
        }
        Text(
            text = if(film.title.length > Constants.MAX_LENGTH)
                film.title.take(Constants.MAX_LENGTH) + "..."
            else film.title,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.h6
        )
    }

}