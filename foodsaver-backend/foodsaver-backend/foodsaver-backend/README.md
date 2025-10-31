# Foodsaver Backend

## Overview
Foodsaver is a backend application built with Spring Boot and Kotlin. It provides a RESTful API for managing users, products, and notifications in a food-saving application.

## Features
- User registration, login, and profile management
- Product management including adding, updating, and retrieving products
- Notification management for sending and retrieving notifications

## Project Structure
```
foodsaver-backend
├── src
│   ├── main
│   │   ├── kotlin
│   │   │   └── com
│   │   │       └── foodsaver
│   │   │           ├── FoodsaverApplication.kt
│   │   │           ├── controller
│   │   │           │   ├── UserController.kt
│   │   │           │   ├── ProductController.kt
│   │   │           │   └── NotificationController.kt
│   │   │           ├── service
│   │   │           │   ├── UserService.kt
│   │   │           │   ├── ProductService.kt
│   │   │           │   └── NotificationService.kt
│   │   │           ├── repository
│   │   │           │   ├── UserRepository.kt
│   │   │           │   ├── ProductRepository.kt
│   │   │           │   └── NotificationRepository.kt
│   │   │           ├── model
│   │   │           │   ├── User.kt
│   │   │           │   ├── Product.kt
│   │   │           │   └── Notification.kt
│   │   │           └── config
│   │   │               └── DatabaseConfig.kt
│   │   └── resources
│   │       ├── application.yml
│   │       └── data.sql
│   └── test
│       └── kotlin
│           └── com
│               └── foodsaver
│                   ├── FoodsaverApplicationTests.kt
│                   └── controller
│                       ├── UserControllerTest.kt
│                       └── ProductControllerTest.kt
├── build.gradle.kts
└── README.md
```

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd foodsaver-backend
   ```
3. Build the project using Gradle:
   ```
   ./gradlew build
   ```
4. Run the application:
   ```
   ./gradlew bootRun
   ```

## Configuration
- Update the `application.yml` file with your database connection details.
- Use the `data.sql` file to initialize the database with sample data.

## Testing
- Unit tests are located in the `src/test/kotlin/com/foodsaver` directory.
- Run tests using:
  ```
  ./gradlew test
  ```

## License
This project is licensed under the MIT License. See the LICENSE file for details.