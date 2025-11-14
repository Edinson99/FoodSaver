# FoodSaver 🌱

Una aplicación móvil y plataforma web diseñada para reducir el desperdicio de alimentos conectando vendedores que tienen productos próximos a vencer con compradores que buscan alimentos a precios reducidos o donaciones.

## 🎯 Objetivo

Combatir el desperdicio de alimentos facilitando la venta y donación de productos que están próximos a vencer, creando una economía circular que beneficia tanto a vendedores como a compradores y al medio ambiente.

## ✨ Características Principales

### 🔐 Autenticación de Usuarios
- Sistema de registro e inicio de sesión
- Perfiles diferenciados para vendedores y compradores
- Gestión segura de sesiones

### 📦 Gestión de Productos
- **Publicación de productos** con fotos, descripción, precio y fecha de vencimiento
- **Categorización** (Frutas, Verduras, Lácteos, Panadería, Carnes, Bebidas, etc.)
- **Modalidades**: Venta con descuento o donación gratuita
- **Estados del producto**: Disponible, Vendido, Donado, Expirado, Reservado

### 🔍 Búsqueda y Filtros
- Búsqueda por nombre de producto
- Filtros por categoría, ubicación y tipo (venta/donación)
- Resultados en tiempo real

### 💬 Sistema de Chat
- **Comunicación directa** entre vendedores y compradores
- **Chat por producto** para negociar detalles
- **Historial de conversaciones**
- Notificaciones de nuevos mensajes

### 🔔 Notificaciones Push
- Nuevos productos disponibles en tu área
- Productos próximos a vencer
- Mensajes de chat
- Confirmaciones de venta/donación

### 📊 Reportes y Estadísticas
- **Panel de impacto ambiental** personal
- Historial de productos vendidos/donados
- Estadísticas de ingresos y ahorro
- Métricas de contribución al medio ambiente

### 📱 Funcionalidades Técnicas
- **Cámara integrada** para capturar fotos de productos
- **Geolocalización** para mostrar productos cercanos
- **Sincronización en tiempo real** con el backend
- **Interfaz intuitiva** con Jetpack Compose

## 🏗️ Arquitectura del Proyecto

### 📱 Frontend (Android)
```
app/
├── src/main/java/com/foodsaver/
│   ├── ui/                          # Pantallas de la aplicación
│   │   ├── LoginScreen.kt          # Inicio de sesión
│   │   ├── RegisterScreen.kt       # Registro de usuarios
│   │   ├── HomeScreen.kt           # Pantalla principal
│   │   ├── PublishProductScreen.kt # Publicar productos
│   │   ├── SearchScreen.kt         # Búsqueda y filtros
│   │   ├── ProductDetailScreen.kt  # Detalle del producto
│   │   ├── ChatListScreen.kt       # Lista de conversaciones
│   │   ├── ChatScreen.kt           # Chat individual
│   │   ├── NotificationsScreen.kt  # Notificaciones
│   │   ├── ReportsScreen.kt        # Reportes e impacto
│   │   └── TestConnectionScreen.kt # Pruebas de conexión
│   ├── network/                     # Configuración de red
│   │   ├── ApiConfig.kt            # URLs y configuración
│   │   └── ApiClient.kt            # Cliente HTTP
│   └── data/model/                  # Modelos de datos
│       ├── Product.kt              # Modelo de producto
│       ├── User.kt                 # Modelo de usuario
│       ├── Notification.kt         # Modelo de notificación
│       └── Report.kt               # Modelo de reportes
```

### 🖥️ Backend (Spring Boot + Kotlin)
```
foodsaver-backend/
├── src/main/kotlin/com/foodsaver/
│   ├── controller/                  # Controladores REST
│   │   ├── UserController.kt       # Gestión de usuarios
│   │   ├── ProductController.kt    # Gestión de productos
│   │   └── NotificationController.kt # Sistema de notificaciones
│   ├── service/                     # Lógica de negocio
│   │   ├── UserService.kt          # Servicios de usuario
│   │   ├── ProductService.kt       # Servicios de producto
│   │   └── NotificationService.kt  # Servicios de notificación
│   ├── repository/                  # Acceso a datos
│   │   ├── UserRepository.kt       # Repositorio de usuarios
│   │   ├── ProductRepository.kt    # Repositorio de productos
│   │   └── NotificationRepository.kt # Repositorio de notificaciones
│   ├── model/                       # Entidades JPA
│   │   ├── User.kt                 # Entidad Usuario
│   │   ├── Product.kt              # Entidad Producto
│   │   └── Notification.kt         # Entidad Notificación
│   └── config/                      # Configuración
│       ├── SecurityConfig.kt       # Configuración de seguridad
│       └── DataInitializer.kt      # Datos de prueba
└── src/main/resources/
    ├── application.yml              # Configuración del servidor
    └── data.sql                     # Datos iniciales
```

## 🚀 Tecnologías Utilizadas

### Frontend
- **Android Studio** - IDE de desarrollo
- **Kotlin** - Lenguaje principal
- **Jetpack Compose** - UI moderna y declarativa
- **Navigation Compose** - Navegación entre pantallas
- **Retrofit** - Cliente HTTP para API REST
- **Corrutinas** - Programación asíncrona
- **Material Design 3** - Diseño y componentes UI

### Backend
- **Spring Boot 3.1.5** - Framework principal
- **Kotlin** - Lenguaje del servidor
- **Spring Data JPA** - Persistencia de datos
- **Spring Security** - Autenticación y autorización
- **H2 Database** - Base de datos en memoria (desarrollo)
- **MySQL** - Base de datos de producción
- **Gradle** - Gestión de dependencias

## 📋 Funcionalidades por Pantalla

### 🏠 Pantalla Principal (Home)
- Dashboard con acceso rápido a todas las funciones
- Botones para publicar, buscar, ver chats, reportes y notificaciones
- Opción de prueba de conexión con el backend

### 🔍 Búsqueda de Productos
- Barra de búsqueda con filtros avanzados
- Filtros por categoría (Frutas, Verduras, etc.)
- Filtros por tipo (Venta/Donación)
- Resultados en tarjetas con información completa

### 📝 Publicar Producto
- Formulario completo con validaciones
- Selección de categorías mediante radio buttons
- Opción de venta con precio o donación gratuita
- Campo de descripción detallada
- Información de ubicación y contacto

### 💬 Sistema de Chat
- Lista de conversaciones activas
- Chat individual por producto
- Interfaz de mensajes en tiempo real
- Información del producto en el header

### 🔔 Notificaciones
- Centro de notificaciones organizado
- Diferentes tipos: nuevos productos, mensajes, etc.
- Marcado de leído/no leído
- Historial completo de notificaciones

### 📊 Reportes de Impacto
- Estadísticas personales de actividad
- Impacto ambiental calculado
- Historial de productos vendidos/donados
- Métricas de ingresos y contribución social

## 🛠️ Configuración e Instalación

### Prerrequisitos
- **Android Studio** Hedgehog o superior
- **JDK 11** o superior
- **Git** para clonar el repositorio

### Configuración del Backend

1. **Clonar el repositorio:**
```bash
git clone <repository-url>
cd Foodsaver/foodsaver-backend/foodsaver-backend
```

2. **Ejecutar el servidor:**
```bash
./gradlew bootRun
```

3. **Verificar funcionamiento:**
- Servidor: `http://localhost:8091`
- Consola H2: `http://localhost:8091/h2-console`

### Configuración del Frontend

1. **Abrir en Android Studio:**
```bash
cd Foodsaver/app
```

2. **Configurar conexión:**
- Verificar URL en [`ApiConfig.kt`](app/src/main/java/com/foodsaver/network/ApiConfig.kt)
- Para emulador: `http://10.0.2.2:8091`
- Para dispositivo físico: `http://[IP_LOCAL]:8091`

3. **Ejecutar la aplicación:**
- Conectar dispositivo o iniciar emulador
- Hacer clic en "Run" en Android Studio

## 🧪 Datos de Prueba

El sistema incluye datos de prueba predefinidos:

### Usuarios
-Prueba local : 'persona' / '12345'
- **Vendedor:** `vendor1@test.com` / `123456`
- **Comprador:** `buyer1@test.com` / `123456`
<img width="358" height="725" alt="Captura de pantalla 2025-09-30 224630" src="https://github.com/user-attachments/assets/718b876a-afac-4118-9ac0-a6b5ba1c4b40" />

<img width="369" height="779" alt="Captura de pantalla 2025-09-30 224814" src="https://github.com/user-attachments/assets/8acf5b56-9ac5-4600-8a15-3401ce2ed481" />
<img width="357" height="807" alt="Captura de pantalla 2025-09-30 224656" src="https://github.com/user-attachments/assets/abea830f-83e9-4d9f-a5f7-29d154db2709" />
<img width="367" height="806" alt="Captura de pantalla 2025-09-30 224739" src="https://github.com/user-attachments/assets/934fef3d-d350-43e8-8211-ba555ae02967" />
<img width="374" height="802" alt="Captura de pantalla 2025-09-30 224756" src="https://github.com/user-attachments/assets/0087b69a-6a72-445d-82e4-e18e9f55f738" />
<img width="369" height="779" alt="Captura de pantalla 2025-09-30 224814" src="https://github.com/user-attachments/assets/aadd75ad-f679-46e4-8161-eb8615773ffa" />





### Productos

- Tomates frescos (Venta)
- Pan integral (Donación)
- Leche próxima a vencer
- Bananos maduros

## 🔧 Pruebas y Debugging

### Pantalla de Prueba de Conexión
La app incluye una pantalla especial para verificar:
- ✅ Estado del backend
- 📦 Obtención de productos
- 🔗 Conectividad general

### Logs y Monitoreo
- Logs detallados en Android Studio
- Logs del servidor Spring Boot
- Monitoreo de requests HTTP

## 🌟 Próximas Funcionalidades

### En Desarrollo
- [ ] **Sistema de calificaciones** para vendedores
- [ ] **Mapa interactivo** con productos georreferenciados
- [ ] **Notificaciones push** nativas
- [ ] **Sistema de reservas** de productos
- [ ] **Integración con métodos de pago**

### Futuras Mejoras
- [ ] **App web** complementaria
- [ ] **Dashboard administrativo**
- [ ] **API pública** para terceros
- [ ] **Integración con redes sociales**
- [ ] **Sistema de puntos** y gamificación

## 📈 Impacto y Métricas

### Objetivos Ambientales
- 🌱 **Reducir desperdicio de alimentos**
- ♻️ **Promover economía circular**
- 🌍 **Conciencia ambiental**

### Métricas de Éxito
- Productos salvados del desperdicio
- Usuarios activos vendedores/compradores
- Transacciones exitosas (ventas + donaciones)
- Impacto ambiental medido

## 🤝 Contribuir al Proyecto

### Cómo Contribuir
1. **Fork** el repositorio
2. Crear una **rama** para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. **Commit** tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. **Push** a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abrir un **Pull Request**

### Reportar Bugs
- Usar las **Issues** de GitHub
- Incluir pasos para reproducir
- Adjuntar logs si es posible

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## 👥 Equipo de Desarrollo

- **Desarrollador Principal:** Edinson Caceres- Yineth camila pardo
- **Backend:** Spring Boot + Kotlin
- **Frontend:** Android + Jetpack Compose

## 📞 Contacto y Soporte

- **Email:** foodsaver.support@example.com
- **GitHub Issues:** Para reportar bugs y solicitar features
- **Documentación:** Ver carpetas individuales para detalles técnicos

---

**FoodSaver** - *Transformando el desperdicio en oportunidades* 🌱🍎📱

*Última actualización: Octubre 2025*

## 👥 Equipo de desarrollo

- **Camila Pardo** - Desarrollo Frontend
- **Edinson Cáceres** - Desarrollo Backend

## 📞 Contacto

Para preguntas o sugerencias, puedes contactarnos a través de:
- Email:ecaceres06@uan.edu.co
- GitHub Issues: [Crear un issue](https://github.com/tu-usuario/foodsaver/issues)

---
# FOODSAVER

[![Android CI/CD](https://github.com/tu-usuario/foodsaver/actions/workflows/android.yml/badge.svg)](https://github.com/Edinson99/foodsaver/actions/workflows/android.yml)
[![Backend CI/CD](https://github.com/tu-usuario/foodsaver/actions/workflows/backend.yml/badge.svg)](https://github.com/Edinson99/foodsaver/actions/workflows/backend.yml)
[![Integration Tests](https://github.com/tu-usuario/foodsaver/actions/workflows/integration.yml/badge.svg)](https://github.com/Edinson99/foodsaver/actions/workflows/integration.yml)

**FoodSaver** - Reduciendo el desperdicio de alimentos, un producto a la vez 🌱
**PRESENTACION DEL PROYECTO**
[FOOD SAVER PRESENTACIÓN.pptx](https://github.com/user-attachments/files/23538431/FOOD.SAVER.PRESENTACION.pptx)

