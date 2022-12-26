package com.omtaem.foodapp.domain.presentation.data.network.model

import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)