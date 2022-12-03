package com.example.foodrecipes.data.dto.recipe_details

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodrecipes.domain.model.recipe_details.RecipeDetails
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipes_details")
data class RecipeDetailsDto(
    @PrimaryKey val id: Int,
    val title: String,
    val image: String?,
    val aggregateLikes: Int,
    val creditsText: String,
    @SerializedName("extendedIngredients") val ingredients: List<IngredientDto>,
    val dishTypes: List<String>,
    val healthScore: Double,
    val instructions: String?,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularSourceUrl: String,
    val summary: String,
    val weightWatcherSmartPoints: Int,
)

fun RecipeDetailsDto.toRecipeDetails(): RecipeDetails {
    return RecipeDetails(
        id = id,
        title = title,
        aggregateLikes = aggregateLikes,
        dishTypes = dishTypes,
        instructions = instructions,
        pricePerServing = pricePerServing,
        readyInMinutes = readyInMinutes,
        servings = servings,
        image = image ?: "",
        ingredients = ingredients.map { ingredient -> ingredient.toIngredient() }
    )
}