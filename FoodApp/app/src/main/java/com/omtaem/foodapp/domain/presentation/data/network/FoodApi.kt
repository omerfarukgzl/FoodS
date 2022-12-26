package com.omtaem.foodapp.domain.presentation.data.network

interface FoodApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): FoodRecipe
}