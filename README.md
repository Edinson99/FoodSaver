FOODSAVER

Desarrollar una aplicación móvil que conecte a fruvers, tiendas y distribuidores de alimentos con restaurantes, fundaciones, comedores comunitarios y consumidores, permitiendo la publicación de productos próximos a 
vencerse para su venta a menor costo o su donación, con el fin de reducir el desperdicio de alimentos y generar un impacto económico, social y ambiental positivo

Autor(es): CAMILA PARDO Y EDISON CACERES

## Referencias

- [Ideas iniciales de proyecto](docs/ideas.md)
- 1. Gestor de Tareas con Prioridades y Recordatorios
Descripción: Una app para organizar tareas y proyectos, con prioridades, fechas límite, notificaciones y recordatorios.
Funciones: CRUD de tareas, categorías, integración con calendario, notificaciones push, persistencia con Room (SQLite).
Desafío: Manejo avanzado de bases de datos, notificaciones, UI moderna con Jetpack Compose.
2. App de Control de Gastos Personales
Descripción: Permite registrar ingresos y gastos, clasificar por categorías, mostrar gráficos de evolución y alertas si se pasa el presupuesto.
Funciones: CRUD de transacciones, gráficos (MPAndroidChart), exportar datos a CSV, uso de Room.
Desafío: Manejo de datos, gráficos, buenas prácticas en UX para entrada rápida de datos.
3. App para Seguimiento de Hábitos
Descripción: Permite crear hábitos, registrar progreso diario, visualizar estadísticas y enviar motivaciones.
Funciones: Crear hábitos, check-in diario, gráficos, notificaciones.
Desafío: Persistencia de datos, manejo de estados complejos, UX para motivar al usuario.

4. App de Consulta de API de Clima con Mapas
Descripción: Consulta clima actual y pronóstico en diferentes ciudades con mapa interactivo.
Funciones: Integrar API externa (OpenWeatherMap), mostrar datos en UI, mapa con Google Maps SDK.
Desafío: Manejo de API REST, mapas, actualización en tiempo real.

- [Funcionalidades de la aplicación](docs/funcionalidades.md)

- Gestión de usuarios
La aplicación debe permitir el registro de usuarios como vendedores (fruvers, tiendas) o compradores/beneficiarios (restaurantes, fundaciones, comedores).
Los usuarios deben poder iniciar sesión y cerrar sesión con credenciales seguras.

Gestión de productos
Los vendedores deben poder publicar productos indicando: nombre, categoría, cantidad, precio (si aplica) y fotografía.
Los productos deben poder clasificarse como venta o donación.
Los vendedores deben poder editar o eliminar sus publicaciones.
Los productos deben poder marcarse como vendidos o donados.

Notificaciones y alertas
La aplicación debe enviar notificaciones en tiempo real a los compradores cuando se publique un nuevo producto de interés.
Los usuarios deben poder configurar qué tipo de notificaciones desean recibir (categoría, ubicación, tipo de producto).

Búsqueda y filtrado de productos
Los compradores deben poder buscar y filtrar productos por:
Categoría (frutas, verduras, lácteos, etc.).
Estado (venta o donación).
Ubicación.


- [Diseño de la interfaz de usuario](docs/ui.md)

<img width="508" height="686" alt="image" src="https://github.com/user-attachments/assets/ec05aee1-61d8-4797-80cd-e27c7e6e851b" />


![1RE](https://github.com/user-attachments/assets/5daf7fa4-8383-4fdd-87f0-1ef6cc0d1a18)
![2RE](https://github.com/user-attachments/assets/b4c8e11a-cfa6-4a5c-a5ef-8cf674aeb87b)
![3re](https://github.com/user-attachments/assets/f6ddd136-0efc-4cc0-9b37-815315008cdd)
![4re](https://github.com/user-attachments/assets/8e42c710-3612-4304-b61b-49f5192ddc5a)
![5re](https://github.com/user-attachments/assets/7735dff3-014e-4a40-8d99-d354c9b5f8f1)
