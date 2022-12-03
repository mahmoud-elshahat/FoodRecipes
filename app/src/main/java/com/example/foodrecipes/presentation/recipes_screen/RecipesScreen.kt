package com.example.foodrecipes.presentation.recipes_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.foodrecipes.presentation.ErrorView
import com.example.foodrecipes.presentation.LoadingItem
import com.example.foodrecipes.presentation.LoadingView
import com.example.foodrecipes.presentation.recipes_screen.components.Header
import com.example.foodrecipes.presentation.recipes_screen.components.RecipeItem
import com.example.foodrecipes.presentation.utils.HttpStatusCode
import com.example.foodrecipes.presentation.utils.HttpStatusCode.getHttpCodeFromLoadError


@Composable
fun RecipesScreen(
    navController: NavController, viewModel: RecipesScreenViewModel = hiltViewModel()
) {
    val lazyRecipesItems = viewModel.recipes.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),

        ) {
        Header()
        LazyColumn {
            items(lazyRecipesItems.itemCount) { index ->
                lazyRecipesItems[index]?.let {
                    RecipeItem(it, navController)
                }
            }
            lazyRecipesItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = lazyRecipesItems.loadState.refresh as LoadState.Error

                        item {
                            ErrorView(message = HttpStatusCode.getMeaningfulMessage(
                                getHttpCodeFromLoadError(e.error.message.toString()),
                                LocalContext.current
                            ), onClickRetry = { retry() })
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = lazyRecipesItems.loadState.append as LoadState.Error
                        item {
                            ErrorView(message = HttpStatusCode.getMeaningfulMessage(
                                getHttpCodeFromLoadError(e.error.localizedMessage!!),
                                LocalContext.current
                            ), onClickRetry = { retry() })
                        }
                    }
                }
            }
        }
    }
}