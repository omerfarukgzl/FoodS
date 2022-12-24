package com.omtaem.foodapp.domain.presentation.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.omtaem.foodapp.databinding.FragmentHomeBinding
import com.omtaem.foodapp.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.omtaem.foodapp.R

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val recipesAdapter by lazy { HomeRecyclerAdapter(::onFoodClick) }
    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setupRecyclerView()
        observeRecipesLiveData()
        onSwipeRefresh()
        auth = Firebase.auth // initialize Firebase auth
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_log_out -> {
                showAlertDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Confirm deletion alert dialog
    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.log_out_question)
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                auth.signOut()
                Toast.makeText(
                    requireContext(),
                    R.string.logged_out_successfully,
                    Toast.LENGTH_LONG
                ).show()
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }.setNegativeButton(getString(R.string.no)) { _, _ ->

            }
            .show()
    }

    private fun onSwipeRefresh() {
        with(binding) {
            swiperefresh.setOnRefreshListener {
                //observeRecipesLiveData()
                swiperefresh.isRefreshing = false
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding) {
            rvRecipes.adapter = recipesAdapter
        }
    }

    private fun observeRecipesLiveData() = lifecycleScope.launch {
        viewModel.getAllRecipe(viewModel.applyQueries())
        viewModel.recipesResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    binding.pbNews.visibility = View.VISIBLE
                }
                is NetworkResult.Success -> {
                    binding.pbNews.visibility = View.INVISIBLE
                    recipesAdapter.setData(it.data!!)
                }
                is NetworkResult.Error -> {
                    binding.pbNews.visibility = View.INVISIBLE
                }
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onFoodClick(result: com.omtaem.foodapp.data.network.model.Result) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(result)
        findNavController().navigate(action)
    }
}