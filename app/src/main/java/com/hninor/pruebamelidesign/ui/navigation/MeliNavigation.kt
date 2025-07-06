package com.hninor.pruebamelidesign.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hninor.pruebamelidesign.ui.search.SearchScreen
import com.hninor.pruebamelidesign.ui.product.ProductDetailScreen
import com.hninor.pruebamelidesign.ui.settings.SettingsScreen
import com.hninor.pruebamelidesign.ui.home.HomeScreen

@Composable
fun MeliNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen()
        }
        composable("search") {
            SearchScreen(navController = navController)
        }
        
        composable("product/{productId}") {
            ProductDetailScreen(navController = navController)
        }
        
        composable("settings") {
            SettingsScreen(navController = navController)
        }
    }
} 