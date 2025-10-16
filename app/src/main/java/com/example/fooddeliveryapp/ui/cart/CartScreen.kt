package com.example.fooddeliveryapp.ui.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.ui.component.AppNavigator
import com.example.fooddeliveryapp.ui.component.CartBottomBar
import com.example.fooddeliveryapp.ui.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    cartViewModel: CartViewModel = hiltViewModel(),
    navController: NavController
) {
    val cartItems by cartViewModel.cartItems.collectAsState()
    val totalPayable by cartViewModel.totalPayable.collectAsState()

    val itemsTotal = cartItems.sumOf { it.price * it.quantity }
    val deliveryCharge = if (itemsTotal > 0) 30.0 else 0.0
    val gst = 0.05 * itemsTotal

    Scaffold(
        topBar = { AppNavigator(navController = navController, title = "Your Cart") },
        bottomBar = {
            if (cartItems.isNotEmpty()) {
                CartBottomBar(
                    totalItems = cartItems.sumOf { it.quantity },
                    totalPrice = totalPayable,
                    primaryButtonText = "Checkout",
                    onButtonClick = { navController.navigate("checkout") }
                )
            }
        }
    ) { innerPadding ->
        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Your cart is empty",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(bottom = 120.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    CartItemsSection(cartItems, cartViewModel)
                }

                item {
                    FinalAmountCard(
                        itemsTotal = itemsTotal,
                        deliveryCharge = deliveryCharge,
                        gst = gst,
                        totalPayable = totalPayable
                    )
                }
            }
        }
    }
}
