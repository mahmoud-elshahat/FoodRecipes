package com.example.foodrecipes.domain.di

import com.example.foodrecipes.data.local.RecipeDetailsDao
import com.example.foodrecipes.data.remote.FoodRecipesApi
import com.example.foodrecipes.data.repository.RecipeRepositoryImpl
import com.example.foodrecipes.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRecipeRepository(
        api: FoodRecipesApi, recipeDetailsDao: RecipeDetailsDao
    ): RecipeRepository {
        return RecipeRepositoryImpl(api, recipeDetailsDao)
    }
}