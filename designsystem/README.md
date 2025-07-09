# Design System Module

Este módulo contiene todos los componentes de diseño reutilizables para la aplicación.

## Componentes disponibles

### UI Components
- `HomeTopBar` - Barra superior de la pantalla principal
- `OfficialStoreBadge` - Badge para tiendas oficiales
- `RatingStars` - Sistema de calificación con estrellas
- `GridListToggleButtons` - Botones para cambiar entre vista de lista y grilla

### Uso

```kotlin
// En tu módulo app, agrega la dependencia:
implementation(project(":designsystem"))

// Luego usa los componentes:
import com.hninor.pruebamelidesign.designsystem.component.HomeTopBar
import com.hninor.pruebamelidesign.designsystem.component.OfficialStoreBadge
import com.hninor.pruebamelidesign.designsystem.component.RatingStars

@Composable
fun MyScreen() {
    HomeTopBar(
        isGrid = false,
        onToggleView = { /* ... */ },
        searchQuery = "",
        onSearchClick = { /* ... */ }
    )
    
    OfficialStoreBadge(brand = "Apple")
    
    RatingStars(rating = 4.5, reviews = 100)
}
```

## Estructura del módulo

```
designsystem/
├── src/main/
│   ├── java/com/hninor/pruebamelidesign/designsystem/
│   │   └── component/
│   │       ├── HomeTopBar.kt
│   │       ├── OfficialStoreBadge.kt
│   │       ├── RatingStars.kt
│   │       └── GridListToggleButtons.kt
│   └── res/
│       └── drawable/
│           ├── star_rate.xml
│           ├── star_half.xml
│           └── star_outline.xml
```

## Convertir a librería

Para convertir este módulo en una librería independiente:

1. Cambiar `android.library` por `android.library` en build.gradle.kts
2. Publicar en Maven Central o JitPack
3. Actualizar las dependencias en otros proyectos 