package com.rezaalamsyah.littlelemon.ui.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.rezaalamsyah.littlelemon.R
import com.rezaalamsyah.littlelemon.data.model.MenuItemDb
import com.rezaalamsyah.littlelemon.ui.composables.EmptyState
import com.rezaalamsyah.littlelemon.ui.composables.HeroPanel
import com.rezaalamsyah.littlelemon.ui.composables.MenuCategory
import com.rezaalamsyah.littlelemon.ui.composables.NavBar
import com.rezaalamsyah.littlelemon.ui.composables.NoItemFoundMsg
import com.rezaalamsyah.littlelemon.ui.navigation.Destinations
import com.rezaalamsyah.littlelemon.utils.ApiResult
import com.rezaalamsyah.littlelemon.utils.theme.ColorPalette
import com.rezaalamsyah.littlelemon.viewmodel.HomeViewModel

@Composable
fun Home(navController: NavController, viewModel: HomeViewModel = viewModel()) {
    val result = viewModel
        .menuData
        .collectAsStateWithLifecycle()
        .value

    HomeScreenUI(result, viewModel::getData) {
        navController.navigate(Destinations.Profile.getRoute())
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    result: ApiResult<List<MenuItemDb>>,
    onRetry: () -> Unit,
    navigateToProfile: () -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item(
            key = "header"
        ) {
            Column {
                NavBar(navigateToProfile)
                Spacer(modifier = Modifier.height(8.dp))
                HeroPanel {
                    query = it
                }
            }
        }
        stickyHeader(
            key = "sticky_header"
        ) {
            MenuCategory(selectedCategory) {
                selectedCategory = it
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        if (result is ApiResult.Success) {
            val filteredItem =
                if (query.isNotBlank() || selectedCategory != null) {
                    result.data
                        .filter {
                            //if query is blank then don't filter by query
                            (query.isBlank() || it.title.contains(
                                other = query,
                                ignoreCase = true
                            )) &&
                                    //if no category is selected then don't filter by category
                                    (selectedCategory == null || it.category.contains(
                                        other = selectedCategory!!,
                                        ignoreCase = true
                                    ))
                        }
                } else {
                    result.data
                }
            if (filteredItem.isNotEmpty()) {
                items(
                    items = filteredItem,
                    key = { item -> item.id }
                ) {
                    MenuItem(item = it)
                }
            } else {
                item(key = "no_item_found_msg") {
                    NoItemFoundMsg()
                }
            }
        } else if (result is ApiResult.Loading) {
            item(key = "loading_state") {
                LoadingView()
            }
        } else {
            item(key = "empty_state") {
                EmptyState(onRetry)
            }
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(50.dp),
            color = ColorPalette.primary1,
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItemDb) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .width(0.dp)
            ) {
                Text(
                    text = item.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(top = 8.dp, end = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = stringResource(id = R.string.menu_item_price, item.price),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            GlideImage(
                model = item.image,
                contentDescription = "Food Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .height(1.dp),
            color = Color.LightGray
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val item = MenuItemDb(
        image = "",
        price = "12.99",
        description = "The famous greek salad of crispy lettuce, peppers, olives and our Chicago",
        title = "Greek Salad",
        category = "Starters"
    )

    HomeScreenUI(
        ApiResult.Success(listOf(item)), {}
    ) {}
}