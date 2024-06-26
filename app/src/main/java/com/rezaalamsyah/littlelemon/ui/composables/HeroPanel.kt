package com.rezaalamsyah.littlelemon.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.rezaalamsyah.littlelemon.R
import com.rezaalamsyah.littlelemon.utils.theme.ColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnsafeOptInUsageError")
@Composable
fun HeroPanel(onSearch: (String) -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(color = ColorPalette.primary1)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_hero_section_title),
            style = MaterialTheme.typography.displayLarge,
            color = ColorPalette.primary2,
        )
        Text(
            text = stringResource(R.string.home_hero_section_subtitle),
            style = MaterialTheme.typography.displaySmall,
            color = ColorPalette.highlight1,
            modifier = Modifier
                .offset(y = (-15).dp)
                .padding(top = 16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(1f)
                    .width(0.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.home_hero_section_about),
                style = MaterialTheme.typography.bodyLarge,
                color = ColorPalette.highlight1
            )
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp)
                    .clip(MaterialTheme.shapes.small),
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "hero image",
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = query,
            onValueChange = {
                query = it
                if (it.isBlank())
                    onSearch(it)
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.home_search_bar_hint),
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true,
            leadingIcon = {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    tint = Color.Black
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch(query) }
            ),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.LightGray,
                cursorColor = ColorPalette.primary1
            )
        )
    }
}