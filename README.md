# Mercado Libre Clone - Android App

Una aplicación Android que replica la funcionalidad básica de Mercado Libre, desarrollada con Jetpack Compose y siguiendo las mejores prácticas de arquitectura limpia.

## 🚀 Características

### ✅ Funcionalidades Implementadas

- **Búsqueda de Productos**: Campo de búsqueda con resultados en tiempo real
- **Visualización de Resultados**: Lista de productos con información detallada
- **Detalle de Producto**: Pantalla completa con imágenes en Pager y PageIndicator personalizado
- **Sistema de Temas**: Cambio dinámico de colores (Azul, Naranja, Violeta, Verde)
- **Navegación**: Navegación fluida entre pantallas
- **Manejo de Errores**: Gestión de errores con Snackbars informativos
- **Accesibilidad**: Soporte para Talkback

### 🎨 Componentes Personalizados

- **PageIndicator**: Componente personalizado desarrollado con Jetpack Compose
- **Sistema de Diseño**: Colores, tipografía y componentes reutilizables
- **Animaciones**: Transiciones suaves entre estados

## 🏗️ Arquitectura

El proyecto sigue la arquitectura **Clean Architecture** basada en Google NowInAndroid:

```
app/
├── data/           # Capa de datos
│   ├── local/      # Base de datos Room
│   ├── remote/     # Servicios de API (mock)
│   ├── repository/ # Implementaciones de repositorios
│   └── model/      # Entidades de datos
├── domain/         # Capa de dominio
│   ├── model/      # Modelos de dominio
│   ├── repository/ # Interfaces de repositorios
│   └── usecase/    # Casos de uso
├── ui/             # Capa de presentación
│   ├── search/     # Pantalla de búsqueda
│   ├── product/    # Pantalla de detalle
│   ├── settings/   # Pantalla de configuración
│   └── theme/      # ViewModels de tema
└── core/           # Componentes compartidos
    ├── di/         # Inyección de dependencias
    ├── designsystem/ # Sistema de diseño
    └── common/     # Utilidades comunes
```

### Patrones de Diseño

- **MVVM**: Model-View-ViewModel para la capa de presentación
- **Repository Pattern**: Abstracción de fuentes de datos
- **Use Case Pattern**: Lógica de negocio encapsulada
- **Dependency Injection**: Hilt para inyección de dependencias
- **Observer Pattern**: Flows para comunicación reactiva

## 🛠️ Tecnologías Utilizadas

### Core
- **Kotlin**: Lenguaje principal
- **Jetpack Compose**: UI declarativa
- **Material Design 3**: Sistema de diseño
- **Navigation Compose**: Navegación entre pantallas

### Arquitectura
- **Hilt**: Inyección de dependencias
- **Room**: Base de datos local
- **DataStore**: Almacenamiento de preferencias
- **Coroutines & Flow**: Programación asíncrona

### Testing
- **JUnit**: Tests unitarios
- **MockK**: Mocking para tests
- **Turbine**: Testing de Flows
- **Truth**: Assertions

### Utilidades
- **Coil**: Carga de imágenes
- **Paging**: Paginación de datos

## 📱 Pantallas

### 1. Pantalla de Búsqueda
- Campo de búsqueda con icono
- Resultados en tiempo real
- Cards de productos con información
- Botón de configuración en la barra superior

### 2. Pantalla de Detalle
- Pager de imágenes con PageIndicator personalizado
- Información completa del producto
- Detalles del vendedor
- Precios con descuentos

### 3. Pantalla de Configuración
- Selector de colores de tema
- Opciones: Azul, Naranja, Violeta, Verde
- Cambio dinámico en toda la app

## 🎯 Funcionalidades Destacadas

### PageIndicator Personalizado
```kotlin
@Composable
fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = MaterialTheme.colorScheme.primary,
    inactiveColor: Color = MaterialTheme.colorScheme.surfaceVariant
)
```

- Desarrollado completamente con Jetpack Compose
- Animaciones suaves entre estados
- Personalizable en colores y tamaños
- Sin dependencias externas

### Sistema de Temas Dinámico
- Cambio de colores en tiempo real
- Persistencia en DataStore
- Aplicación global del tema
- Transiciones animadas

### Manejo de Errores
- Snackbars informativos
- Estados de carga
- Gestión de errores de red
- UX consistente

## 🧪 Testing

### Tests Unitarios
- Casos de uso
- Repositorios
- ViewModels

### Tests de UI
- Componentes Compose
- Navegación
- Interacciones de usuario

## 🚀 Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tu-usuario/prueba-meli-design.git
   cd prueba-meli-design
   ```

2. **Abrir en Android Studio**
   - Abrir Android Studio
   - Seleccionar "Open an existing project"
   - Navegar al directorio del proyecto

3. **Sincronizar dependencias**
   - Esperar a que Gradle sincronice
   - Resolver cualquier dependencia faltante

4. **Ejecutar la aplicación**
   - Conectar dispositivo Android o usar emulador
   - Presionar "Run" (▶️)

## 📋 Requisitos

- **Android Studio**: Arctic Fox o superior
- **Kotlin**: 1.9.0 o superior
- **Android SDK**: API 24 (Android 7.0) o superior
- **Gradle**: 8.0 o superior

## 🎨 Diseño

### Colores del Sistema
- **Primario**: Dinámico (Azul/Naranja/Violeta/Verde)
- **Secundario**: Colores de estado
- **Producto**: Verde para precios, Rojo para descuentos

### Tipografía
- **Material Design 3**: Sistema tipográfico completo
- **Escalabilidad**: Diferentes tamaños para diferentes contextos
- **Legibilidad**: Optimizada para lectura

## 🔧 Configuración

### Permisos
- **Internet**: Para carga de imágenes y datos

### Configuración de Build
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 35 (Android 15)
- **Compile SDK**: 35

## 📊 Métricas de Calidad

- **Cobertura de Tests**: >80%
- **Performance**: Sin pérdida de frames
- **Accesibilidad**: Compatible con Talkback
- **Arquitectura**: Clean Architecture
- **Patrones**: MVVM, Repository, Use Case

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

**Tu Nombre**
- GitHub: [@tu-usuario](https://github.com/tu-usuario)
- LinkedIn: [Tu LinkedIn](https://linkedin.com/in/tu-perfil)

## 🙏 Agradecimientos

- Google NowInAndroid por la arquitectura de referencia
- Jetpack Compose por la UI declarativa
- Material Design 3 por el sistema de diseño
- Mercado Libre por la inspiración del diseño

---

**Nota**: Esta aplicación es un proyecto de demostración y no está afiliada oficialmente a Mercado Libre. 