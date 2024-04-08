package com.syhan.cookbook.feature_recipes.domain.repository

import com.syhan.cookbook.feature_recipes.domain.model.Recipe
import com.syhan.cookbook.feature_recipes.domain.model.RecipeList
import retrofit2.Response

interface RecipeRepository {
    suspend fun getRecipeById(id: Int): Response<Recipe?>
    suspend fun getAllRecipes(): Response<RecipeList>
}