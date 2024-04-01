package com.syhan.cookbook.feature_recipes.presentation.recipes.state

data class RecipeCardState(
    val id: Int = 0,
    val name: String = "",
    val difficulty: String = "",
    val cookTime: String = "",
    val cuisine: String = ""
)
