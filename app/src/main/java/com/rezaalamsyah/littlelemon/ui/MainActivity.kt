package com.rezaalamsyah.littlelemon.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.rezaalamsyah.littlelemon.data.local.PrefsRepository
import com.rezaalamsyah.littlelemon.ui.navigation.Navigation
import androidx.compose.ui.Modifier
import com.rezaalamsyah.littlelemon.utils.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferenceRepository =
            PrefsRepository.getPrefsRepository(this.applicationContext)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navHostController = rememberNavController()
                    Navigation(navHostController = navHostController, preferenceRepository)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navHostController = rememberNavController()
            Navigation(
                navHostController = navHostController,
                PrefsRepository.getPrefsRepository(LocalContext.current)
            )
        }
    }
}