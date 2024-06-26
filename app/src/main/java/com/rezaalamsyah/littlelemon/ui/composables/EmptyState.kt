package com.rezaalamsyah.littlelemon.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rezaalamsyah.littlelemon.R
import com.rezaalamsyah.littlelemon.utils.theme.ColorPalette

@Composable
fun EmptyState(onFailure: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.menu_item_loading_error_msg),
            style = MaterialTheme
                .typography.bodyMedium
        )
        Button(
            onClick = { onFailure() },
            modifier = Modifier
                .padding(0.dp, 16.dp),
            colors = ButtonDefaults.filledTonalButtonColors(containerColor = ColorPalette.primary2),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = stringResource(R.string.menu_item_reload_btn_txt))
        }
    }
}