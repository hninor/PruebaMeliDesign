package com.hninor.pruebamelidesign.data.repository

import com.hninor.pruebamelidesign.data.remote.MockProductService
import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.model.SearchResult
import com.hninor.pruebamelidesign.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val mockProductService: MockProductService
) : ProductRepository {
    
    override fun searchProducts(query: String): Flow<SearchResult> {
        return mockProductService.searchProducts(query).map { products ->
            SearchResult(
                products = products,
                totalResults = products.size,
                query = query
            )
        }
    }
    
    override fun getProductById(id: String): Flow<Product> {
        return mockProductService.getProductById(id).map { product ->
            product ?: throw IllegalArgumentException("Product not found")
        }
    }
} 