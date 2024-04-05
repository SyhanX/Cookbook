package com.syhan.cookbook.feature_recipes.presentation.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syhan.cookbook.databinding.RecipeCardBinding
import com.syhan.cookbook.feature_recipes.presentation.recipes.state.RecipeCardState

class RecipesAdapter() :
    ListAdapter<RecipeCardState, RecipesAdapter.RecipeCardViewHolder>(RecipesDiffCallback) {

    inner class RecipeCardViewHolder(val binding: RecipeCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeCardState) {
            binding.apply {
                recipeName.text = item.name
                recipeDifficulty.text = item.difficulty
                recipeCuisine.text = item.cuisine
                recipeCookingTime.text = item.cookTime
                Glide.with(recipeImage.context)
                    .load(item.image)
                    .into(recipeImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCardViewHolder {
        val view = RecipeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesAdapter.RecipeCardViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemViewType(position: Int): Int = position
}

private object RecipesDiffCallback : DiffUtil.ItemCallback<RecipeCardState>() {
    override fun areItemsTheSame(oldItem: RecipeCardState, newItem: RecipeCardState): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RecipeCardState, newItem: RecipeCardState): Boolean =
        oldItem.hashCode() == newItem.hashCode() && oldItem == newItem
}