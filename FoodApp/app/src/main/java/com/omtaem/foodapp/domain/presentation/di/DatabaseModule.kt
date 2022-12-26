package com.omtaem.foodapp.domain.presentation.di


import android.content.Context
import androidx.room.Room
import com.omtaem.foodapp.domain.presentation.data.local.RecipesDatabase
import com.omtaem.foodapp.domain.presentation.util.Contants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRecipesDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideRecipesDao(database: RecipesDatabase) = database.recipesDao()
}