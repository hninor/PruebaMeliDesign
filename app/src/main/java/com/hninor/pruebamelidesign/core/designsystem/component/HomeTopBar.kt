package com.hninor.pruebamelidesign.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hninor.pruebamelidesign.core.designsystem.component.GridListToggleButtons

@Composable
fun HomeTopBar(
    isGrid: Boolean,
    onToggleView: (Boolean) -> Unit,
    searchQuery: String,
    onSearchClick: (String?) -> Unit,
    onSettingsClick: (() -> Unit)? = null,
    address: String = "Calle Posta 4789",
    showBack: Boolean = false,
    onBack: (() -> Unit)? = null,
    showActions: Boolean = true
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        // Primera fila: back opcional, campo de búsqueda y settings
        Row(
            modifier = Modifier
                .fillMaxWidth()
       ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showBack && onBack != null) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }

            }
            // Campo de búsqueda clickeable
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp)
                    .padding(start = 16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
                    .clickable { onSearchClick(if (searchQuery.isNotBlank()) searchQuery else null) },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (searchQuery.isBlank()) "Buscar..." else searchQuery,
                    color = if (searchQuery.isBlank()) Color(0xFFBDBDBD) else Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
            }
            if (showActions) {
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(onClick = { onSettingsClick?.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Configuración",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
            } else {
                Spacer(modifier = Modifier.width(12.dp + 28.dp)) // Ocupa el espacio del icono
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Segunda fila: dirección y vista
        Row(
            modifier = Modifier
                .fillMaxWidth()
  ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Acción al hacer clic */ }) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Ubicación",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }


            Text(
                text = address,
                color = Color.Black,
                fontSize = 15.sp
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Cambiar dirección",
                tint = Color.Black,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            if (showActions) {
                // Iconos de grilla y lista, separados
                GridListToggleButtons(
                    isGrid = isGrid,
                    onToggleView = onToggleView
                )
            } else {
                Spacer(modifier = Modifier.width(28.dp * 2).height(28.dp)) // Ocupa el espacio de los dos iconos
            }
        }
    }
} 