package com.syhan.cookbook.feature_recipes.domain.usecase

import com.syhan.cookbook.feature_recipes.domain.model.RecipeList
import com.syhan.cookbook.feature_recipes.domain.repository.RecipeRepository
import retrofit2.Response

class GetAllRecipes(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): Response<RecipeList>{
        return repository.getAllRecipes()
    }
}