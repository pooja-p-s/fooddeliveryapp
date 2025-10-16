package com.example.fooddeliveryapp.domain.usecase

import com.example.fooddeliveryapp.data.model.FoodItem
import com.example.fooddeliveryapp.data.repo.Images.demoFoodList
import com.example.fooddeliveryapp.domain.repo.IFoodRepository
import javax.inject.Inject

class GetFoodItemsUse @Inject constructor(
    private val foodRepository: IFoodRepository
) {
    operator fun invoke(): List<FoodItem> {
        return demoFoodList
    }
}