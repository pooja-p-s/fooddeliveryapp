package com.example.fooddeliveryapp.domain.repo


import com.example.fooddeliveryapp.data.model.FoodItem

interface IFoodRepository{
    fun getFoodItems(): List<FoodItem>
}