package com.hninor.pruebamelidesign.domain.usecase

import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(id: String): Flow<Product> {
        return productRepository.getProductById(id)
    }
} 