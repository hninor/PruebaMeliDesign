package com.hninor.pruebamelidesign.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val price: Double,
    val originalPrice: Double?,
    val currency: String,
    val images: String, // JSON string
    val condition: String,
    val availableQuantity: Int,
    val soldQuantity: Int,
    val permalink: String,
    val sellerId: String,
    val sellerName: String,
    val sellerReputation: Double
) 