package com.example.fooddeliveryapp.ui.component

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController

@Composable
fun HandleBackPress(navController: NavController, finishActivity: () -> Unit) {
    var lastBackPressTime by remember { mutableLongStateOf(0L) }
    val context = LocalContext.current

    BackHandler {
        val currentTime = System.currentTimeMillis()
        if (navController.currentDestination?.route != "home") {
            navController.popBackStack()
        } else {
            if (currentTime - lastBackPressTime < 2000) {
                finishActivity() // safely finish main activity
            } else {
                lastBackPressTime = currentTime
                Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}