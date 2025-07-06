package com.hninor.pruebamelidesign.data.remote

import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.model.Seller
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MockProductService @Inject constructor() {
    
    private val mockProducts = listOf(
        Product(
            id = "1",
            title = "iPhone 15 Pro Max 256GB Titanio Natural",
            price = 1299.99,
            originalPrice = 1399.99,
            currency = "USD",
            images = listOf(
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp"
            ),
            condition = "Nuevo",
            availableQuantity = 10,
            soldQuantity = 150,
            permalink = "https://www.mercadolibre.com.ar/iphone-15-pro-max",
            seller = Seller("seller1", "Apple Store", 4.8)
        ),
        Product(
            id = "2",
            title = "Samsung Galaxy S24 Ultra 512GB Negro",
            price = 1199.99,
            originalPrice = null,
            currency = "USD",
            images = listOf(
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp"
            ),
            condition = "Nuevo",
            availableQuantity = 5,
            soldQuantity = 89,
            permalink = "https://www.mercadolibre.com.ar/samsung-galaxy-s24",
            seller = Seller("seller2", "Samsung Store", 4.7)
        ),
        Product(
            id = "3",
            title = "MacBook Pro 14\" M3 Pro 1TB",
            price = 2499.99,
            originalPrice = 2699.99,
            currency = "USD",
            images = listOf(
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp"
            ),
            condition = "Nuevo",
            availableQuantity = 3,
            soldQuantity = 45,
            permalink = "https://www.mercadolibre.com.ar/macbook-pro-14",
            seller = Seller("seller1", "Apple Store", 4.8)
        ),
        Product(
            id = "4",
            title = "AirPods Pro 2da Generación",
            price = 249.99,
            originalPrice = 299.99,
            currency = "USD",
            images = listOf(
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp"
            ),
            condition = "Nuevo",
            availableQuantity = 25,
            soldQuantity = 320,
            permalink = "https://www.mercadolibre.com.ar/airpods-pro",
            seller = Seller("seller1", "Apple Store", 4.8)
        ),
        Product(
            id = "5",
            title = "iPad Air 5ta Generación 256GB",
            price = 699.99,
            originalPrice = null,
            currency = "USD",
            images = listOf(
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp",
                "https://http2.mlstatic.com/D_NQ_NP_2X_615787-MLA74682345678_022024-F.webp"
            ),
            condition = "Nuevo",
            availableQuantity = 8,
            soldQuantity = 67,
            permalink = "https://www.mercadolibre.com.ar/ipad-air",
            seller = Seller("seller1", "Apple Store", 4.8)
        )
    )
    
    fun searchProducts(query: String): Flow<List<Product>> = flow {
        delay(1000) // Simular delay de red
        val filteredProducts = mockProducts.filter { product ->
            product.title.contains(query, ignoreCase = true) ||
            product.seller.name.contains(query, ignoreCase = true)
        }
        emit(filteredProducts)
    }
    
    fun getProductById(id: String): Flow<Product?> = flow {
        delay(500) // Simular delay de red
        val product = mockProducts.find { it.id == id }
        emit(product)
    }
} 