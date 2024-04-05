package com.syhan.cookbook.feature_recipes.presentation.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhan.cookbook.common.presentation.state.NetworkResponse
import com.syhan.cookbook.feature_recipes.domain.usecase.RecipeUseCases
import com.syhan.cookbook.feature_recipes.presentation.recipes.state.RecipeCardState
import com.syhan.cookbook.feature_recipes.presentation.recipes.state.RecipeListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val TAG = "recipesvm"

class RecipesViewModel(
    private val useCases: RecipeUseCases
) : ViewModel() {

    private val _listState = MutableStateFlow(RecipeListState())
    val listState: StateFlow<RecipeListState>
        get() = _listState

    private val _responseState: MutableStateFlow<NetworkResponse> = MutableStateFlow(NetworkResponse.Loading)
    val responseState = _responseState.asStateFlow()


    private var getAllRecipesJob: Job? = null

    init {
        viewModelScope.launch {
            getAllRecipes()
        }
    }

    private suspend fun getAllRecipes() {
        getAllRecipesJob?.cancel()
        getAllRecipesJob = useCases.getAllRecipes()
            .onEach { recipeList ->
                _listState.value = listState.value.copy(
                    recipeList = recipeList.recipes.map { recipe ->
                        RecipeCardState(
                            id = recipe.id,
                            name = recipe.name,
                            cookTime = recipe.cookTimeMinutes.toString(),
                            difficulty = recipe.difficulty,
                            cuisine = recipe.cuisine,
                            image = recipe.image
                        )
                    }
                )
            }.launchIn(viewModelScope)
    }


}