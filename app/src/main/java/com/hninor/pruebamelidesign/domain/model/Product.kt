package com.hninor.pruebamelidesign.domain.model

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val originalPrice: Double?,
    val currency: String,
    val images: List<String>,
    val condition: String,
    val availableQuantity: Int,
    val soldQuantity: Int,
    val permalink: String,
    val seller: Seller
)

data class Seller(
    val id: String,
    val name: String,
    val reputation: Double
)

data class SearchResult(
    val products: List<Product>,
    val totalResults: Int,
    val query: String
) 