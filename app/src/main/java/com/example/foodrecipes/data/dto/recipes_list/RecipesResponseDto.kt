package com.example.foodrecipes.data.dto.recipes_list

import com.example.foodrecipes.domain.model.recipes_list.RecipesResponse

data class RecipesResponseDto(
    val number: Int,
    val offset: Int,
    val results: List<RecipeDto>,
    val totalResults: Int
)

fun RecipesResponseDto.toRecipesResponse(): RecipesResponse {
    return RecipesResponse(
        offset = offset,
        results = results.map { item -> item.toRecipe() },
    )
}