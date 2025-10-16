package com.example.fooddeliveryapp.ui.status

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.fooddeliveryapp.data.model.OrderStatus
import com.example.fooddeliveryapp.ui.viewmodel.CartViewModel
import com.example.fooddeliveryapp.ui.viewmodel.HomeScreenViewmodel
import kotlinx.coroutines.delay

@Composable
fun OrderStatusScreen(navController: NavController) {
    val cartViewModel: CartViewModel = hiltViewModel()
    var status by remember { mutableStateOf<OrderStatus?>(null) }

    LaunchedEffect(Unit) {
        delay(1000)
        status = listOf(
            OrderStatus.Success,
            OrderStatus.Success,
            OrderStatus.Success,
            OrderStatus.Success
        ).random()
    }


    LaunchedEffect(status) {
        if (status == OrderStatus.Success) {
            cartViewModel.clearCart()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        if (status == null) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }

        AnimatedVisibility(
            visible = status != null,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 3 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { it / 3 })
        ) {
            when (status) {
                OrderStatus.Success -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        SuccessCard(
                            onTrackOrder = { navController.navigate("home") },
                            onBackHome = { navController.navigate("home") },
                            estimatedTime = "30–35 mins"
                        )
                    }
                    cartViewModel.clearCart()
                }

                OrderStatus.PaymentFailed -> ErrorCard(
                    icon = Icons.Default.ErrorOutline,
                    title = "Payment Failed",
                    message = "We couldn’t process your payment. Please try again with another method.",
                    buttonText = "Retry Payment"
                ) { navController.popBackStack() }

                OrderStatus.RestaurantClosed -> {
                    ErrorCard(
                        icon = Icons.Default.Restaurant,
                        title = "Restaurant Closed",
                        message = "The restaurant is currently closed. Please try again later.",
                        buttonText = "Back to Home"
                    ) { navController.navigate("home") }
                    cartViewModel.clearCart()
                }

                OrderStatus.FoodUnavailable -> ErrorCard(
                    icon = Icons.Default.WarningAmber,
                    title = "Item Unavailable",
                    message = "Some items in your order are out of stock. Modify your cart and retry.",
                    buttonText = "Modify Order"
                ) { navController.popBackStack() }

                else -> {
                    Text(
                        text = "Unexpected error. Please try again.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
