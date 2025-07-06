package com.hninor.pruebamelidesign.domain.usecase

import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.model.SearchResult
import com.hninor.pruebamelidesign.domain.model.Seller
import com.hninor.pruebamelidesign.domain.repository.ProductRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class SearchProductsUseCaseTest {
    
    private lateinit var searchProductsUseCase: SearchProductsUseCase
    private lateinit var mockRepository: ProductRepository
    
    @Before
    fun setup() {
        mockRepository = mock()
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
                price = 1299.99,
                originalPrice = null,
                currency = "USD",
                images = listOf("image1.jpg"),
                condition = "Nuevo",
                availableQuantity = 10,
                soldQuantity = 150,
                permalink = "https://example.com",
                seller = Seller("seller1", "Apple Store", 4.8)
            )
        )
        val expectedResult = SearchResult(
            products = mockProducts,
            totalResults = 1,
            query = query
        )
        
        whenever(mockRepository.searchProducts(query)).thenReturn(flowOf(expectedResult))
        
        // When
        val result = searchProductsUseCase(query)
        
        // Then
        result.collect { searchResult ->
            assertEquals(expectedResult, searchResult)
        }
    }
} 