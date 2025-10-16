package com.example.fooddeliveryapp.data.repo

import com.example.fooddeliveryapp.data.model.FoodItem
import java.util.UUID

object Images {

    private val demoFoodListData = listOf(
        // Burgers
        Triple(
            "Classic Cheeseburger",
            "https://images.pexels.com/photos/1639563/pexels-photo-1639563.jpeg?auto=compress&cs=tinysrgb&w=600",
            299.0
        ),
        Triple(
            "Double BBQ Burger",
            "https://images.pexels.com/photos/1435903/pexels-photo-1435903.jpeg?auto=compress&cs=tinysrgb&w=600",
            319.0
        ),

        // Pizzas
        Triple(
            "Margherita Pizza",
            "https://images.pexels.com/photos/2232/vegetables-italian-pizza-restaurant.jpg?auto=compress&cs=tinysrgb&w=600",
            249.0
        ),
        Triple(
            "Pepperoni Pizza",
            "https://images.pexels.com/photos/2619967/pexels-photo-2619967.jpeg?auto=compress&cs=tinysrgb&w=600",
            289.0
        ),

        // Pastas
        Triple(
            "Creamy Alfredo Pasta",
            "https://images.pexels.com/photos/1279330/pexels-photo-1279330.jpeg?auto=compress&cs=tinysrgb&w=600",
            279.0
        ),
        Triple(
            "Penne Arrabiata",
            "https://images.pexels.com/photos/4109084/pexels-photo-4109084.jpeg?auto=compress&cs=tinysrgb&w=600",
            259.0
        ),

        // Curries / Indian
        Triple(
            "Butter Chicken",
            "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&w=600",
            319.0
        ),
        Triple(
            "Thai Green Curry",
            "https://images.pexels.com/photos/5394513/pexels-photo-5394513.jpeg?auto=compress&cs=tinysrgb&w=600",
            299.0
        ),

        // Seafood
        Triple(
            "Grilled Salmon",
            "https://images.pexels.com/photos/2098085/pexels-photo-2098085.jpeg?auto=compress&cs=tinysrgb&w=600",
            369.0
        ),
        Triple(
            "Sushi Platter",
            "https://images.pexels.com/photos/357756/pexels-photo-357756.jpeg?auto=compress&cs=tinysrgb&w=600",
            399.0
        ),

        // Desserts
        Triple(
            "Chocolate Sundae",
            "https://images.pexels.com/photos/1352278/pexels-photo-1352278.jpeg?auto=compress&cs=tinysrgb&w=600",
            179.0
        ),
        Triple(
            "Strawberry Pancakes",
            "https://images.pexels.com/photos/376464/pexels-photo-376464.jpeg?auto=compress&cs=tinysrgb&w=600",
            189.0
        ),

        // Drinks
        Triple(
            "Iced Latte",
            "https://images.pexels.com/photos/34085/pexels-photo.jpg?auto=compress&cs=tinysrgb&w=600",
            149.0
        ),
        Triple(
            "Cappuccino",
            "https://images.pexels.com/photos/867466/pexels-photo-867466.jpeg?auto=compress&cs=tinysrgb&w=600",
            159.0
        ),

        // Salad / Healthy
        Triple(
            "Greek Salad",
            "https://images.pexels.com/photos/1640770/pexels-photo-1640770.jpeg?auto=compress&cs=tinysrgb&w=600",
            199.0
        )
    )

    val demoFoodList: List<FoodItem> by lazy {
        demoFoodListData.map { (name, image, price) ->
            FoodItem(
                id = UUID.randomUUID().toString(),
                image = image,
                name = name,
                price = price
            )
        }
    }
}
