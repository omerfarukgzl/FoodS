package com.omtaem.foodapp.domain.presentation.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.omtaem.foodapp.databinding.FragmentFavoriteFoodsBinding
import com.omtaem.foodapp.domain.presentation.favorite.adapter.FavoriteFoodsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class FavoriteFoodsFragment : Fragment() {

    private var _binding: FragmentFavoriteFoodsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val favoriteViewModel: FavoriteFoodsViewModel by viewModels()
    private val favoriteFoodsAdapter by lazy { FavoriteFoodsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteFoodsBinding.inflate(inflater, container, false)
        val view = binding.root

        setupRecyclerView()
        observeFavoriteRecipes()
        getFavoriteRecipes()
        swipeToDelete()

        return view
    }

    private fun collectEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            favoriteViewModel.productsEvent.collect { event ->
                when (event) {
                    is FoodsEvent.ShowUndoDeleteItemMessage -> {
                        Snackbar.make(
                            requireView(),
                            "Are you sure?",
                            Snackbar.LENGTH_LONG
                        ).setAction("Undo") {
                            if (favoriteViewModel.favoritesEntity.value?.isEmpty() == true) {
                                setupRecyclerView()
                            }
                            favoriteViewModel.onUndoDeleteClick(event.favoriteEntity)

                        }.show()
                    }
                }
            }
        }
    }

    private fun getFavoriteRecipes() {
        favoriteViewModel.getFavoriteNews()
    }

    private fun observeFavoriteRecipes() =
        favoriteViewModel.favoritesEntity.observe(viewLifecycleOwner) {
            binding.apply {
                if (it.isNullOrEmpty()) {
                    rvFavorite.visibility = View.GONE
                    tvErrorMessage.visibility = View.VISIBLE
                } else {
                    favoriteFoodsAdapter.favorites = it
                    tvErrorMessage.visibility = View.INVISIBLE
                    rvFavorite.visibility = View.VISIBLE
                }
                collectEvents()
            }
        }

    private fun swipeToDelete() {
        ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val favoriteArticle = favoriteFoodsAdapter.favorites[viewHolder.layoutPosition]
                favoriteViewModel.onItemSwiped(favoriteArticle)
            }
        }).attachToRecyclerView(binding.rvFavorite)
    }

    private fun setupRecyclerView() {
        binding.rvFavorite.adapter = favoriteFoodsAdapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}