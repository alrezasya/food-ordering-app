package com.rezaalamsyah.littlelemon.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rezaalamsyah.littlelemon.R

@Composable
fun NavBar(navigateToProfile: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "little lemon logo",
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, start = 50.dp)
                .height(50.dp)
                .weight(1F, true)
        )

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "profile",
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .size(50.dp)
                .clickable {
                    navigateToProfile()
                }
        )
    }
}