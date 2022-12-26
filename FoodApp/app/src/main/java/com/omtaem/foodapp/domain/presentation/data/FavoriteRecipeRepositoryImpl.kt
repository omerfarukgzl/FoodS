package com.omtaem.foodapp.domain.presentation.data

import com.omtaem.foodapp.domain.presentation.data.local.RecipesDao
import com.omtaem.foodapp.domain.presentation.data.local.entity.FavoritesEntity
import com.omtaem.foodapp.domain.presentation.domain.FavoriteRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRecipeRepositoryImpl @Inject constructor(
    private val recipesDao: RecipesDao
) : FavoriteRecipeRepository {

    override fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    override suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity = favoritesEntity)
    }

    override suspend fun deleteFavoriteRecipe(result: com.omtaem.foodapp.domain.presentation.data.network.model.Result) {
        recipesDao.deleteFavoriteRecipe(result = result)
    }

    override suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }
}