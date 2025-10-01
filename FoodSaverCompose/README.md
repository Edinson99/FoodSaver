# FoodSaver Compose

FoodSaver Compose is an Android application built using Jetpack Compose, designed to facilitate the sharing and saving of food products. The app provides a user-friendly interface for users to register, log in, and manage their food products efficiently.

## Features

- **User Authentication**: Users can register and log in to their accounts.
- **Product Management**: Users can publish, view, and manage their food products.
- **Notifications**: Users receive notifications related to their products and account activities.
- **Responsive UI**: Built with Jetpack Compose for a modern and responsive user interface.

## Project Structure

The project is organized into the following main components:

- **MainActivity.kt**: The entry point of the application.
- **UI Screens**: Contains various screens for login, registration, product management, and notifications.
  - `LoginScreen.kt`
  - `RegisterScreen.kt`
  - `PublishProductScreen.kt`
  - `ProductDetailScreen.kt`
  - `NotificationsScreen.kt`
  - `HomeScreen.kt`
- **Components**: Reusable UI components such as product and notification cards.
  - `ProductCard.kt`
  - `NotificationCard.kt`
- **Models**: Data models representing the core entities of the application.
  - `Product.kt`
  - `User.kt`
  - `Notification.kt`
- **Resources**: Contains app resources such as colors, strings, and themes.

## Getting Started

To get started with the FoodSaver Compose project:

1. Clone the repository:
   ```
   git clone <repository-url>
   ```

2. Open the project in Android Studio.

3. Build and run the application on an Android device or emulator.

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.

## License

This project is licensed under the MIT License. See the LICENSE file for details.