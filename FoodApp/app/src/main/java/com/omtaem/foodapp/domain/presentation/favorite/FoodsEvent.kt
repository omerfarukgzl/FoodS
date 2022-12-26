package com.omtaem.foodapp.domain.presentation.favorite

import com.omtaem.foodapp.domain.presentation.data.local.entity.FavoritesEntity

sealed class FoodsEvent {
    data class ShowUndoDeleteItemMessage(val favoriteEntity: FavoritesEntity) : FoodsEvent()
}