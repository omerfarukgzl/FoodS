package com.omtaem.foodapp.domain.presentation.di


import com.omtaem.foodapp.domain.presentation.data.FavoriteRecipeRepositoryImpl
import com.omtaem.foodapp.domain.presentation.data.FoodRecipeRepositoryImpl
import com.omtaem.foodapp.domain.presentation.domain.FavoriteRecipeRepository
import com.omtaem.foodapp.domain.presentation.domain.FoodRecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideRecipesRepository(
        recipesRepositoryImpl: FoodRecipeRepositoryImpl
    ): FoodRecipeRepository

    @Binds
    @Singleton
    abstract fun bindFavoriteRecipesRepository(
        favoriteRecipesRepositoryImpl: FavoriteRecipeRepositoryImpl
    ): FavoriteRecipeRepository
}