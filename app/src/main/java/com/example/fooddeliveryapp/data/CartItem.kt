package com.example.fooddeliveryapp.data

data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int = 1
)
