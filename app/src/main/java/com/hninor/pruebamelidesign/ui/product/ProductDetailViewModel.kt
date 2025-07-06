package com.hninor.pruebamelidesign.ui.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.usecase.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val productId: String = checkNotNull(savedStateHandle["productId"])
    
    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()
    
    init {
        loadProduct()
    }
    
    private fun loadProduct() {
        _uiState.update { it.copy(isLoading = true, error = null) }
        
        viewModelScope.launch {
            getProductUseCase(productId)
                .catch { exception ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        error = exception.message ?: "Error desconocido"
                    ) }
                }
                .collect { product ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        product = product,
                        error = null
                    ) }
                }
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class ProductDetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String? = null
) 