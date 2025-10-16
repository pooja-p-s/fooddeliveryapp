package com.example.fooddeliveryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fooddeliveryapp.ui.Route
import com.example.fooddeliveryapp.ui.component.AppTopBar
import com.example.fooddeliveryapp.ui.component.HandleBackPress
import com.example.fooddeliveryapp.ui.cart.CartScreen
import com.example.fooddeliveryapp.ui.checkout.CheckoutScreen
import com.example.fooddeliveryapp.ui.home.HomeScreen
import com.example.fooddeliveryapp.ui.status.OrderStatusScreen
import com.example.fooddeliveryapp.ui.theme.FoodDeliveryAppTheme
import com.example.fooddeliveryapp.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            HandleBackPress(
                navController = navController,
                finishActivity = { finish() }
            )
            FoodDeliveryAppTheme {
                Scaffold(
                    topBar = {
                        AppTopBar()
                    }
                ) { innerPadding ->
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = Route.HOME,
                        route = "root_graph"
                    ) {
                        composable(Route.HOME) { backStackEntry ->
                            val parentEntry = remember(backStackEntry) {
                                navController.getBackStackEntry("root_graph")
                            }
                            val cartViewModel: CartViewModel = hiltViewModel(parentEntry)
                            HomeScreen(navController = navController, cartViewModel = cartViewModel)
                        }

                        composable(Route.CART) { backStackEntry ->
                            val parentEntry = remember(backStackEntry) {
                                navController.getBackStackEntry("root_graph")
                            }
                            val cartViewModel: CartViewModel = hiltViewModel(parentEntry)
                            CartScreen(navController = navController, cartViewModel = cartViewModel)
                        }
                        composable(Route.CHECKOUT) { backStackEntry ->
                            val parentEntry = remember(backStackEntry) {
                                navController.getBackStackEntry("root_graph")
                            }
                            val cartViewModel: CartViewModel = hiltViewModel(parentEntry)
                            CheckoutScreen(navController = navController, cartViewModel = cartViewModel)
                        }
                        composable(Route.ORDERSTATUS) { backStackEntry ->
                            val parentEntry = remember(backStackEntry) {
                                navController.getBackStackEntry("root_graph")
                            }
                            OrderStatusScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

