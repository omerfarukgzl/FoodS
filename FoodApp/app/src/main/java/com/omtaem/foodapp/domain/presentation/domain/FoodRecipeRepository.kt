package com.omtaem.foodapp.domain.presentation.domain

import com.omtaem.foodapp.domain.presentation.data.network.model.FoodRecipe

interface FoodRecipeRepository {

    suspend fun getAllFood(queries: Map<String, String>): FoodRecipe
}