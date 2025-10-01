# Foodsaver Project

## Overview
Foodsaver is an Android application designed to facilitate the sharing and management of food products. The app allows users to log in, register, publish products, and view product details, notifications, and a home dashboard.

## Features
- **User Authentication**: Users can log in and register to access the app.
- **Product Management**: Users can publish new products and view detailed information about existing products.
- **Notifications**: Users receive notifications related to their activities within the app.
- **User-Friendly Interface**: The app provides a clean and intuitive user interface for seamless navigation.

## Project Structure
The project is organized into the following main components:

- **MainActivity**: Entry point of the application, handling navigation to other activities.
- **LoginActivity**: Manages user login and authentication.
- **RegisterActivity**: Handles user registration.
- **PublishProductActivity**: Allows users to publish new products.
- **ProductDetailActivity**: Displays detailed information about products.
- **NotificationsActivity**: Manages user notifications.
- **HomeActivity**: Serves as the main dashboard for users.

## Layouts
The app includes various XML layout files for different activities:
- `activity_main.xml`: Layout for the main activity.
- `activity_login.xml`: Layout for the login activity.
- `activity_register.xml`: Layout for the registration activity.
- `activity_publish_product.xml`: Layout for publishing products.
- `activity_product_detail.xml`: Layout for product details.
- `activity_notifications.xml`: Layout for notifications.
- `activity_home.xml`: Layout for the home activity.

## Models
The app defines several data models:
- **Product**: Represents a product with properties like id, name, description, and price.
- **User**: Represents a user with properties like id, username, email, and password.
- **Notification**: Represents a notification with properties like id, title, message, and timestamp.

## Build Configuration
The project uses Kotlin DSL for build configuration, specifying dependencies and settings for both the app module and the overall project.

## Getting Started
To get started with the Foodsaver project:
1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the application on an Android device or emulator.

## License
This project is licensed under the MIT License. See the LICENSE file for more details.