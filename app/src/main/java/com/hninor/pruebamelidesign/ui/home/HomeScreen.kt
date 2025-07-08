package com.hninor.pruebamelidesign.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hninor.pruebamelidesign.R
import com.hninor.pruebamelidesign.core.designsystem.component.HomeTopBar
import com.hninor.pruebamelidesign.core.designsystem.component.MeliChip
import com.hninor.pruebamelidesign.core.designsystem.component.RatingStars
import com.hninor.pruebamelidesign.domain.model.Product
import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(value: Number, currency: String = "$ "): String {
    val format = NumberFormat.getNumberInstance(Locale("es", "CO"))
    return "$currency${format.format(value)}"
}

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    var isGrid by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val systemUiController = rememberSystemUiController()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(navController) {
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("search_query")
            ?.observe(lifecycleOwner) { query ->
                searchQuery = query ?: ""
            }
    }

    systemUiController.setStatusBarColor(MaterialTheme.colorScheme.primary)

    val onClearSearch: () -> Unit = {
        searchQuery = ""
        navController.currentBackStackEntry?.savedStateHandle?.remove<String>("search_query")
    }
    val onSettingsClick: () -> Unit = {
        navController.navigate("settings") {
            popUpTo("home")
            launchSingleTop = true
        }
    }
    val onToggleView: (Boolean) -> Unit = { isGrid = !isGrid }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        HomeTopBar(
            isGrid = isGrid,
            onToggleView = onToggleView,
            searchQuery = searchQuery,
            onSearchClick = { initialText ->
                navController.navigate("search") {
                    popUpTo("home")
                    launchSingleTop = true
                    if (!initialText.isNullOrBlank()) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            "search_query",
                            initialText
                        )
                    }
                }
            },
            onSettingsClick = onSettingsClick,
            showBack = searchQuery.isNotBlank(),
            onBack = if (searchQuery.isNotBlank()) onClearSearch else null
        )
        FilterChips()
        val filteredProducts = viewModel.filterProducts(searchQuery)
        AnimatedContent(targetState = isGrid) { grid ->
            if (grid) {
                ProductGrid(products = filteredProducts, onProductClick = { product ->
                    navController.navigate("product/${product.id}") {
                        popUpTo("home")
                        launchSingleTop = true
                    }
                })
            } else {
                ProductList(products = filteredProducts, onProductClick = { product ->
                    navController.navigate("product/${product.id}") {
                        popUpTo("home")
                        launchSingleTop = true
                    }
                })
            }
        }
    }
}

@Composable
fun FilterChips() {
    val scrollState = rememberScrollState()
    var selectedChip by remember { mutableStateOf(0) }
    val chips = listOf(
        Triple("Llega mañana", Icons.Default.LocalShipping, true),
        Triple("Mejor precio en cuotas", null, false),
        Triple("Enviado por", null, false),
        Triple("Disponible en 3 colores", null, false),
        Triple("Apple Tienda Oficial", Icons.Default.Verified, false),
        Triple("Envío gratis", Icons.Default.Star, false)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .horizontalScroll(scrollState)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chips.forEachIndexed { index, (text, icon, _) ->
            MeliChip(
                text = text,
                icon = icon,
                checked = selectedChip == index,
                onCheckedChange = { selectedChip = index }
            )
        }
    }
}

@Composable
fun ProductList(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 4.dp)
    ) {
        items(products) { product ->
            ProductCard(product, onClick = { onProductClick(product) })
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClick() }
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .height(IntrinsicSize.Min)
    ) {
        Box(modifier = Modifier.fillMaxHeight()) {
            AsyncImage(
                model = product.images.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(120.dp)
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surface),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite ,
                    contentDescription = null,
                    tint = if (isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            if (product.isOfficial) {
                Text(
                    text = "APPLE TIENDA OFICIAL",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .background(Color.Black, RoundedCornerShape(3.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = product.brand.uppercase(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 15.sp,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(2.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Por ${product.brand}",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )

                    Icon(
                        imageVector = Icons.Filled.Verified,
                        contentDescription = "Verificado",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(12.dp)
                    )

            }
            RatingStars(
                rating = product.rating,
                reviews = product.reviews,
                modifier = Modifier.padding(top = 2.dp)
            )
            if (product.originalPrice != null) {
                Text(
                    text = formatCurrency(product.originalPrice),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 13.sp,
                    textDecoration = TextDecoration.LineThrough
                )
            }
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = formatCurrency(product.price),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                if (product.discount != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = product.discount,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            if (product.freeShipping) {
                Text(
                    text = "Envío gratis",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 13.sp
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            if (product.arrivesTomorrow) {
                Text(
                    text = "Llega gratis mañana",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.tertiaryContainer,
                            RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            if (product.colors != null) {
                Text(
                    text = "Disponible en ${product.colors} colores",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun ProductGrid(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 4.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { idx ->
            ProductGridCard(products[idx], onClick = { onProductClick(products[idx]) })
        }
    }
}

@Composable
fun ProductGridCard(product: Product, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(6.dp)
            .clickable { onClick() }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = product.images.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "Favorito",
                tint = Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .size(22.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        if (product.isOfficial) {
            Text(
                text = "${product.brand.uppercase()} TIENDA OFICIAL",
                color = Color.Black,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(4.dp))
                    .padding(2.dp)
            )
        }
        Text(
            product.brand,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Gray,
            maxLines = 1
        )
        Text(product.title, fontWeight = FontWeight.Normal, fontSize = 13.sp, maxLines = 2)
        Spacer(modifier = Modifier.height(2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "${product.price} ${product.currency}",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.secondary
            )
            if (product.discount != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    product.discount,
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        if (product.originalPrice != null) {
            Text(
                "${product.originalPrice} ${product.currency}",
                color = Color.Gray,
                fontSize = 12.sp,
                textDecoration = TextDecoration.LineThrough
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${product.rating}", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(2.dp))
            repeat(5) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(13.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text("(${product.reviews})", fontSize = 12.sp, color = Color.Gray)
        }
        if (product.freeShipping) {
            Text(
                "Envío gratis",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        if (product.arrivesTomorrow) {
            Text(
                "Llega gratis mañana",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        Text(
            "Disponible en ${product.colors} colores",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
} 