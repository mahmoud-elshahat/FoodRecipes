package com.example.foodrecipes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodrecipes.data.dto.recipe_details.RecipeDetailsDto

@Dao
interface RecipeDetailsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun cacheRecipeDetails(recipeDetailsDto: RecipeDetailsDto)

    @Query("SELECT * FROM recipes_details WHERE id=:id ")
    suspend fun getRecipeDetails(id: Int): RecipeDetailsDto?
}