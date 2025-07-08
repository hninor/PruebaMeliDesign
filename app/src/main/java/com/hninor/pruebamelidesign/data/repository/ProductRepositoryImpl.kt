package com.hninor.pruebamelidesign.data.repository

import com.hninor.pruebamelidesign.data.remote.MockProductService
import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.model.SearchResult
import com.hninor.pruebamelidesign.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor() : ProductRepository {
    private val mockService = MockProductService()
    override fun getProducts(): Flow<List<Product>> = flow {
        emit(mockService.getProducts())
    }

    override fun searchProducts(query: String): Flow<SearchResult> = flow {
        val products = mockService.getProducts().filter {
            it.title.contains(query, ignoreCase = true)
        }
        emit(SearchResult(products = products, totalResults = products.size, query = query))
    }

    override fun getProductById(id: String): Flow<Product> = flow {
        val product = mockService.getProducts().find { it.id == id }
        if (product != null) emit(product)
        else throw IllegalArgumentException("Product not found")
    }
} 