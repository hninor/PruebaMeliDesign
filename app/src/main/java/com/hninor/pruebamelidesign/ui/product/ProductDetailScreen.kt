package com.hninor.pruebamelidesign.ui.product

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hninor.pruebamelidesign.R
import com.hninor.pruebamelidesign.core.designsystem.component.HomeTopBar
import com.hninor.pruebamelidesign.core.designsystem.component.PageIndicator
import com.hninor.pruebamelidesign.core.designsystem.component.RatingStars
import com.hninor.pruebamelidesign.ui.home.formatCurrency

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen(
    navController: NavController,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val product = uiState.product
    val pagerImages = product?.images ?: emptyList()
    val pagerState = rememberPagerState(pageCount = { pagerImages.size })
    var isFavorite by remember { mutableStateOf(false) }

    val gray = MaterialTheme.colorScheme.outline


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



    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        HomeTopBar(
            isGrid = false,
            onToggleView = {},
            searchQuery = "",
            onSearchClick = {},
            onSettingsClick = null,
            address = "Calle Bauness 1825",
            showBack = true,
            onBack = { navController.navigateUp() },
            showActions = false
        )

        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {


            Row(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        product.condition,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    val quantity = formatCurrency(product.soldQuantity, "")
                    Text(
                        "| $quantity vendidos",
                        color = MaterialTheme.colorScheme.outline,
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                RatingStars(
                    rating = product.rating,
                    reviews = product.reviews,
                )
            }

            Text(
                product.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            if (product.isOfficial) {
                Text(
                    text = "${product.brand.uppercase()} TIENDA OFICIAL",
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .background(Color.Black, RoundedCornerShape(3.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

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
                        contentScale = ContentScale.Crop
                    )
                }

                Row(
                    Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "${pagerState.currentPage + 1}/${pagerImages.size}",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.outline
                    )
                }

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = if (isFavorite) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
            // Puntos de carrusel

            PageIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp),
                pageCount = product.images.size,
                currentPage = pagerState.currentPage
            )


            Row(
                Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatCurrency(product.price),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

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
                "Mismo precio en 9 cuotas de $ 127.999",
                color = gray,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Text(
                "Envío gratis el sábado",
                color = MaterialTheme.colorScheme.tertiary,
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
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
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
                Text(
                    "Agregar al carrito",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 18.sp
                )
            }
            // Vendedor
            Row(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.watch),
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



