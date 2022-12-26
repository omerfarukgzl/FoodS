package com.omtaem.foodapp.domain.presentation.data

import com.omtaem.foodapp.domain.presentation.data.network.FoodApi
import com.omtaem.foodapp.domain.presentation.data.network.model.FoodRecipe
import com.omtaem.foodapp.domain.presentation.domain.FoodRecipeRepository
import javax.inject.Inject

class FoodRecipeRepositoryImpl @Inject constructor(
    private val api: FoodApi
): FoodRecipeRepository {
    override suspend fun getAllFood(queries: Map<String, String>): FoodRecipe {
        return api.getRecipes(queries)
    }
}