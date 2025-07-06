package com.hninor.pruebamelidesign.domain.usecase

import com.hninor.pruebamelidesign.domain.model.SearchResult
import com.hninor.pruebamelidesign.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(query: String): Flow<SearchResult> {
        return productRepository.searchProducts(query)
    }
} 