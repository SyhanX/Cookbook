package com.syhan.cookbook.feature_recipes.data.repository

import com.syhan.cookbook.feature_recipes.data.remote.RecipeApi
import com.syhan.cookbook.feature_recipes.domain.model.Recipe
import com.syhan.cookbook.feature_recipes.domain.model.RecipeList
import com.syhan.cookbook.feature_recipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class RecipeRepositoryImpl(
    private val api: RecipeApi
) : RecipeRepository {
    override suspend fun getRecipeById(id: Int): Recipe? = api.getRecipeById(id)
    override suspend fun getAllRecipes(): Flow<RecipeList> = api.getAllRecipes()
}