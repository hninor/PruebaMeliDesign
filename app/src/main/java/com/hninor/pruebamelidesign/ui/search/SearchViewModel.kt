package com.hninor.pruebamelidesign.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.pruebamelidesign.domain.model.SearchResult
import com.hninor.pruebamelidesign.domain.usecase.SearchProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
    
    fun searchProducts(query: String) {
        if (query.isBlank()) {
            _uiState.update { it.copy(
                isLoading = false,
                searchResult = null,
                error = null
            ) }
            return
        }
        
        _uiState.update { it.copy(isLoading = true, error = null) }
        
        viewModelScope.launch {
            searchProductsUseCase(query)
                .catch { exception ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        error = exception.message ?: "Error desconocido"
                    ) }
                }
                .collect { searchResult ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        searchResult = searchResult,
                        error = null
                    ) }
                }
        }
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

data class SearchUiState(
    val isLoading: Boolean = false,
    val searchResult: SearchResult? = null,
    val error: String? = null
) 