package com.rezaalamsyah.littlelemon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rezaalamsyah.littlelemon.data.local.PrefsRepository
import com.rezaalamsyah.littlelemon.ui.screens.Home
import com.rezaalamsyah.littlelemon.ui.screens.OnBoarding
import com.rezaalamsyah.littlelemon.ui.screens.Profile

@Composable
fun Navigation(navHostController: NavHostController, prefsRepository: PrefsRepository) {
    NavHost(
        navController = navHostController,
        startDestination =
        if (prefsRepository.isUserLoggedIn())
            Destinations.Home.getRoute()
        else
            Destinations.OnBoarding.getRoute()
    ) {
        composable(route = Destinations.OnBoarding.getRoute()) {
            OnBoarding(navHostController)
        }
        composable(route = Destinations.Home.getRoute()) {
            Home(navHostController)
        }
        composable(route = Destinations.Profile.getRoute()) {
            Profile(navHostController)
        }
    }
}