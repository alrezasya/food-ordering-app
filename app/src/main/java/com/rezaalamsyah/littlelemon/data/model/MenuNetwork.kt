package com.rezaalamsyah.littlelemon.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("image")
    val image: String = "",
    @SerialName("price")
    val price: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("title")
    val title: String = "",
    @SerialName("category")
    val category: String = ""
) {
    fun toDb() = MenuItemDb(
        image = image,
        price = price,
        description = description,
        id = id,
        title = title,
        category = category
    )
}