package com.omtaem.foodapp.domain.presentation.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtaem.foodapp.data.local.entity.FavoritesEntity
import com.omtaem.foodapp.domain.presentation.data.local.entity.FavoritesEntity
import com.omtaem.foodapp.domain.presentation.domain.FavoriteRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteFoodsViewModel @Inject constructor(
    private val recipeRepository: FavoriteRecipeRepository
) : ViewModel() {

    var favoritesEntity: MutableLiveData<List<FavoritesEntity>> = MutableLiveData()

    fun getFavoriteNews() = viewModelScope.launch {
        recipeRepository.readFavoriteRecipes().collect {
            favoritesEntity.value = it
        }
    }

    private val newsEventChannel = Channel<FoodsEvent>()
    val productsEvent = newsEventChannel.receiveAsFlow()

    fun onItemSwiped(favoriteEntity: FavoritesEntity) = viewModelScope.launch {
        recipeRepository.deleteFavoriteRecipe(favoriteEntity.result)
        newsEventChannel.send(FoodsEvent.ShowUndoDeleteItemMessage(favoriteEntity))
    }

    fun onUndoDeleteClick(favoriteEntity: FavoritesEntity) = viewModelScope.launch {
        recipeRepository.insertFavoriteRecipe(favoriteEntity)
    }
}