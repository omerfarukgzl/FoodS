package com.omtaem.foodapp.domain.presentation.data.network.model

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)