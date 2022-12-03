package com.example.foodrecipes.domain.repository

import com.example.foodrecipes.data.dto.recipe_details.RecipeDetailsDto
import com.example.foodrecipes.domain.model.recipes_list.RecipesResponse

interface RecipeRepository {
    suspend fun fetchRecipes(offset: Int): RecipesResponse

    suspend fun fetchRecipesDetails(id: Int): RecipeDetailsDto

    suspend fun getRecipeDetails(id: Int): RecipeDetailsDto?

    suspend fun cacheRecipeDetails(recipeDetails: RecipeDetailsDto)
}