package com.example.foodrecipes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodrecipes.presentation.recipes_details_screen.RecipesDetailsScreen
import com.example.foodrecipes.presentation.recipes_screen.RecipesScreen
import com.example.foodrecipes.presentation.theme.FoodAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.RecipesScreen.route
                ) {
                    composable(
                        route = Screen.RecipesScreen.route
                    ) {
                        RecipesScreen(navController)
                    }
                    composable(
                        route = Screen.RecipesDetailsScreen.route + "/{id}",
                        arguments = listOf(
                            navArgument("id") {
                                type = NavType.IntType
                                defaultValue = 1
                            }
                        )
                    ) {
                        val id = it.arguments?.getInt("id")
                        RecipesDetailsScreen(navController)
                    }
                }
            }
        }
    }
}


