package com.rezaalamsyah.littlelemon.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rezaalamsyah.littlelemon.R
import com.rezaalamsyah.littlelemon.utils.theme.ColorPalette

@Composable
fun MenuCategory(
    selectedCategory: String?,
    onCategorySelectionChange: (selectedCategory: String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_menu_break_down_title),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(start = 16.dp)
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CategoryItem(
                categoryName = stringResource(R.string.starters_category_label),
                selectedCategory,
                onCategorySelectionChange
            )
            CategoryItem(
                categoryName = stringResource(R.string.mains_category_label),
                selectedCategory,
                onCategorySelectionChange
            )
            CategoryItem(
                categoryName = stringResource(R.string.desserts_category_label),
                selectedCategory,
                onCategorySelectionChange
            )
            CategoryItem(
                categoryName = stringResource(R.string.slides_category_label),
                selectedCategory,
                onCategorySelectionChange
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(1.dp),
            color = ColorPalette.primary1
        )
    }
}

@Composable
fun CategoryItem(
    categoryName: String,
    selectedCategory: String?,
    onCheckedChange: (category: String?) -> Unit
) {
    Text(
        text = categoryName,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .background(
                color = if (categoryName == selectedCategory) ColorPalette.primary1 else ColorPalette.primary1.copy(
                    alpha = 0.2F
                ),
                shape = MaterialTheme.shapes.small
            )
            .padding(8.dp)
            .clickable {
                onCheckedChange(
                    if (selectedCategory == categoryName)
                        null
                    else categoryName
                )
            },
        color = if (categoryName == selectedCategory) ColorPalette.secondary2 else ColorPalette.primary1,
        style = MaterialTheme.typography.bodyMedium
    )
}