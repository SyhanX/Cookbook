package com.syhan.cookbook.feature_recipes.data.remote

import com.syhan.cookbook.feature_recipes.domain.model.Recipe
import com.syhan.cookbook.feature_recipes.domain.model.RecipeList
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {
    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: Int): Recipe?

    @GET("recipes")
    suspend fun getAllRecipes(): RecipeList
}