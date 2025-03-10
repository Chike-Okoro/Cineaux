package com.ceocoding.cineaux.presentation.details.components

import android.graphics.ImageDecoder
import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.domain.model.Cast
import com.ceocoding.cineaux.util.Constants

@Composable
fun CastItem(cast: Cast) {

    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(Constants.BASE_POSTER_IMAGE_URL + cast.profilePath)
                .crossfade(true)
                .error(R.drawable.ic_user)
                .fallback(R.drawable.ic_user)
                .build(),
            contentDescription = cast.name,
            modifier = Modifier
                .clip(CircleShape)
                .size(100.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = cast.name,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }


}