package com.hninor.pruebamelidesign.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hninor.pruebamelidesign.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.KeyboardArrowRight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.SideEffect
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState

// Datos mock para productos
data class MockProduct(
    val id: String,
    val title: String,
    val brand: String,
    val imageRes: Int,
    val price: String,
    val originalPrice: String?,
    val discount: String?,
    val rating: Double,
    val reviews: Int,
    val isOfficial: Boolean,
    val freeShipping: Boolean,
    val arrivesTomorrow: Boolean,
    val colors: Int
)

val mockProducts = listOf(
    MockProduct(
        id = "1",
        title = "Apple iPhone 13 (128 GB)-Blanco estelar - Distribuidor Autorizado",
        brand = "Apple",
        imageRes = R.drawable.ic_launcher_foreground, // Usa tu imagen real aquí
        price = "$1.151.999",
        originalPrice = "$2.399.999",
        discount = "52% OFF",
        rating = 4.9,
        reviews = 38,
        isOfficial = true,
        freeShipping = true,
        arrivesTomorrow = false,
        colors = 3
    ),
    MockProduct(
        id = "2",
        title = "Apple iPhone 16 (256 GB)- Rosa",
        brand = "Apple",
        imageRes = R.drawable.ic_launcher_foreground,
        price = "$2.125.000",
        originalPrice = null,
        discount = null,
        rating = 4.9,
        reviews = 269,
        isOfficial = false,
        freeShipping = true,
        arrivesTomorrow = true,
        colors = 5
    )
)

@Composable
fun HomeScreen() {
    var isGrid by remember { mutableStateOf(false) }
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color(0xFFFFE600))
    }
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFFFE600))) {
        TopBar(isGrid = isGrid, onToggleView = { isGrid = !isGrid })
        FilterChips()
        if (isGrid) {
            ProductGrid(products = mockProducts)
        } else {
            ProductList(products = mockProducts)
        }
    }
}

@Composable
fun TopBar(isGrid: Boolean, onToggleView: (Boolean) -> Unit) {
    // Barra de búsqueda y configuración
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFE600))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Buscar...",
                color = Color.Gray,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        // Botón de configuración arriba a la derecha
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Configuración",
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )
    }
    // Segunda fila: dirección y botones de vista
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFE600))
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Ubicación",
            tint = Color.Black,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text("Calle Posta 4789", fontSize = 15.sp, color = Color.Black)
        Spacer(modifier = Modifier.width(2.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "Cambiar dirección",
            tint = Color.Black,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        // Botón de lista
        Icon(
            imageVector = Icons.Default.List,
            contentDescription = "Vista de lista",
            tint = if (!isGrid) Color(0xFF23238E) else Color.Black,
            modifier = Modifier
                .size(28.dp)
                .clickable { onToggleView(false) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Botón de grilla
        Icon(
            imageVector = Icons.Filled.ViewModule,
            contentDescription = "Vista de grilla",
            tint = if (isGrid) Color(0xFF23238E) else Color.Black,
            modifier = Modifier
                .size(28.dp)
                .clickable { onToggleView(true) }
        )
    }
}

@Composable
fun FilterChips() {
    val scrollState = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .horizontalScroll(scrollState)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Chip(text = "Llega mañana")
        Chip(text = "Mejor precio en cuotas")
        Chip(text = "Enviado por")
        Chip(text = "Disponible en 3 colores")
        Chip(text = "Apple Tienda Oficial")
        Chip(text = "Envío gratis")
    }
}

@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF0F0F0))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(text = text, fontSize = 13.sp, color = Color.Black)
    }
}

@Composable
fun ProductList(products: List<MockProduct>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 4.dp)
    ) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: MockProduct) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.title,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            if (product.isOfficial) {
                Text(
                    text = "APPLE TIENDA OFICIAL",
                    color = Color.Black,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.background(Color(0xFFE0E0E0), RoundedCornerShape(4.dp)).padding(2.dp)
                )
            }
            Text(product.brand, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Text(product.title, fontSize = 14.sp, maxLines = 2)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(product.price, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF23238E))
                if (product.discount != null) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(product.discount, color = Color(0xFF00A650), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
            }
            if (product.originalPrice != null) {
                Text(
                    product.originalPrice,
                    color = Color.Gray,
                    fontSize = 13.sp,
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
                Text("Envío gratis", color = Color(0xFF00A650), fontSize = 13.sp)
            }
            if (product.arrivesTomorrow) {
                Text("Llega gratis mañana", color = Color(0xFF00A650), fontSize = 13.sp)
            }
            Text("Disponible en ${product.colors} colores", fontSize = 12.sp, color = Color.Gray)
        }
        // Corazón favorito
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Favorito",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun ProductGrid(products: List<MockProduct>) {
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
            ProductGridCard(products[idx])
        }
    }
}

@Composable
fun ProductGridCard(product: MockProduct) {
    Column(
        modifier = Modifier
            .background(Color.White, RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(6.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.title,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_star), // Usa un corazón si tienes, si no, deja ic_star
                contentDescription = "Favorito",
                tint = Color.Gray,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .size(22.dp)
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            product.brand.uppercase(),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.Gray,
            maxLines = 1
        )
        Text(
            product.title,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                product.price,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                color = Color(0xFF23238E)
            )
            if (product.discount != null) {
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    product.discount,
                    color = Color(0xFF00A650),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        if (product.originalPrice != null) {
            Text(
                product.originalPrice,
                color = Color.Gray,
                fontSize = 12.sp,
                textDecoration = TextDecoration.LineThrough
            )
        }
        if (product.freeShipping) {
            Text(
                "Envío gratis",
                color = Color(0xFF00A650),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        if (product.arrivesTomorrow) {
            Text(
                "Llega gratis mañana",
                color = Color(0xFF00A650),
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
        // Puedes agregar más etiquetas si lo deseas
    }
} 