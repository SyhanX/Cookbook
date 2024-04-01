package com.syhan.cookbook.feature_recipes.presentation.recipes.state

data class RecipeListState(
    val recipeList: List<RecipeCardState> = emptyList()
)