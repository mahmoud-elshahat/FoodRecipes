package com.example.foodrecipes.presentation.recipes_details_screen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.foodrecipes.domain.model.recipe_details.RecipeDetails
import com.example.foodrecipes.presentation.ErrorView
import com.example.foodrecipes.presentation.LoadingView
import com.example.foodrecipes.presentation.RecipeDetailsUiState
import com.example.foodrecipes.presentation.recipes_details_screen.components.*
import com.example.foodrecipes.presentation.utils.HttpStatusCode

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun RecipesDetailsScreen(
    navController: NavController,
    id: Int,
    viewModel: RecipesDetailsScreenViewModel = hiltViewModel(),
) {
    LaunchedEffect(true) {
        viewModel.getRecipeDetails(id)
    }
    when (val recipeDetailsState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is RecipeDetailsUiState.Loading -> LoadingView()
        is RecipeDetailsUiState.Error -> ErrorView(message = HttpStatusCode.getMeaningfulMessage(
            recipeDetailsState.message, LocalContext.current
        ), onClickRetry = { })
        is RecipeDetailsUiState.Loaded -> RecipeDetails(
            recipeDetails = recipeDetailsState.data, navController = navController
        )
        else -> {}
    }
}

@Composable
fun RecipeDetails(navController: NavController, recipeDetails: RecipeDetails) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Navbar(navController)
        Column(
            modifier = Modifier
                .scrollable(rememberScrollState(), Orientation.Horizontal)
                .weight(weight = 1f, fill = false)
                .fillMaxSize()
        ) {
            DetailsHeader(recipeDetails)
            DetailsItems(recipeDetails)
            DishTypes(recipeDetails.dishTypes)
            Ingredients(recipeDetails.ingredients)
        }
    }
}