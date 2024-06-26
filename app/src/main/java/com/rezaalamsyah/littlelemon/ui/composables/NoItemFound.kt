package com.rezaalamsyah.littlelemon.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rezaalamsyah.littlelemon.R

@Composable
fun NoItemFoundMsg() {
    Text(
        text = stringResource(R.string.no_menu_item_found_msg),
        style = MaterialTheme
            .typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(16.dp)
    )
}