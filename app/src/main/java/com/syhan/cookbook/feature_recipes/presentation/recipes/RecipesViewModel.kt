package com.syhan.cookbook.feature_recipes.presentation.recipes

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syhan.cookbook.common.presentation.state.NetworkResponse
import com.syhan.cookbook.feature_recipes.domain.usecase.RecipeUseCases
import com.syhan.cookbook.feature_recipes.presentation.recipes.state.RecipeCardState
import com.syhan.cookbook.feature_recipes.presentation.recipes.state.RecipeListState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

private const val TAG = "recipesvm"

class RecipesViewModel(
    private val useCases: RecipeUseCases
) : ViewModel() {

    private val _listState = MutableStateFlow(RecipeListState())
    val listState = _listState.asStateFlow()

    private val _networkState = MutableStateFlow<NetworkResponse>(NetworkResponse.Loading)
    val networkState = _networkState.asStateFlow()

    private var recipesJob: Job? = null

    init {
        getAllRecipes()
    }

    fun getAllRecipes() {
        recipesJob?.cancel()
        recipesJob = viewModelScope.launch(Dispatchers.IO) {
            val response = try {
                _networkState.emit(NetworkResponse.Loading)
                useCases.getAllRecipes()
            } catch (e: IOException) {
                _networkState.emit(NetworkResponse.InternetConnectionError)
                Log.d(TAG, "Failed to retrieve data, your internet probably sucks")
                return@launch
            } catch (e: HttpException) {
                _networkState.emit(NetworkResponse.UnexpectedError)
                Log.d(TAG, "Failed to retrieve data because of reasons")
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                _networkState.emit(NetworkResponse.Success)
                _listState.value = listState.value.copy(
                    recipeList = response.body()!!.recipes.map { recipe ->
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
                Log.d(TAG, "Retrieved data successfully")
            }
        }
    }
}