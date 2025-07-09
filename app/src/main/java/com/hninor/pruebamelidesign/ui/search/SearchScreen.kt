package com.hninor.pruebamelidesign.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.NorthEast
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import coil.compose.AsyncImage
import com.hninor.pruebamelidesign.core.designsystem.theme.PriceColor
import com.hninor.pruebamelidesign.core.designsystem.theme.DiscountColor
import com.hninor.pruebamelidesign.domain.model.Product
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.semantics.semantics

val defaultSuggestions = listOf(
    "iphone", "samsung", "notebook", "auriculares", "playstation", "xiaomi", "monitor", "teclado", "mouse", "impresora"
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val initialQuery = navController.previousBackStackEntry?.savedStateHandle?.get<String>("search_query") ?: ""
    var searchQuery by remember {
        mutableStateOf(
            if (initialQuery.isNotEmpty())
                TextFieldValue(initialQuery, TextRange(initialQuery.length))
            else
                TextFieldValue("")
        )
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val uiState by viewModel.uiState.collectAsState()
    val recentSearches = remember { mutableStateListOf<String>() }

    // Solicita el foco y muestra el teclado al entrar
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    // Si cambia el parámetro inicial, actualiza el campo y el cursor
    LaunchedEffect(initialQuery) {
        if (initialQuery != searchQuery.text) {
            searchQuery = TextFieldValue(initialQuery, TextRange(initialQuery.length))
        }
    }

    // Búsqueda en tiempo real
    LaunchedEffect(searchQuery.text) {
        viewModel.searchProducts(searchQuery.text)
    }

    fun addRecentSearch(query: String) {
        if (query.isBlank()) return
        recentSearches.remove(query)
        recentSearches.add(0, query)
        if (recentSearches.size > 10) recentSearches.removeLast()
    }

    fun triggerSearch(query: String) {
        addRecentSearch(query)
        navController.previousBackStackEntry?.savedStateHandle?.set("search_query", query)
        keyboardController?.hide()
        navController.popBackStack()
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Column(
                Modifier.background(Color.White)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 4.dp, end = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.Black
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        TextField(
                            value = searchQuery,
                            onValueChange = { searchQuery = it },
                            placeholder = { Text("Buscar en Mercado Libre", color = Color.Gray) },
                            singleLine = true,
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                                cursorColor = Color.Black,
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent,
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .focusRequester(focusRequester),
                            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    if (searchQuery.text.isNotBlank()) {
                                        triggerSearch(searchQuery.text)
                                    }
                                }
                            ),
                            enabled = true,
                            readOnly = false,
                            trailingIcon = {
                                if (searchQuery.text.isNotBlank()) {
                                    IconButton(onClick = { searchQuery = TextFieldValue("", TextRange(0)) }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Limpiar búsqueda"
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
                Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 2.dp)
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            if (uiState.isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    // Sugerencias de productos (coincidencias)
                    if (searchQuery.text.isNotBlank() && uiState.searchResults.isNotEmpty()) {
                        items(uiState.searchResults) { product ->
                            SuggestionRow(
                                text = product.title,
                                iconLeft = Icons.Default.History,
                                iconRight = Icons.Default.NorthEast,
                                onClick = { triggerSearch(product.title) },
                                onIconRightClick = { searchQuery = TextFieldValue(product.title, TextRange(product.title.length)) }
                            )
                            Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 1.dp)
                        }
                    }
                    // Sugerencias por defecto o búsquedas recientes
                    if (searchQuery.text.isBlank() || uiState.searchResults.isEmpty()) {
                        val suggestions = if (recentSearches.isEmpty()) defaultSuggestions else recentSearches
                        items(suggestions) { suggestion ->
                            SuggestionRow(
                                text = suggestion,
                                iconLeft = Icons.Default.History,
                                iconRight = Icons.Default.NorthEast,
                                onClick = { triggerSearch(suggestion) },
                                onIconRightClick = { searchQuery = TextFieldValue(suggestion, TextRange(suggestion.length)) }
                            )
                            Divider(color = MaterialTheme.colorScheme.outlineVariant, thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SuggestionRow(
    text: String,
    iconLeft: androidx.compose.ui.graphics.vector.ImageVector?,
    iconRight: androidx.compose.ui.graphics.vector.ImageVector?,
    onClick: () -> Unit,
    onIconRightClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (iconLeft != null) {
            Icon(
                imageVector = iconLeft,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        if (iconRight != null) {
            IconButton(
                onClick = {
                    onIconRightClick()
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = iconRight,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultCard(
    product: Product,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp).semantics(mergeDescendants = true) {},
            verticalAlignment = Alignment.Top
        ) {
            // Imagen del producto
            AsyncImage(
                model = product.images.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .size(60.dp)
                    .padding(end = 12.dp)
            )
            
            // Información del producto
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${product.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = PriceColor
                    )
                    
                    product.originalPrice?.let { originalPrice ->
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(
                            text = "$${originalPrice}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = DiscountColor
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = product.seller.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
} 