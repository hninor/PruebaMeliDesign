package com.hninor.pruebamelidesign.ui.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hninor.pruebamelidesign.core.designsystem.component.HomeTopBar
import com.hninor.pruebamelidesign.designsystem.component.MeliChip
import com.hninor.pruebamelidesign.core.designsystem.component.OfficialStoreBadge
import com.hninor.pruebamelidesign.core.designsystem.component.RatingStars
import com.hninor.pruebamelidesign.core.designsystem.component.ToggleIconButton
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
    var isScreenVisible by remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        isScreenVisible = true
    }

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

    val alpha by animateFloatAsState(
        targetValue = if (isScreenVisible) 1f else 0f,
        animationSpec = tween(600, easing = EaseInOut),
        label = "screen_alpha"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .graphicsLayer(alpha = alpha)
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

        AnimatedContent(
            targetState = isGrid,
            transitionSpec = {
                ContentTransform(
                    targetContentEnter = slideInHorizontally(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ),
                        initialOffsetX = { if (targetState) it else -it }
                    ) + fadeIn(
                        animationSpec = tween(400, easing = EaseInOut)
                    ),
                    initialContentExit = slideOutHorizontally(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        ),
                        targetOffsetX = { if (targetState) -it else it }
                    ) + fadeOut(
                        animationSpec = tween(400, easing = EaseInOut)
                    )
                )
            }
        ) { grid ->
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
            val delay = index * 100L
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(delay)
            }

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
        items(
            count = products.size,
            key = { index -> index }
        ) { index ->
            ProductCard(products[index], onClick = { onProductClick(products[index]) })
        }
    }
}

@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                isPressed = true
                onClick()
            }
            .scale(scale)
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
            ToggleIconButton(
                isActive = isFavorite,
                onClick = { isFavorite = !isFavorite },
                activeIcon = Icons.Filled.Favorite,
                contentDescription = "Favorito",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            ProductInfo(product)
        }
    }
}

@Composable
fun ProductInfo(product: Product) {
    if (product.isOfficial) {
        OfficialStoreBadge(brand = product.brand)
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
            color = MaterialTheme.colorScheme.outline,
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
    Spacer(modifier = Modifier.height(8.dp))

    Row(

        verticalAlignment = Alignment.CenterVertically,

    ) {
        if (product.originalPrice != null) {
            Text(
                text = formatCurrency(product.originalPrice),
                color = MaterialTheme.colorScheme.outline,
                fontSize = 13.sp,
                textDecoration = TextDecoration.LineThrough
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        if (product.discount != null) {
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = product.discount,
                color = MaterialTheme.colorScheme.tertiary,
                fontSize = 13.sp,
            )
        }

    }


    Text(
        text = formatCurrency(product.price),
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )




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
            color = MaterialTheme.colorScheme.outline,
            fontSize = 12.sp
        )
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
        items(
            count = products.size,
            key = { index -> index }
        ) { index ->
            ProductGridCard(products[index], onClick = { onProductClick(products[index]) })
        }
    }
}

@Composable
fun ProductGridCard(product: Product, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }
    var isPressed by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(6.dp)
            .clickable {
                isPressed = true
                onClick()
            }
            .scale(scale)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = product.images.firstOrNull(),
                contentDescription = product.title,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            ToggleIconButton(
                isActive = isFavorite,
                onClick = { isFavorite = !isFavorite },
                activeIcon = Icons.Filled.Favorite,
                contentDescription = "Favorito",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))

        ProductInfo(product)


    }
} 