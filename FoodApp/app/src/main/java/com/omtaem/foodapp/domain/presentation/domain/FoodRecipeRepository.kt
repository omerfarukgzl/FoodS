package com.omtaem.foodapp.domain.presentation.domain

interface FoodRecipeRepository {

    suspend fun getAllFood(queries: Map<String, String>): FoodRecipe
}