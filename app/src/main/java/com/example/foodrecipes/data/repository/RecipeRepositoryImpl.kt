package com.example.foodrecipes.data.repository

import com.example.foodrecipes.data.dto.recipe_details.RecipeDetailsDto
import com.example.foodrecipes.data.dto.recipes_list.toRecipesResponse
import com.example.foodrecipes.data.local.RecipeDetailsDao
import com.example.foodrecipes.data.remote.FoodRecipesApi
import com.example.foodrecipes.domain.model.recipes_list.RecipesResponse
import com.example.foodrecipes.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: FoodRecipesApi,
    private val recipeDetailsDao: RecipeDetailsDao
) : RecipeRepository {

    override suspend fun fetchRecipes(offset: Int): RecipesResponse {
        return api.getRandomRecipes(offset).toRecipesResponse()
//        return RecipesResponseDto(20,offset, listOf(
//            RecipeDto(782601,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(715446,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(715415,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            RecipeDto(11,"Home","https://ichef.bbci.co.uk/food/ic/food_16x9_1600/recipes/british_shakshuka_26737_16x9.jpg"),
//            ),20).toRecipesResponse()
    }

    override suspend fun fetchRecipesDetails(id: Int): RecipeDetailsDto {
        return api.getRecipeDetails(id)
    }

    override suspend fun getRecipeDetails(id: Int): RecipeDetailsDto? {
        return recipeDetailsDao.getRecipeDetails(id)
    }

    override suspend fun cacheRecipeDetails(recipeDetails: RecipeDetailsDto) {
        recipeDetailsDao.cacheRecipeDetails(recipeDetails)
    }
}