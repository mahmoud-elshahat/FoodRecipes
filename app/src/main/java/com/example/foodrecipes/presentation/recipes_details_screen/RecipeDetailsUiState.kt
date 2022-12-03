package com.example.foodrecipes.presentation

import com.example.foodrecipes.domain.model.recipe_details.RecipeDetails

sealed class RecipeDetailsUiState {
    object Loading : RecipeDetailsUiState()
    class Loaded(val data: RecipeDetails) : RecipeDetailsUiState()
    class Error(val message: String) : RecipeDetailsUiState()
}