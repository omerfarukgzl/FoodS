package com.omtaem.foodapp.domain.presentation.domain

import com.omtaem.foodapp.domain.presentation.data.local.entity.FavoritesEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteRecipeRepository {

    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)
    suspend fun deleteFavoriteRecipe(result: com.omtaem.foodapp.domain.presentation.data.network.model.Result)
    suspend fun deleteAllFavoriteRecipes()
}