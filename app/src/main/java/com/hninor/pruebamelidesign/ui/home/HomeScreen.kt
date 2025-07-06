package com.hninor.pruebamelidesign.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFFFE600))) {
        TopBar()
        FilterChips()
        ProductList(products = mockProducts)
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFE600))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icono menú
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        // Barra de búsqueda
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
        // Icono notificaciones con badge
        Box {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Notificaciones",
                tint = Color.Black,
                modifier = Modifier.size(28.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 10.dp, y = (-4).dp)
                    .size(18.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF23238E)),
                contentAlignment = Alignment.Center
            ) {
                Text("286", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FilterChips() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Chip(text = "Llega mañana")
        Chip(text = "Mejor precio en cuotas")
        Chip(text = "Enviado por")
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
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
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