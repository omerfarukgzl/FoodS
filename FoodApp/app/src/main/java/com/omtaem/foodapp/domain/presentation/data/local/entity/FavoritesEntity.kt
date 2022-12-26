package com.omtaem.foodapp.domain.presentation.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.omtaem.foodapp.domain.presentation.util.Contants.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
data class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)