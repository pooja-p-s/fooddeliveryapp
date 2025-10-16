package com.example.fooddeliveryapp.data.repo

import com.example.fooddeliveryapp.data.model.FoodItem
import com.example.fooddeliveryapp.data.repo.Images.demoFoodList
import com.example.fooddeliveryapp.domain.repo.IFoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor() : IFoodRepository {
    override fun getFoodItems(): List<FoodItem> = demoFoodList
}