package com.example.foodrecipes.data.di

import android.content.Context
import androidx.room.Room
import com.example.foodrecipes.data.local.RecipeDetailsDao
import com.example.foodrecipes.data.local.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Provides
    @Singleton
    fun provideRecipeDatabase(@ApplicationContext context: Context): RecipesDatabase {
        return Room.databaseBuilder(
            context,
            RecipesDatabase::class.java,
            "Food"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRecipeDao(recipesDatabase: RecipesDatabase): RecipeDetailsDao {
        return recipesDatabase.recipeDetailsDao()
    }
}