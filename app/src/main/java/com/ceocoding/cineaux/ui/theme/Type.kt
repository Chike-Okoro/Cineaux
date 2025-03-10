package com.ceocoding.cineaux.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ceocoding.cineaux.R

// Set of Material typography styles to start with

val mooli_regular = FontFamily(
    Font(R.font.mooli_regular, FontWeight.Normal)
)
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = mooli_regular,
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp,
        color = Color.White
    ),
    h1 = TextStyle(
        fontFamily = mooli_regular,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.White
    ),
    h5 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = mooli_regular,
        fontSize = 16.sp,
        color = Color.White
    ),
    h6 = TextStyle(
        fontWeight = FontWeight.Normal,
        fontFamily = mooli_regular,
        fontSize = 14.sp,
        color = Color.White
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)