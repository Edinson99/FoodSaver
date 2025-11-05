package com.foodsaver.config

import com.foodsaver.model.*
import com.foodsaver.repository.*
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DataInitializer(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository,
    private val notificationRepository: NotificationRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (userRepository.count() == 0L) {
            println("🌱 Inicializando datos de prueba...")
            initializeData()
            println("✅ Datos de prueba creados exitosamente!")
        } else {
            println("📊 Base de datos ya tiene datos.")
            println("👥 Total usuarios: ${userRepository.count()}")
            println("📦 Total productos: ${productRepository.count()}")
            println("🔔 Total notificaciones: ${notificationRepository.count()}")
        }
    }

    private fun initializeData() {
        // Crear usuarios de prueba
        val vendor1 = userRepository.save(User(
            email = "vendor1@test.com",
            password = "123456",
            name = "Fruver La Plaza",
            phone = "3001234567",
            location = "Centro - Mercado Principal",
            userType = UserType.VENDOR
        ))

        val vendor2 = userRepository.save(User(
            email = "vendor2@test.com",
            password = "123456",
            name = "Panadería El Buen Pan",
            phone = "3007654321",
            location = "Norte - Zona Rosa",
            userType = UserType.VENDOR
        ))

        val buyer1 = userRepository.save(User(
            email = "buyer1@test.com",
            password = "123456",
            name = "María García",
            phone = "3009876543",
            location = "Sur - Barrio Nuevo",
            userType = UserType.BUYER
        ))

        val buyer2 = userRepository.save(User(
            email = "buyer2@test.com",
            password = "123456",
            name = "Carlos López",
            phone = "3001112233",
            location = "Oeste - Zona Industrial",
            userType = UserType.BUYER
        ))

        // Crear productos de prueba - CAMBIO: Usar enum en lugar de strings
        val products = listOf(
            Product(
                name = "Tomates frescos",
                category = ProductCategory.VERDURAS,  // CAMBIO: enum en lugar de "VERDURAS"
                quantity = "5kg",
                price = 2000.0,
                description = "Tomates rojos frescos, perfectos para ensaladas. Cultivados localmente sin pesticidas.",
                location = "Centro - Mercado Principal",
                isForSale = true,
                status = ProductStatus.AVAILABLE,
                expiryDate = LocalDateTime.now().plusDays(3),
                vendorId = vendor1.id
            ),
            Product(
                name = "Pan integral del día",
                category = ProductCategory.PANADERIA,  // CAMBIO: enum
                quantity = "10 unidades",
                price = null,
                description = "Pan integral horneado hoy. Ideal para donación a familias necesitadas.",
                location = "Norte - Zona Rosa",
                isForSale = false,
                status = ProductStatus.AVAILABLE,
                expiryDate = LocalDateTime.now().plusDays(1),
                vendorId = vendor2.id
            ),
            Product(
                name = "Manzanas rojas",
                category = ProductCategory.FRUTAS,  // CAMBIO: enum
                quantity = "3kg",
                price = 1500.0,
                description = "Manzanas rojas importadas, excelente calidad. Perfectas para jugos o consumo directo.",
                location = "Centro - Mercado Principal",
                isForSale = true,
                status = ProductStatus.AVAILABLE,
                expiryDate = LocalDateTime.now().plusDays(7),
                vendorId = vendor1.id
            ),
            Product(
                name = "Leche próxima a vencer",
                category = ProductCategory.LACTEOS,  // CAMBIO: enum
                quantity = "20 litros",
                price = 800.0,
                description = "Leche entera, vence en 2 días. Precio especial por pronta expiración.",
                location = "Sur - Supermercado Local",
                isForSale = true,
                status = ProductStatus.AVAILABLE,
                expiryDate = LocalDateTime.now().plusDays(2),
                vendorId = vendor1.id
            ),
            Product(
                name = "Pollo asado",
                category = ProductCategory.CARNES,  // CAMBIO: enum
                quantity = "5 unidades",
                price = null,
                description = "Pollos asados del día anterior. Donación para comedores comunitarios.",
                location = "Centro - Restaurante El Pollo",
                isForSale = false,
                status = ProductStatus.AVAILABLE,
                expiryDate = LocalDateTime.now().plusHours(12),
                vendorId = vendor2.id
            ),
            Product(
                name = "Bananos maduros",
                category = ProductCategory.FRUTAS,  // CAMBIO: enum
                quantity = "10kg",
                price = 500.0,
                description = "Bananos muy maduros, ideales para batidos y postres. Precio especial.",
                location = "Centro - Mercado Principal",
                isForSale = true,
                status = ProductStatus.AVAILABLE,
                expiryDate = LocalDateTime.now().plusDays(1),
                vendorId = vendor1.id
            )
        )

        productRepository.saveAll(products)

        // Crear notificaciones de prueba
        val notifications = listOf(
            Notification(
                title = "¡Bienvenido a FoodSaver!",
                message = "Gracias por unirte a nuestra comunidad. Juntos reducimos el desperdicio de alimentos.",
                type = NotificationType.GENERAL,
                userId = buyer1.id
            ),
            Notification(
                title = "Nuevo producto disponible",
                message = "Hay tomates frescos disponibles cerca de tu ubicación.",
                type = NotificationType.NEW_PRODUCT,
                userId = buyer1.id,
                productId = products[0].id
            ),
            Notification(
                title = "Producto próximo a vencer",
                message = "Tu leche expira en 2 días. Considera donarla si no la vas a usar.",
                type = NotificationType.PRODUCT_EXPIRING,
                userId = vendor1.id,
                productId = products[3].id
            )
        )

        notificationRepository.saveAll(notifications)

        println("✅ Datos creados:")
        println("👥 ${userRepository.count()} usuarios")
        println("📦 ${productRepository.count()} productos")
        println("🔔 ${notificationRepository.count()} notificaciones")
        println("\n📧 Usuarios de prueba:")
        println("🏪 vendor1@test.com - Fruver La Plaza")
        println("🥖 vendor2@test.com - Panadería El Buen Pan")
        println("👤 buyer1@test.com - María García")
        println("👤 buyer2@test.com - Carlos López")
        println("🔑 Contraseña para todos: 123456")
    }
}