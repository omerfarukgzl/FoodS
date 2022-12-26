package com.omtaem.foodapp.domain.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.omtaem.foodapp.databinding.ItemFoodBinding
import com.omtaem.foodapp.domain.presentation.data.network.model.FoodRecipe
import com.omtaem.foodapp.domain.presentation.util.RecipesDiffUtil


class HomeRecyclerAdapter(private val onFoodClick: ((result: Result) -> Unit)?) : RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder>() {

    private var recipes = emptyList<Result>()

    class MyViewHolder(private val binding: ItemFoodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result, onFoodClick: ((result: Result) -> Unit)?) {
            binding.result = result
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onFoodClick?.invoke(result)
            }
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemFoodBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder.from(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        holder.bind(result = currentRecipe, onFoodClick)
    }

    override fun getItemCount(): Int = recipes.size

    fun setData(newData: FoodRecipe) {
        val recipesDiffUtil = RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}