package com.syhan.cookbook.feature_recipes.domain.repository

import com.syhan.cookbook.feature_recipes.domain.model.Recipe
import com.syhan.cookbook.feature_recipes.domain.model.RecipeList
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getRecipeById(id: Int): Recipe?
    suspend fun getAllRecipes(): Flow<RecipeList>
}