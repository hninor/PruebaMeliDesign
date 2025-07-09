# Mercado Libre Clone - Android App

Una aplicación Android que replica la funcionalidad básica de Mercado Libre, desarrollada con Jetpack Compose y siguiendo las mejores prácticas de arquitectura limpia y modularización.

## 🚀 Características

### ✅ Funcionalidades Implementadas

- **Búsqueda de Productos**: Campo de búsqueda con resultados en tiempo real
- **Visualización de Resultados**: Lista y grilla de productos con información detallada
- **Detalle de Producto**: Pantalla completa con galería de imágenes usando Pager
- **Sistema de Temas**: Cambio dinámico de colores (Azul, Naranja, Violeta, Verde)
- **Navegación**: Navegación fluida entre pantallas con animaciones
- **Manejo de Errores**: Gestión de errores con Snackbars informativos
- **Accesibilidad**: Soporte para Talkback y navegación por teclado

### 🎨 Componentes Personalizados

- **PageIndicator**: Componente personalizado desarrollado con Jetpack Compose
- **Sistema de Diseño**: Colores, tipografía y componentes reutilizables
- **Animaciones**: Transiciones suaves entre estados y vistas
- **Galería de Imágenes**: Pager horizontal con indicadores

## 🏗️ Arquitectura

El proyecto sigue la arquitectura **Clean Architecture** con modularización:

```
PruebaMeliDesign/
├── app/                    # Módulo principal de la aplicación
│   ├── data/              # Capa de datos
│   │   ├── local/         # Base de datos Room
│   │   ├── remote/        # Servicios de API (mock)
│   │   ├── repository/    # Implementaciones de repositorios
│   │   └── model/         # Entidades de datos
│   ├── domain/            # Capa de dominio
│   │   ├── model/         # Modelos de dominio
│   │   ├── repository/    # Interfaces de repositorios
│   │   └── usecase/       # Casos de uso
│   ├── ui/                # Capa de presentación
│   │   ├── home/          # Pantalla principal
│   │   ├── search/        # Pantalla de búsqueda
│   │   ├── product/       # Pantalla de detalle
│   │   ├── settings/      # Pantalla de configuración
│   │   ├── navigation/    # Navegación
│   │   └── theme/         # ViewModels de tema
│   └── core/              # Componentes compartidos
│       ├── di/            # Inyección de dependencias
│       └── designsystem/  # Sistema de diseño
├── designsystem/          # Módulo de sistema de diseño
│   ├── component/         # Componentes reutilizables
│   ├── theme/            # Temas y colores
│   └── type/             # Tipografía
└── gradle/               # Configuración de dependencias
    └── libs.versions.toml
```

## 📦 Modularización

### 🎯 Beneficios de la Modularización

- **Separación de Responsabilidades**: Cada módulo tiene una responsabilidad específica
- **Reutilización**: Componentes del sistema de diseño pueden ser reutilizados
- **Testing Independiente**: Cada módulo puede ser testeado de forma aislada
- **Escalabilidad**: Fácil agregar nuevos módulos sin afectar existentes
- **Compilación Rápida**: Solo se recompilan los módulos modificados

### 📋 Estructura de Módulos

#### **app** (Módulo Principal)
- **Responsabilidad**: Lógica de negocio y presentación
- **Dependencias**: designsystem, Hilt, Room, Navigation
- **Contenido**: 
  - ViewModels y pantallas
  - Repositorios y casos de uso
  - Configuración de DI

#### **designsystem** (Módulo de Diseño)
- **Responsabilidad**: Componentes UI reutilizables
- **Dependencias**: Compose UI, Material 3
- **Contenido**:
  - Componentes personalizados (PageIndicator, ProductImageGallery, etc.)
  - Sistema de colores y temas
  - Tipografía y estilos

### 🔧 Configuración de Módulos

```kotlin
// settings.gradle.kts
include(":app")
include(":designsystem")

// build.gradle.kts (app)
dependencies {
    implementation(project(":designsystem"))
    // Otras dependencias...
}
```

### 🧪 Testing por Módulos

```kotlin
// Tests en app module
@RunWith(MockitoJUnitRunner::class)
class SearchProductsUseCaseTest

// Tests en designsystem module  
@Composable
fun PageIndicatorTest()
```

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

### 1. Pantalla Principal (Home)
- Vista de lista y grilla de productos
- Filtros por categorías
- Búsqueda integrada
- Animaciones de transición

### 2. Pantalla de Búsqueda
- Campo de búsqueda con icono
- Resultados en tiempo real
- Cards de productos con información
- Historial de búsquedas

### 3. Pantalla de Detalle
- Pager de imágenes con PageIndicator personalizado
- Información completa del producto
- Detalles del vendedor
- Precios con descuentos

### 4. Pantalla de Configuración
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

### Galería de Imágenes con Pager
```kotlin
@Composable
fun ProductImageGallery(
    images: List<String>,
    productTitle: String,
    isFavorite: Boolean,
    onFavoriteToggle: () -> Unit
)
```

- Pager horizontal nativo de Compose
- Indicador de páginas personalizado
- Contador de imágenes
- Botón de favorito integrado

### Sistema de Temas Dinámico
- Cambio de colores en tiempo real
- Persistencia en DataStore
- Aplicación global del tema
- Transiciones animadas

### Animaciones Avanzadas
- Transiciones entre vista de lista y grilla
- Animaciones de escala en cards
- Animaciones de entrada con stagger
- Animaciones de pager

## 🧪 Testing

### Tests Unitarios
- Casos de uso
- Repositorios
- ViewModels

### Tests de UI
- Componentes Compose
- Navegación
- Interacciones de usuario

### Tests de Integración
- Flujos completos de usuario
- Persistencia de datos
- Cambios de tema

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
- **Arquitectura**: Clean Architecture con modularización
- **Patrones**: MVVM, Repository, Use Case

## 🤝 Contribución

1. Fork el proyecto
2. Crear una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abrir un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

## 👨‍💻 Autor

**Tu Nombre** - [@tu-usuario](https://github.com/tu-usuario)

---

⭐ Si este proyecto te ayudó, ¡dale una estrella! 