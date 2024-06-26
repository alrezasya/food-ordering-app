package com.rezaalamsyah.littlelemon.data.network

import com.rezaalamsyah.littlelemon.data.model.MenuNetwork
import com.rezaalamsyah.littlelemon.data.network.ApiClient.client
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AppApi(
    private val httpClient: HttpClient = client
) {

    private companion object {
        const val MENU_PATH =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    }

    suspend fun getMenuData(): MenuNetwork {
        return httpClient
            .get(MENU_PATH).body()
    }
}