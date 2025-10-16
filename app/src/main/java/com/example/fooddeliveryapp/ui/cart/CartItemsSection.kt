package com.example.fooddeliveryapp.ui.cart

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fooddeliveryapp.data.CartItem
import com.example.fooddeliveryapp.ui.component.QuantityButton
import com.example.fooddeliveryapp.ui.viewmodel.CartViewModel

@Composable
fun CartItemsSection(cartItems: List<CartItem>, cartViewModel: CartViewModel) {
    ElevatedCard (
        modifier = Modifier.fillMaxWidth()
            .border(
                2.dp,
                MaterialTheme.colorScheme.primary,
                RoundedCornerShape(20.dp)
            ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(20.dp),
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            cartItems.forEach { item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Text(
                            text = "₹${item.price * item.quantity}",
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        QuantityButton(symbol = "-", onClick = { cartViewModel.removeFromCart(item.id) })
                        Text(
                            item.quantity.toString(),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        QuantityButton(symbol = "+", onClick = { cartViewModel.addToCart(item) })
                    }
                }
            }
        }
    }
}
