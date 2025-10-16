package com.example.fooddeliveryapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.data.model.FoodItem
import com.example.fooddeliveryapp.domain.usecase.GetFoodItemsUse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
    private val getFoodItemsUse: GetFoodItemsUse
) : ViewModel() {

    private val _allFoodItems = MutableLiveData<List<FoodItem>>()
    private val _availableFoodItems = MutableLiveData<List<FoodItem>>()
    val availableFoodItems: LiveData<List<FoodItem>> = _availableFoodItems

    init {
        loadFoodItems()
    }

    private fun loadFoodItems() {
        val items = getFoodItemsUse()
        _allFoodItems.value = items
        _availableFoodItems.value = items
    }

    fun searchFood(searchKey: String) {
        val allItems = _allFoodItems.value ?: return
        if (searchKey.isBlank()) {
            _availableFoodItems.value = allItems
        } else {
            _availableFoodItems.value = allItems.filter {
                it.name.contains(searchKey, ignoreCase = true)
            }
        }
    }
}