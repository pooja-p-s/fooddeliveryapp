package com.example.fooddeliveryapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

object AppThemeDefaults {
    @Composable
    fun textFieldColors(): TextFieldColors {
        return TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        )
    }
}

val MaterialTheme.textFieldColor: TextFieldColors
    @Composable
    get() = AppThemeDefaults.textFieldColors()
