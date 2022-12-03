package com.example.foodrecipes.domain.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.foodrecipes.data.dto.recipe_details.toRecipeDetails
import com.example.foodrecipes.domain.model.recipe_details.RecipeDetails
import com.example.foodrecipes.domain.model.recipes_list.Recipe
import com.example.foodrecipes.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val repository: RecipeRepository
) :
    PagingSource<Int, Recipe>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Recipe> {
        return try {
            val nextPage = params.key ?: 1
            val recipesResponse = repository.fetchRecipes(nextPage)
            LoadResult.Page(
                data = recipesResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = recipesResponse.offset.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    suspend fun getRecipeDetails(id: Int): RecipeDetails {
        var recipeDetails = repository.getRecipeDetails(id)
        if (recipeDetails == null) {
            recipeDetails = repository.fetchRecipesDetails(id)
            repository.cacheRecipeDetails(recipeDetails)
        }
        return recipeDetails.toRecipeDetails()
    }

    override fun getRefreshKey(state: PagingState<Int, Recipe>): Int? {
        TODO("Not yet implemented")
    }
}