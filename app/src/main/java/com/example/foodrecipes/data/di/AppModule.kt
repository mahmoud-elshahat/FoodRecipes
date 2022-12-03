package com.example.foodrecipes.data.di

import android.util.Log
import com.example.foodrecipes.data.local.RecipeDetailsDao
import com.example.foodrecipes.data.remote.Constants.BASE_URL
import com.example.foodrecipes.data.remote.FoodRecipesApi
import com.example.foodrecipes.data.repository.RecipeRepositoryImpl
import com.example.foodrecipes.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val TAG = "API-LOGS"

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message: String ->
            Log.d(TAG, message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", "139544a9873a4d36ad95dc96aca3ecff").build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodRecipesApi(okHttpClient: OkHttpClient): FoodRecipesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodRecipesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        api: FoodRecipesApi,
        recipeDetailsDao: RecipeDetailsDao
    ): RecipeRepository {
        return RecipeRepositoryImpl(api, recipeDetailsDao)
    }
}