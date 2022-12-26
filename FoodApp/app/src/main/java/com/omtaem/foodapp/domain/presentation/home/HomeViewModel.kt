package com.omtaem.foodapp.domain.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omtaem.foodapp.domain.presentation.data.network.model.FoodRecipe
import com.omtaem.foodapp.domain.presentation.domain.FoodRecipeRepository
import com.omtaem.foodapp.domain.presentation.util.Contants
import com.omtaem.foodapp.domain.presentation.util.Contants.DEFAULT_DIET_TYPE
import com.omtaem.foodapp.domain.presentation.util.Contants.DEFAULT_MEAL_TYPE
import com.omtaem.foodapp.domain.presentation.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FoodRecipeRepository
) : ViewModel() {

    private val _recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()
    val recipesResponse: LiveData<NetworkResult<FoodRecipe>> = _recipesResponse

    fun getAllRecipe(queries: Map<String, String>) = viewModelScope.launch {
        _recipesResponse.postValue(NetworkResult.Success(repository.getAllFood(queries = queries)))
    }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[Contants.QUERY_NUMBER] = Contants.DEFAULT_RECIPES_NUMBER
        queries[Contants.QUERY_API_KEY] = Contants.API_KEY
        queries[Contants.QUERY_TYPE] = DEFAULT_MEAL_TYPE
        queries[Contants.QUERY_DIET] = DEFAULT_DIET_TYPE
        queries[Contants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Contants.QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

}