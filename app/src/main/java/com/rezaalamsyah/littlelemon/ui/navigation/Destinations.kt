package com.rezaalamsyah.littlelemon.ui.navigation

sealed interface Destinations{
    fun getRoute() : String

    data object Home : Destinations {
        override fun getRoute(): String {
            return "home"
        }
    }
    data object Profile : Destinations {
        override fun getRoute(): String {
            return "profile"
        }
    }
    data object OnBoarding : Destinations {
        override fun getRoute(): String {
            return "onBoarding"
        }
    }
}