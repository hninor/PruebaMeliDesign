package com.hninor.pruebamelidesign.domain.repository

import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun searchProducts(query: String): Flow<SearchResult>
    fun getProductById(id: String): Flow<Product>
} 