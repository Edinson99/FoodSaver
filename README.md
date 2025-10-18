# FOODSAVER

Desarrollar una aplicación móvil que conecte a fruvers, tiendas y distribuidores de alimentos con restaurantes, fundaciones, comedores comunitarios y consumidores, permitiendo la publicación de productos próximos a vencerse para su venta a menor costo o su donación, con el fin de reducir el desperdicio de alimentos y generar un impacto económico, social y ambiental positivo.

**Autor(es):** CAMILA PARDO Y EDINSON CACERES

## 🚀 Características principales

- **Gestión de usuarios:** Registro como vendedores o compradores
- **Publicación de productos:** Con información detallada y fotografías
- **Sistema de donaciones:** Productos gratuitos para fundaciones
- **Notificaciones en tiempo real:** Alertas de nuevos productos
- **Búsqueda avanzada:** Filtros por categoría, ubicación y tipo

## 📱 Tecnologías utilizadas

### Frontend (Android)
- **Kotlin** - Lenguaje principal
- **Jetpack Compose** - UI moderna y declarativa
- **Material Design 3** - Componentes de interfaz
- **Retrofit** - Cliente HTTP para API REST
- **Navigation Component** - Navegación entre pantallas

### Backend
- **Spring Boot** - Framework principal
- **Kotlin** - Lenguaje del servidor
- **JPA/Hibernate** - ORM para base de datos
- **H2 Database** - Base de datos en desarrollo
- **Spring Security** - Autenticación y autorización

## 🏗️ Arquitectura del proyecto

```
📁 FoodSaver/
├── 📁 app/ (Android)
│   ├── 📁 src/main/java/com/example/foodsaver/
│   │   ├── 📁 data/
│   │   │   ├── 📁 model/        # Modelos de datos
│   │   │   ├── 📁 network/      # APIs y DTOs
│   │   │   └── 📁 repository/   # Repositorios
│   │   ├── 📁 ui/               # Pantallas y componentes
│   │   └── MainActivity.kt
│   └── 📁 build.gradle.kts
└── 📁 foodsaver-backend/ (Spring Boot)
    ├── 📁 src/main/kotlin/com/foodsaver/
    │   ├── 📁 model/            # Entidades JPA
    │   ├── 📁 repository/       # Repositorios Spring Data
    │   ├── 📁 service/          # Lógica de negocio
    │   ├── 📁 controller/       # Controladores REST
    │   └── 📁 config/           # Configuraciones
    └── 📁 build.gradle.kts
```

## 🔧 Instalación y configuración

### Prerrequisitos
- **Android Studio** (Arctic Fox o superior)
- **IntelliJ IDEA** (para el backend)
- **JDK 17** o superior
- **Git**

### Configuración del Backend
1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/foodsaver.git
   cd foodsaver
   ```

2. Abre el proyecto backend en IntelliJ IDEA:
   ```bash
   cd foodsaver-backend
   ./gradlew bootRun
   ```

3. El servidor estará disponible en: `http://localhost:8080`

### Configuración del Android
1. Abre Android Studio
2. Importa el proyecto desde la carpeta `app/`
3. Sincroniza Gradle
4. Ejecuta en emulador o dispositivo

## 📋 Funcionalidades implementadas

### ✅ Completadas
- [x] Pantallas de bienvenida y autenticación
- [x] Navegación entre pantallas
- [x] Modelos de datos (Android y Backend)
- [x] APIs REST básicas
- [x] Configuración de red y conectividad
- [x] Pantalla de búsqueda con filtros
- [x] Pantalla de publicación de productos

### 🔄 En desarrollo
- [ ] Autenticación JWT completa
- [ ] Sistema de notificaciones
- [ ] Chat entre usuarios
- [ ] Reportes y estadísticas
- [ ] Carga de imágenes

### 📋 Por implementar
- [ ] Geolocalización
- [ ] Notificaciones push
- [ ] Base de datos en producción
- [ ] Tests unitarios e integración

## 🌐 APIs disponibles

### Usuarios
- `POST /api/auth/login` - Iniciar sesión
- `POST /api/auth/register` - Registrar usuario
- `GET /api/users/{id}` - Obtener usuario por ID

### Productos
- `GET /api/products` - Listar todos los productos
- `GET /api/products/available` - Productos disponibles
- `POST /api/products` - Crear nuevo producto
- `GET /api/products/search` - Buscar productos con filtros

### Notificaciones
- `GET /api/notifications/user/{userId}` - Notificaciones del usuario
- `POST /api/notifications` - Crear notificación
- `PUT /api/notifications/{id}/read` - Marcar como leída

## 🤝 Contribuir

1. Fork del proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 👥 Equipo de desarrollo

- **Camila Pardo** - Desarrollo Frontend
- **Edinson Cáceres** - Desarrollo Backend

## 📞 Contacto

Para preguntas o sugerencias, puedes contactarnos a través de:
- Email:ecaceres06@uan.edu.co
- GitHub Issues: [Crear un issue](https://github.com/tu-usuario/foodsaver/issues)

---

**FoodSaver** - Reduciendo el desperdicio de alimentos, un producto a la vez 🌱
