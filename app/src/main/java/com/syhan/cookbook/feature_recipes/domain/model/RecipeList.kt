package com.syhan.cookbook.feature_recipes.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeList(
    val recipes: List<Recipe>,
    val total: Int,
    val skip: Int,
    val limit: Int
)