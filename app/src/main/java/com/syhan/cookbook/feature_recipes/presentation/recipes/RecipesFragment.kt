package com.syhan.cookbook.feature_recipes.presentation.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syhan.cookbook.common.domain.util.observeWithLifecycle
import com.syhan.cookbook.databinding.FragmentRecipesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "recipes_fragment"
class RecipesFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private val recipesAdapter = RecipesAdapter()

    private val viewModel by viewModel<RecipesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = binding.recyclerView
        setUpRecyclerView()
        submitData()

    }

    private fun submitData() {
        viewModel.listState.observeWithLifecycle(this) {
            recipesAdapter.submitList(it.recipeList)
        }
    }

    private fun setUpRecyclerView() {
        recyclerView.apply {
            adapter = recipesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}