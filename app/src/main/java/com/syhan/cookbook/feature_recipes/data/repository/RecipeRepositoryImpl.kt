package com.syhan.cookbook.feature_recipes.data.repository

import com.syhan.cookbook.feature_recipes.data.remote.RecipeApi
import com.syhan.cookbook.feature_recipes.domain.repository.RecipeRepository

class RecipeRepositoryImpl(
    private val api: RecipeApi
) : RecipeRepository {
    override suspend fun getRecipeById(id: Int) = api.getRecipeById(id)
    override suspend fun getAllRecipes() = api.getAllRecipes()
}