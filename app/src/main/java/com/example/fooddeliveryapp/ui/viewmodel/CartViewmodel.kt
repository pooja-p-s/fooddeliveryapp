package com.example.fooddeliveryapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fooddeliveryapp.data.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor() : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    val totalPrice: StateFlow<Double> = _cartItems
        .map { list -> list.sumOf { it.price * it.quantity } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    val totalPayable: StateFlow<Double> = _cartItems
        .map { list ->
            val itemsTotal = list.sumOf { it.price * it.quantity }
            val deliveryCharge = if (itemsTotal > 0) 30.0 else 0.0
            val gst = 0.05 * itemsTotal
            itemsTotal + deliveryCharge + gst
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0.0)

    fun addToCart(item: CartItem) {
        _cartItems.value = _cartItems.value.toMutableList().apply {
            val index = indexOfFirst { it.id == item.id }
            if (index >= 0) {
                val existing = this[index]
                this[index] = existing.copy(quantity = existing.quantity + 1)
            } else add(item)
        }
    }

    fun removeFromCart(itemId: String) {
        _cartItems.value = _cartItems.value.toMutableList().apply {
            val index = indexOfFirst { it.id == itemId }
            if (index >= 0) {
                val existing = this[index]
                if (existing.quantity > 1)
                    this[index] = existing.copy(quantity = existing.quantity - 1)
                else removeAt(index)
            }
        }
    }

    fun getCount(itemId: String): Int =
        _cartItems.value.firstOrNull { it.id == itemId }?.quantity ?: 0

    fun clearCart() {
        _cartItems.value = emptyList()
    }
}
