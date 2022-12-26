package com.omtaem.foodapp.domain.presentation.data.network

import com.omtaem.foodapp.domain.presentation.data.network.model.FoodRecipe
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodApi {

    @GET("/recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): FoodRecipe
}