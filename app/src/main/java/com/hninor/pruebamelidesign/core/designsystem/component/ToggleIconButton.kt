package com.hninor.pruebamelidesign.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ToggleIconButton(
    isActive: Boolean,
    activeIcon: ImageVector,
    inactiveIcon: ImageVector = activeIcon,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isActive) activeIcon else inactiveIcon,
            contentDescription = contentDescription,
            tint = if (isActive) MaterialTheme.colorScheme.secondary else Color.Black.copy(alpha = 0.4f),
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
fun GridListToggleButtons(
    isGrid: Boolean,
    onToggleView: (Boolean) -> Unit
) {
    ToggleIconButton(
        isActive = isGrid,
        activeIcon = Icons.Default.ViewModule,
        inactiveIcon = Icons.Default.ViewModule,
        contentDescription = "Ver como grilla",
        onClick = { onToggleView(true) }
    )
    ToggleIconButton(
        isActive = !isGrid,
        activeIcon = Icons.Default.List,
        inactiveIcon = Icons.Default.List,
        contentDescription = "Ver como lista",
        onClick = { onToggleView(false) }
    )
} 