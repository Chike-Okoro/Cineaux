package com.ceocoding.cineaux.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.ui.theme.AshColor
import com.ceocoding.cineaux.ui.theme.mooli_regular

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.search),
    shouldShowHint: Boolean = false,
    onFocusChanged: (FocusState) -> Unit
) {

    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    defaultKeyboardAction(ImeAction.Search)
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .padding(2.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = RoundedCornerShape(15.dp)
                )
                .background(AshColor)
                .fillMaxWidth()
                .padding(16.dp)
                .padding(end = 16.dp)
                .onFocusChanged { onFocusChanged(it) },
            textStyle = TextStyle(
                fontWeight = FontWeight.Normal,
                fontFamily = mooli_regular,
                fontSize = 14.sp,
                color = Color.White
            )
        )
        if (shouldShowHint){
            Text(
                text = hint,
                fontWeight = FontWeight.Light,
                color = Color.LightGray,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
            )
        }
        IconButton(
            onClick = onSearch,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.big_search_icon),
                contentDescription = hint,
                tint = Color.White
            )
        }
    }

}