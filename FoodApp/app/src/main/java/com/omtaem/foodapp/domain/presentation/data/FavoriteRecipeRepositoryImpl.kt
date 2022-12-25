package com.omtaem.foodapp.domain.presentation.data

import com.omtaem.foodapp.domain.presentation.domain.FavoriteRecipeRepository
import kotlinx.coroutines.flow.Flow

class FavoriteRecipeRepositoryImpl @Inject constructor(
    private val recipesDao: RecipesDao
) : FavoriteRecipeRepository {

    override fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDao.readFavoriteRecipes()
    }

    override suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDao.insertFavoriteRecipe(favoritesEntity = favoritesEntity)
    }

    override suspend fun deleteFavoriteRecipe(result: com.omtaem.foodapp.data.network.model.Result) {
        recipesDao.deleteFavoriteRecipe(result = result)
    }

    override suspend fun deleteAllFavoriteRecipes() {
        recipesDao.deleteAllFavoriteRecipes()
    }
}