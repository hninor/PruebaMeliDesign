package com.hninor.pruebamelidesign.ui.product

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.hninor.pruebamelidesign.R
import com.hninor.pruebamelidesign.core.designsystem.component.PageIndicator
import com.hninor.pruebamelidesign.core.designsystem.theme.PriceColor
import com.hninor.pruebamelidesign.core.designsystem.theme.DiscountColor
import com.hninor.pruebamelidesign.core.designsystem.theme.RatingColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    productId: String? = null,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val product = uiState.product
    val pagerImages = product?.images ?: emptyList()
    val pagerState = rememberPagerState(pageCount = { pagerImages.size })
    val yellow = MaterialTheme.colorScheme.primary
    val blue = MaterialTheme.colorScheme.secondary
    val green = PriceColor
    val gray = MaterialTheme.colorScheme.onSurfaceVariant
    val lightGray = MaterialTheme.colorScheme.surfaceVariant

    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }
    if (uiState.error != null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: ${uiState.error}")
        }
        return
    }
    if (product == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Producto no encontrado")
        }
        return
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        // Barra superior amarilla
        Column(Modifier.background(yellow)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = Color.Black
                    )
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Buscar en Mercado Libre",
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
            // Dirección
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(yellow)
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
                Text("Calle Bauness 1825", fontSize = 15.sp, color = Color.Black)
                Spacer(modifier = Modifier.width(2.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Cambiar dirección",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        // Contenido scrollable
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            // Estado, vendidos
            Row(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(product.condition, color = green, fontSize = 13.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Text("| ${product.soldQuantity} vendidos", color = gray, fontSize = 13.sp)
            }
            // Título
            Text(
                product.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            // Etiqueta tienda oficial
            if (product.isOfficial) {
                Text(
                    text = "APPLE TIENDA OFICIAL",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .background(lightGray, RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
            // Carrusel de imágenes
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .padding(vertical = 8.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxSize()
                ) { page ->
                    AsyncImage(
                        model = pagerImages[page],
                        contentDescription = product.title + ", imagen ${page + 1} de ${pagerImages.size}",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
                // Indicador de página
                Row(
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${pagerState.currentPage + 1}/${pagerImages.size}", fontSize = 12.sp, color = gray)
                }
                // Corazón favorito
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            // Puntos de carrusel
            Row(
                Modifier.align(Alignment.CenterHorizontally).padding(bottom = 8.dp)
                    .semantics {
                        this.contentDescription = "Página ${pagerState.currentPage + 1} de ${pagerImages.size}"
                    }
            ) {
                repeat(pagerImages.size) { idx ->
                    Box(
                        Modifier
                            .size(if (pagerState.currentPage == idx) 10.dp else 8.dp)
                            .background(
                                if (pagerState.currentPage == idx) blue else lightGray,
                                RoundedCornerShape(50)
                            )
                            .padding(2.dp)
                    )
                    if (idx < pagerImages.size - 1) Spacer(modifier = Modifier.width(4.dp))
                }
            }
            // Precio y cuotas
            Row(
                Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text("${product.price} ${product.currency}", fontWeight = FontWeight.Bold, fontSize = 32.sp, color = blue)
            }
            if (product.originalPrice != null) {
                Text(
                    "${product.originalPrice} ${product.currency}",
                    color = gray,
                    fontSize = 15.sp,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            Text(
                "Mismo precio en 9 cuotas de $ 127.999",
                color = gray,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                "Envío gratis el sábado",
                color = green,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
            )
            // Stock
            Text(
                "Stock disponible",
                color = gray,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
            )
            // Cantidad y botones
            Row(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Cantidad: 1 (+10 disponibles)", color = gray, fontSize = 15.sp)
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = blue)
            ) {
                Text("Comprar ahora", color = Color.White, fontSize = 18.sp)
            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .height(48.dp)
            ) {
                Text("Agregar al carrito", color = blue, fontSize = 18.sp)
            }
            // Vendedor
            Row(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Tienda oficial Apple", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    Text("Vendido por MACSTATION OFICIAL", color = gray, fontSize = 13.sp)
                    Text("+10mil ventas", color = gray, fontSize = 13.sp)
                }
            }
            // Descripción
            Text(
                "Descripción",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
            Text(
                "El iPhone 14 viene con el sistema de dos cámaras más impresionante en un iPhone 14, para que tomes fotos espectaculares con mucha o poca luz. Y te da más tranquilidad gracias a una funcionalidad de seguridad que salva vidas.\n\nAviso legal\n(1) La pantalla tiene las esquinas redondeadas. Si se mide en forma de rectángulo, la pantalla tiene 6.06 pulgadas en diagonal. El área real de visualización es menor.\n(2) La funcionalidad Emergencia SOS usa conexión celular o llamadas por Wi-Fi.\n(3) La duración de la batería varía según el uso y la configuración.\n(4) Se requiere un plan de datos. SGs está disponible por regiones y a través de operadores seleccionados. La velocidad y la disponibilidad pueden variar según el lugar y el proveedor de servicios.\n(5) El iPhone 14 es resistente a las salpicaduras, al agua y al polvo; pruebas de laboratorio controladas, con una clasificación IP68 según la norma IEC 60529 (hasta 30 minutos a una profundidad máxima de 6 metros). La resistencia a las salpicaduras, al agua y al polvo no es una condición permanente, y podría disminuir como consecuencia del uso normal. No intentes cargar un iPhone mojado; consulta el manual del usuario para ver las instrucciones de limpieza y secado. La garantía no cubre daños producidos por líquidos.\n(6) Algunas funcionalidades podrían no estar disponibles en todos los países o áreas",
                color = gray,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 2.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailContent(
    product: com.hninor.pruebamelidesign.domain.model.Product,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Pager de imágenes
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            val pagerState = rememberPagerState(pageCount = { product.images.size })
            
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                AsyncImage(
                    model = product.images[page],
                    contentDescription = "${product.title} - Imagen ${page + 1}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            
            // Page Indicator
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                PageIndicator(
                    pageCount = product.images.size,
                    currentPage = pagerState.currentPage
                )
            }
        }
        
        // Información del producto
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Precio
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = PriceColor,
                    fontWeight = FontWeight.Bold
                )
                
                product.originalPrice?.let { originalPrice ->
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "$${originalPrice}",
                        style = MaterialTheme.typography.titleMedium,
                        color = DiscountColor
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Información del vendedor
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Vendedor",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = product.seller.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        
                        Spacer(modifier = Modifier.size(8.dp))
                        
                        Text(
                            text = "★ ${product.seller.reputation}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = RatingColor
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Detalles del producto
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Detalles",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    DetailRow("Condición", product.condition)
                    DetailRow("Disponibles", "${product.availableQuantity} unidades")
                    DetailRow("Vendidos", "${product.soldQuantity} unidades")
                }
            }
        }
    }
}

@Composable
fun DetailRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
} 