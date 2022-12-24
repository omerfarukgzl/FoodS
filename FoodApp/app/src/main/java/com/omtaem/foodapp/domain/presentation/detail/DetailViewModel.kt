package com.omtaem.foodapp.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtaem.foodapp.data.local.entity.FavoritesEntity
import com.omtaem.foodapp.domain.FavoriteRecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.omtaem.foodapp.data.network.model.Result

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val favoriteRecipeRepository: FavoriteRecipeRepository
) : ViewModel() {

    var favLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        favLiveData.value = false
    }

    fun addFavoriteNews(favoritesEntity: FavoritesEntity) = viewModelScope.launch {
        favoriteRecipeRepository.insertFavoriteRecipe(favoritesEntity = favoritesEntity)
    }

    fun deleteFavoriteNews(result: Result) = viewModelScope.launch {
        favoriteRecipeRepository.deleteFavoriteRecipe(result = result)
    }

    fun isFav(result: Result) = viewModelScope.launch {
        favLiveData.value = false
        favoriteRecipeRepository.readFavoriteRecipes().collect {
            it.forEach { favoriteRecipe ->
                if (result.title.equals(favoriteRecipe.result.title)) {
                    favLiveData.value = true
                }
            }
        }
    }
}