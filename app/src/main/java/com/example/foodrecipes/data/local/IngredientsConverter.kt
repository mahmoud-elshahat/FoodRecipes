package com.example.foodrecipes.data.local

import androidx.room.TypeConverter
import com.example.foodrecipes.data.dto.recipe_details.IngredientDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class IngredientsConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromIngredientsList(ingredients: List<IngredientDto?>?): String? {
        return ingredients?.let {
            val type: Type = object : TypeToken<List<IngredientDto?>?>() {}.type
            return gson.toJson(ingredients, type)
        }
    }

    @TypeConverter
    fun toIngredientsList(ingredientsString: String?): List<IngredientDto>? {
        return ingredientsString?.let {
            val type: Type = object : TypeToken<List<IngredientDto?>?>() {}.type
            return gson.fromJson<List<IngredientDto>>(ingredientsString, type)
        }
    }
}