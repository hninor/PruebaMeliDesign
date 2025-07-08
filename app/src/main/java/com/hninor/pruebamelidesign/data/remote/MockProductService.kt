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
            id = "aed87a8ed0bacc718dde766f",
            title = "Small Gold Gloves",
            brand = "Motorola",
            price = 304234.22,
            originalPrice = 451668.73,
            discount = "33% OFF",
            currency = "ARS",
            images = listOf(
                "https://picsum.photos/seed/erKAa/200/200",
                "https://picsum.photos/seed/rJrzO/200/200?grayscale",
                "https://picsum.photos/seed/bzSHCB/200/200"
            ),
            condition = "Nuevo",
            availableQuantity = 10,
            soldQuantity = 90305,
            rating = 3.5,
            reviews = 90305,
            isOfficial = true,
            freeShipping = true,
            arrivesTomorrow = false,
            colors = 3,
            permalink = "https://example.com/product/aed87a8ed0bacc718dde766f",
            seller = Seller(id = "seller1", name = "Niska", reputation = 4.8)
        ),
        Product(
            id = "26ed8be26138ef6e7bccf6da",
            title = "Recycled Marble Pants",
            brand = "AMD",
            price = 352776.58,
            originalPrice = 523834.35,
            discount = "33% OFF",
            currency = "ARS",
            images = listOf(
                "https://picsum.photos/seed/bttfQ/200/200?grayscale",
                "https://picsum.photos/seed/Yk0XCv/200/200"
            ),
            condition = "Nuevo",
            availableQuantity = 5,
            soldQuantity = 77155,
            rating = 1.73,
            reviews = 77155,
            isOfficial = false,
            freeShipping = false,
            arrivesTomorrow = false,
            colors = 2,
            permalink = "https://example.com/product/26ed8be26138ef6e7bccf6da",
            seller = Seller(id = "seller2", name = "Becky Hill", reputation = 4.2)
        ),
        Product(
            id = "a92ba3dfaa3d5b127ba5c59d",
            title = "Awesome Marble Tuna",
            brand = "AMD",
            price = 232644.54,
            originalPrice = 363892.69,
            discount = "36% OFF",
            currency = "ARS",
            images = listOf(
                "https://picsum.photos/seed/IffkN/200/200?grayscale",
                "https://picsum.photos/seed/A35u3kF/200/200"
            ),
            condition = "Nuevo",
            availableQuantity = 3,
            soldQuantity = 11361,
            rating = 4.46,
            reviews = 11361,
            isOfficial = false,
            freeShipping = false,
            arrivesTomorrow = false,
            colors = 1,
            permalink = "https://example.com/product/a92ba3dfaa3d5b127ba5c59d",
            seller = Seller(id = "seller3", name = "Hillsong Worship", reputation = 4.5)
        ),
        Product(
            id = "7fe51da85aced13bdd8305c9",
            title = "Handmade Cotton Hat",
            brand = "Huawei",
            price = 479621.39,
            originalPrice = 621059.39,
            discount = "23% OFF",
            currency = "ARS",
            images = listOf(
                "https://picsum.photos/seed/EFJvQG/200/200",
                "https://picsum.photos/seed/V8c7hy/200/200"
            ),
            condition = "Nuevo",
            availableQuantity = 8,
            soldQuantity = 40964,
            rating = 3.8,
            reviews = 40964,
            isOfficial = true,
            freeShipping = true,
            arrivesTomorrow = true,
            colors = 4,
            permalink = "https://example.com/product/7fe51da85aced13bdd8305c9",
            seller = Seller(id = "seller4", name = "The Emotions", reputation = 4.7)
        ),
        Product(
            id = "af4def9d2809e82677dce0aa",
            title = "Tasty Wooden Bike",
            brand = "Motorola",
            price = 76170.84,
            originalPrice = 90106.69,
            discount = "15% OFF",
            currency = "ARS",
            images = listOf(
                "https://picsum.photos/seed/7MLqEMxlK1/200/200",
                "https://picsum.photos/seed/S07OsP/200/200"
            ),
            condition = "Nuevo",
            availableQuantity = 2,
            soldQuantity = 51521,
            rating = 1.50,
            reviews = 51521,
            isOfficial = false,
            freeShipping = true,
            arrivesTomorrow = false,
            colors = 2,
            permalink = "https://example.com/product/af4def9d2809e82677dce0aa",
            seller = Seller(id = "seller5", name = "Sabrina Carpenter", reputation = 4.1)
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

    fun getProducts(): List<Product> = mockProducts
} 