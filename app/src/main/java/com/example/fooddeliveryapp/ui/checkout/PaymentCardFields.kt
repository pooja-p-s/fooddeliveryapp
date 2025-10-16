package com.example.fooddeliveryapp.ui.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.ui.theme.textFieldColor

@Composable
fun PaymentCardFields(
    cardNumber: String,
    expiryDate: String,
    cardHolder: String,
    onCardChange: (String) -> Unit,
    onExpiryChange: (String) -> Unit,
    onNameChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = cardNumber,
            colors = MaterialTheme.textFieldColor,
            onValueChange = onCardChange,
            label = { Text("Card Number") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        OutlinedTextField(
            value = expiryDate,
            colors = MaterialTheme.textFieldColor,
            onValueChange = onExpiryChange,
            label = { Text("Expiry Date (MM/YY)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
        OutlinedTextField(
            value = cardHolder,
            colors = MaterialTheme.textFieldColor,
            onValueChange = onNameChange,
            label = { Text("Cardholder Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
    }
}