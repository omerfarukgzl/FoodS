package com.omtaem.foodapp.domain.presentation.data.local
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.omtaem.foodapp.domain.presentation.data.local.entity.FavoritesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    /** For Favorite Recipes **/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    @Query("SELECT * FROM favorites_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    @Query("DELETE FROM favorites_recipes_table WHERE result=:result")
    suspend fun deleteFavoriteRecipe(result: com.omtaem.foodapp.domain.presentation.data.network.model.Result)

    @Query("DELETE FROM favorites_recipes_table")
    suspend fun deleteAllFavoriteRecipes()
}