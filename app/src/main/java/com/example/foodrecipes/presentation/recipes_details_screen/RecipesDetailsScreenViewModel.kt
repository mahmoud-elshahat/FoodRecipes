package com.example.foodrecipes.presentation.recipes_details_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodrecipes.domain.usecase.GetRecipesUseCase
import com.example.foodrecipes.presentation.RecipeDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesDetailsScreenViewModel @Inject constructor(
    private val recipesUseCase: GetRecipesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeDetailsUiState>(RecipeDetailsUiState.Loading)
    val uiState: StateFlow<RecipeDetailsUiState> = _uiState

    fun getRecipeDetails(id: Int) {
        _uiState.value = RecipeDetailsUiState.Loading
        viewModelScope.launch {
            try {
                _uiState.value = RecipeDetailsUiState.Loaded(recipesUseCase.getRecipeDetails(id))
            } catch (exception: Exception) {
                _uiState.value = RecipeDetailsUiState.Error(exception.message.toString())
            }
        }
    }
}