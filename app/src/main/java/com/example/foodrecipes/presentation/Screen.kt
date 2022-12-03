package com.example.foodrecipes.presentation

sealed class Screen(val route: String) {
    object RecipesScreen : Screen("recipes_screen")
    object RecipesDetailsScreen : Screen("recipes_details_screen")

    fun withId(id: Int): String {
        return buildString {
            append(route)
            append("/$id")
        }
    }
}