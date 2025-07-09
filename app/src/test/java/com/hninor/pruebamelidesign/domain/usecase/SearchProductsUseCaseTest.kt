package com.hninor.pruebamelidesign.domain.usecase

import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.model.SearchResult
import com.hninor.pruebamelidesign.domain.model.Seller
import com.hninor.pruebamelidesign.domain.repository.ProductRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import io.mockk.mockk
import io.mockk.every

class SearchProductsUseCaseTest {
    
    private lateinit var searchProductsUseCase: SearchProductsUseCase
    private lateinit var mockRepository: ProductRepository
    
    @Before
    fun setup() {
        mockRepository = mockk()
        searchProductsUseCase = SearchProductsUseCase(mockRepository)
    }
    
    @Test
    fun `when search products then return search result`() = runTest {
        // Given
        val query = "iPhone"
        val mockProducts = listOf(
            Product(
                id = "1",
                title = "iPhone 15 Pro",
                brand = "Apple",
                price = 1299.99,
                originalPrice = null,
                discount = null,
                currency = "USD",
                images = listOf("image1.jpg"),
                condition = "Nuevo",
                availableQuantity = 10,
                soldQuantity = 150,
                rating = 4.8,
                reviews = 1250,
                isOfficial = true,
                freeShipping = true,
                arrivesTomorrow = true,
                colors = 3,
                permalink = "https://example.com",
                seller = Seller("seller1", "Apple Store", 4.8)
            )
        )
        val expectedResult = SearchResult(
            products = mockProducts,
            totalResults = 1,
            query = query
        )
        
        every { mockRepository.searchProducts(query) } returns flowOf(expectedResult)
        
        // When
        val result = searchProductsUseCase(query)
        
        // Then
        result.collect { searchResult ->
            assertEquals(expectedResult, searchResult)
        }
    }
    
    @Test
    fun `when search with empty query then return empty result`() = runTest {
        // Given
        val query = ""
        val expectedResult = SearchResult(
            products = emptyList(),
            totalResults = 0,
            query = query
        )
        
        every { mockRepository.searchProducts(query) } returns flowOf(expectedResult)
        
        // When
        val result = searchProductsUseCase(query)
        
        // Then
        result.collect { searchResult ->
            assertEquals(expectedResult, searchResult)
        }
    }
    
    @Test
    fun `when search with multiple products then return all products`() = runTest {
        // Given
        val query = "smartphone"
        val mockProducts = listOf(
            Product(
                id = "1",
                title = "iPhone 15 Pro",
                brand = "Apple",
                price = 1299.99,
                originalPrice = null,
                discount = null,
                currency = "USD",
                images = listOf("image1.jpg"),
                condition = "Nuevo",
                availableQuantity = 10,
                soldQuantity = 150,
                rating = 4.8,
                reviews = 1250,
                isOfficial = true,
                freeShipping = true,
                arrivesTomorrow = true,
                colors = 3,
                permalink = "https://example.com",
                seller = Seller("seller1", "Apple Store", 4.8)
            ),
            Product(
                id = "2",
                title = "Samsung Galaxy S24",
                brand = "Samsung",
                price = 999.99,
                originalPrice = 1199.99,
                discount = "17%",
                currency = "USD",
                images = listOf("image2.jpg"),
                condition = "Nuevo",
                availableQuantity = 25,
                soldQuantity = 89,
                rating = 4.6,
                reviews = 890,
                isOfficial = true,
                freeShipping = false,
                arrivesTomorrow = false,
                colors = 2,
                permalink = "https://example.com/samsung",
                seller = Seller("seller2", "Samsung Store", 4.7)
            )
        )
        val expectedResult = SearchResult(
            products = mockProducts,
            totalResults = 2,
            query = query
        )
        
        every { mockRepository.searchProducts(query) } returns flowOf(expectedResult)
        
        // When
        val result = searchProductsUseCase(query)
        
        // Then
        result.collect { searchResult ->
            assertEquals(expectedResult, searchResult)
            assertEquals(2, searchResult.products.size)
            assertEquals("iPhone 15 Pro", searchResult.products[0].title)
            assertEquals("Samsung Galaxy S24", searchResult.products[1].title)
        }
    }
} 