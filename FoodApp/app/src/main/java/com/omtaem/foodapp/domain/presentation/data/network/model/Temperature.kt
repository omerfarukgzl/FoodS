package com.omtaem.foodapp.domain.presentation.data.network.model

data class Temperature(
    @SerializedName("number")
    val number: Double,
    @SerializedName("unit")
    val unit: String
)