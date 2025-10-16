# 🍔 OnlineFoodApp

A modern **Food Delivery Android App** built using **Kotlin**, **Jetpack Compose (Material 3)**, and **MVVM architecture**.  
This project follows **clean architecture principles**, **Material Design 3 guidelines**, and **scalable UI patterns** to demonstrate an end-to-end app flow for modern food delivery experiences.

> ⚙️ This is a **learning and showcase project** focused on building UI flows, understanding Jetpack Compose state management, and structuring apps using MVVM.  
> It is **not a production-ready application**, but is designed to reflect **real-world engineering practices** and **modern Android design standards**.

---

## 📱 Screenshots

| Home | Cart | Checkout | Order Success |
|:----:|:----:|:---------:|:--------------:|
| ![Home](appscreens/homescreen.png) | ![Cart](appscreens/cartscreen.png) | ![Checkout](appscreens/checkoutscreen.png) | ![Success](appscreens/statusscreen.png) |

*(All UI screens follow Material 3 typography, spacing, elevation, and accessibility standards.)*

---

## 🎯 Features

- **Home Screen:**  
  Displays food items in a grid layout with name, price, and “Add” button.  
  Includes a clean search bar for dish filtering.

- **Cart Screen:**  
  Real-time updates for quantity changes and total price.  
  Automatically calculates item total, delivery charge, and GST (5%).

- **Checkout Screen:**  
  Form with dropdown-based payment selection, card details input, and total payable summary.

- **Order Success Screen:**  
  Material 3 card with a confirmation animation and clear navigation options.

---

## 🧩 Tech Stack

| Layer | Technology | Purpose |
|:------|:------------|:--------|
| **Language** | Kotlin | Type-safe, concise, and modern |
| **UI Toolkit** | Jetpack Compose (Material 3) | Declarative and reactive UI |
| **Architecture** | MVVM | Clear separation between UI and business logic |
| **State Management** | ViewModel + StateFlow / LiveData | Reactive and lifecycle-aware |
| **Dependency Injection** | Hilt *(extendable)* | For scalable and testable modules |
| **Navigation** | Compose Navigation | Back stack management and smooth transitions |
| **Design System** | Material 3 | Consistent colors, shapes, and elevation |
| **Build Tool** | Gradle (KTS) | Reliable build configuration |

---

## 🧠 Architecture Overview

```text
OnlineFoodApp/
├── data/
│   ├── model/
│   │   ├── FoodItem.kt
│   │   ├── OrderStatus.kt
│   │   └── CartItem.kt
│   ├── repo/
│   │   └── FoodRepoImpl.kt
│   ├── di/
│   │   └── RepositoryModule.kt
│
├── domain/
│   ├── repo/
│   │   └── IFoodRepository.kt
│   └── usecase/
│       └── GetFoodItemsUse.kt
│
├── ui/
│   ├── home/
│   │   ├── HomeScreen.kt
│   │   └── FoodItemCard.kt
│   ├── cart/
│   │   ├── CartScreen.kt
│   │   ├── BillRow.kt
│   │   └── FinalAmountCard.kt
│   ├── checkout/
│   │   ├── CheckoutScreen.kt
│   │   └── PaymentCardFields.kt
│   ├── status/
│   │   ├── OrderStatusScreen.kt
│   │   └── SuccessCard.kt
│   ├── component/
│   │   ├── AppBar.kt
│   │   ├── QuantityButton.kt
│   │   └── CustomDropdown.kt
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Theme.kt
│   │   └── Type.kt
│   └── viewmodel/
│       ├── HomeScreenViewModel.kt
│       └── CartViewModel.kt
│
├── FoodDeliveryApp.kt
└── MainActivity.kt

```
---

Each screen has its **own ViewModel** managing UI state independently — improving modularity and testability.  
State flows only one way: **ViewModel → UI**, ensuring predictable updates.


## 💡 Design & Code Highlights

- Built entirely with **Jetpack Compose (Material 3)** components (`Card`, `OutlinedTextField`, `Button`, `TopAppBar`, etc.)
- **MVVM pattern** ensuring scalable and maintainable code.
- **Reactive UI updates**: State-driven rendering via ViewModel.
- **Reusable composables** for food item cards, buttons, and billing summaries.
- **Clean architecture**: UI, business logic, and data layers separated.
- **Responsive design**: Adapts to screen size and density.
- **Material 3 theming**: ColorScheme, typography, and elevation surfaces.

---

## 🧪 Future Enhancements

- Integrate **REST API** (Retrofit + Ktor backend)
- Add **Room Database** for offline persistence
- Implement **Firebase Authentication**
- Add **Compose Motion & Transition animations**
- Introduce **unit testing** for ViewModels and Composables

---

## 🚀 Getting Started

### Prerequisites
- Android Studio **Giraffe or newer**
- Kotlin **1.9+**
- Gradle **8.0+**
- Android SDK **33+**

### Steps

```bash
# Clone this repository
git clone https://github.com/yourusername/OnlineFoodApp.git

# Open in Android Studio
# Build and run on emulator or physical device (Android 12+)
```

---

## 👩‍💻 Developer

Pooja Pradeep Simla
Software Engineer | Android & Backend Developer
3+ years experience with Kotlin, .NET MAUI, C#, and Python
Strong in MVVM, Jetpack Compose, Clean Architecture, and Material Design 3
Passionate about building production-grade, scalable Android apps

