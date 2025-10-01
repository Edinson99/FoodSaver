# Foodsaver App

## Overview
The Foodsaver app is designed to facilitate the sharing and management of food products among users. It provides a user-friendly interface for logging in, registering, publishing products, and viewing product details and notifications.

## Features
- **User Authentication**: Users can log in and register to access the app.
- **Product Management**: Users can publish new products and view details of existing products.
- **Notifications**: Users receive notifications related to their activities within the app.
- **Home Dashboard**: A central hub for navigating through the app's features.

## Project Structure
The project is organized as follows:

```
app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── foodsaver/
│   │   │           ├── MainActivity.kt
│   │   │           ├── ui/
│   │   │           │   ├── LoginActivity.kt
│   │   │           │   ├── RegisterActivity.kt
│   │   │           │   ├── PublishProductActivity.kt
│   │   │           │   ├── ProductDetailActivity.kt
│   │   │           │   ├── NotificationsActivity.kt
│   │   │           │   └── HomeActivity.kt
│   │   │           └── model/
│   │   │               ├── Product.kt
│   │   │               ├── User.kt
│   │   │               └── Notification.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_login.xml
│   │   │   │   ├── activity_register.xml
│   │   │   │   ├── activity_publish_product.xml
│   │   │   │   ├── activity_product_detail.xml
│   │   │   │   ├── activity_notifications.xml
│   │   │   │   └── activity_home.xml
│   │   │   ├── values/
│   │   │   │   ├── strings.xml
│   │   │   │   ├── colors.xml
│   │   │   │   └── styles.xml
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── README.md
```

## Getting Started
To get started with the Foodsaver app, clone the repository and open it in your preferred IDE. Ensure you have the necessary dependencies installed as specified in the `build.gradle.kts` file.

## Contributing
Contributions are welcome! Please feel free to submit a pull request or open an issue for any suggestions or improvements.

## License
This project is licensed under the MIT License - see the LICENSE file for details.