package com.example.fooddeliveryapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.data.CartItem
import com.example.fooddeliveryapp.ui.component.CartBottomBar
import com.example.fooddeliveryapp.ui.viewmodel.CartViewModel
import com.example.fooddeliveryapp.ui.viewmodel.HomeScreenViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeScreenViewmodel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    navController: NavController
) {
    val availableItems by homeViewModel.availableFoodItems.observeAsState(emptyList())
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPrice by cartViewModel.totalPrice.collectAsState()

    val totalItems = cartItems.sumOf { it.quantity }

    Scaffold(
        bottomBar = {
            if (totalItems > 0) {
                CartBottomBar(
                    totalItems = totalItems,
                    totalPrice = totalPrice,
                    primaryButtonText = "View Cart",
                    onButtonClick = { navController.navigate("cart") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = "Delicious Food Near You 🍔",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(16.dp)
            )
            if (availableItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No items available")
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    items(availableItems) { item ->
                        val cartEntry = cartItems.find { it.id == item.id }
                        val count = cartEntry?.quantity ?: 0

                        FoodItemCard(
                            food = item,
                            count = count,
                            onAdd = {
                                val cartItem = CartItem(
                                    id = item.id,
                                    name = item.name,
                                    price = item.price,
                                    quantity = 1
                                )
                                cartViewModel.addToCart(cartItem)
                            },
                            onRemove = { cartViewModel.removeFromCart(item.id) }
                        )
                    }
                }
            }
        }
    }
}
