package com.syhan.cookbook.feature_recipes.presentation.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syhan.cookbook.R
import com.syhan.cookbook.common.domain.util.observeWithLifecycle
import com.syhan.cookbook.common.presentation.state.NetworkResponse
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

        binding.retryButton.setOnClickListener {
            viewModel.getAllRecipes()
        }

        observeNetworkState()
        setUpRecyclerView()
        submitData()

    }

    private fun observeNetworkState() {
        viewModel.networkState.observeWithLifecycle(this) { response ->
            binding.apply {
                when (response) {
                    is NetworkResponse.Loading -> {
                        loadingSpinner.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                        errorMessage.visibility = View.GONE
                    }

                    is NetworkResponse.Success -> {
                        loadingSpinner.visibility = View.GONE
                        recyclerView.visibility = View.VISIBLE
                        errorMessage.visibility = View.GONE
                    }

                    is NetworkResponse.InternetConnectionError -> {
                        loadingSpinner.visibility = View.GONE
                        errorMessage.visibility = View.VISIBLE
                        errorText.setText(R.string.error_no_internet)
                    }

                    is NetworkResponse.UnexpectedError -> {
                        loadingSpinner.visibility = View.GONE
                        errorMessage.visibility = View.VISIBLE
                        errorText.setText(R.string.error_unexpected)
                    }
                }
            }
        }
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