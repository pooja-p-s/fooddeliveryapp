package com.example.fooddeliveryapp.ui.checkout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fooddeliveryapp.ui.component.AppNavigator
import com.example.fooddeliveryapp.ui.component.CustomDropdown
import com.example.fooddeliveryapp.ui.theme.textFieldColor
import com.example.fooddeliveryapp.ui.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    navController: NavController,
    cartViewModel: CartViewModel
) {
    val totalPayable by cartViewModel.totalPayable.collectAsState()

    var selectedPayment by remember { mutableStateOf("Credit/Debit Card") }
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cardHolder by remember { mutableStateOf("") }
    var upiId by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 80.dp)
    ) {
        item { AppNavigator(navController = navController, title = "Checkout") }

        item {
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.elevatedCardElevation(2.dp)
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text(
                        text = "Delivery Address",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "123, Marine Drive, Kochi, Kerala",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Estimated delivery: 30–40 mins",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }

        item {
            Text(
                text = "Payment Method",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(8.dp))
            CustomDropdown(
                label = "Select payment method",
                options = listOf("Credit/Debit Card", "UPI", "Cash on Delivery"),
                selectedOption = selectedPayment,
                onOptionSelected = { selectedPayment = it }
            )
        }

        item {
            AnimatedVisibility(visible = selectedPayment == "Credit/Debit Card") {
                PaymentCardFields(
                    cardNumber = cardNumber,
                    expiryDate = expiryDate,
                    cardHolder = cardHolder,
                    onCardChange = { cardNumber = it },
                    onExpiryChange = { expiryDate = it },
                    onNameChange = { cardHolder = it }
                )
            }

            AnimatedVisibility(visible = selectedPayment == "UPI") {
                PaymentUPIField(upiId = upiId, onUpiChange = { upiId = it })
            }
        }

        item {
            OrderTotalCard(totalPrice = totalPayable)
        }

        item {
            Button(
                onClick = { navController.navigate("orderStatus") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Place Order", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}


@Composable
fun PaymentUPIField(
    upiId: String,
    onUpiChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = upiId,
            colors = MaterialTheme.textFieldColor,
            onValueChange = onUpiChange,
            label = { Text("Enter UPI ID (e.g. name@bank)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )
    }
}

