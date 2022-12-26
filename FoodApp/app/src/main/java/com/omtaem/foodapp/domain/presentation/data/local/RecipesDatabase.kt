package com.omtaem.foodapp.domain.presentation.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.omtaem.foodapp.domain.presentation.data.RecipesTypeConverter
import com.omtaem.foodapp.domain.presentation.data.local.entity.FavoritesEntity

@Database(
    entities = [FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}