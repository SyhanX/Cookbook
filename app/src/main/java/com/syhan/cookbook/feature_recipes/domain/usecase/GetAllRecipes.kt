package com.syhan.cookbook.feature_recipes.domain.usecase

import com.syhan.cookbook.feature_recipes.domain.model.RecipeList
import com.syhan.cookbook.feature_recipes.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecipes(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(): Flow<RecipeList>{
        return repository.getAllRecipes()
    }
}