package com.hninor.pruebamelidesign.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            getProductsUseCase()
                .catch { exception ->
                    _uiState.update { it.copy(
                        isLoading = false, 
                        error = exception.message ?: "Error desconocido"
                    ) }
                }
                .collect { products ->
                    _uiState.update { it.copy(
                        isLoading = false, 
                        products = products, 
                        error = null
                    ) }
                }
        }
    }

    fun filterProducts(query: String): List<Product> {
        if (query.isBlank()) return _uiState.value.products
        
        val lowerQuery = query.lowercase()
        return _uiState.value.products.filter { product ->
            // Buscar en múltiples campos
            product.title.lowercase().contains(lowerQuery) ||
            product.brand.lowercase().contains(lowerQuery) ||
            product.seller.name.lowercase().contains(lowerQuery) ||
            product.condition.lowercase().contains(lowerQuery) ||
            product.discount?.lowercase()?.contains(lowerQuery) == true ||
            product.price.toString().contains(lowerQuery) ||
            product.originalPrice?.toString()?.contains(lowerQuery) == true ||
            product.currency.lowercase().contains(lowerQuery)
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class HomeUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
) 