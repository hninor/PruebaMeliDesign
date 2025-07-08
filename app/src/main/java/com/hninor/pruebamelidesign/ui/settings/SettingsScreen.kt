package com.hninor.pruebamelidesign.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hninor.pruebamelidesign.core.designsystem.theme.Blue
import com.hninor.pruebamelidesign.core.designsystem.theme.Orange
import com.hninor.pruebamelidesign.core.designsystem.theme.Violet
import com.hninor.pruebamelidesign.core.designsystem.theme.Green
import com.hninor.pruebamelidesign.domain.model.ThemeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val currentTheme by viewModel.currentTheme.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Color del tema",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Selecciona el color principal de la aplicación",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Opciones de color
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ThemeColorOption(
                    themeColor = ThemeColor.BLUE,
                    color = Blue,
                    displayName = "Azul",
                    isSelected = currentTheme == ThemeColor.BLUE,
                    onSelect = { viewModel.setTheme(ThemeColor.BLUE) }
                )
                
                ThemeColorOption(
                    themeColor = ThemeColor.ORANGE,
                    color = Orange,
                    displayName = "Naranja",
                    isSelected = currentTheme == ThemeColor.ORANGE,
                    onSelect = { viewModel.setTheme(ThemeColor.ORANGE) }
                )
                
                ThemeColorOption(
                    themeColor = ThemeColor.VIOLET,
                    color = Violet,
                    displayName = "Violeta",
                    isSelected = currentTheme == ThemeColor.VIOLET,
                    onSelect = { viewModel.setTheme(ThemeColor.VIOLET) }
                )
                
                ThemeColorOption(
                    themeColor = ThemeColor.GREEN,
                    color = Green,
                    displayName = "Verde",
                    isSelected = currentTheme == ThemeColor.GREEN,
                    onSelect = { viewModel.setTheme(ThemeColor.GREEN) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeColorOption(
    themeColor: ThemeColor,
    color: Color,
    displayName: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Card(
        onClick = onSelect,
        modifier = Modifier.fillMaxWidth().semantics(mergeDescendants = true) {},
        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 4.dp else 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer 
            else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Círculo de color
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(color)
                    .border(
                        width = if (isSelected) 3.dp else 1.dp,
                        color = if (isSelected) MaterialTheme.colorScheme.primary 
                        else MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
                    .semantics {
                        contentDescription = if (isSelected) "Color $displayName seleccionado" else "Color $displayName"
                    }
            )
            
            Spacer(modifier = Modifier.size(16.dp))
            
            Text(
                text = displayName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Icono de selección
            if (isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Seleccionado",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
} 