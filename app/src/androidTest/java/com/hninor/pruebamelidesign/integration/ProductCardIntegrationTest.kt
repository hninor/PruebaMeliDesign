package com.hninor.pruebamelidesign.integration

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.hninor.pruebamelidesign.core.designsystem.component.OfficialStoreBadge
import com.hninor.pruebamelidesign.core.designsystem.component.RatingStars
import com.hninor.pruebamelidesign.core.designsystem.component.ProductPriceDisplay
import com.hninor.pruebamelidesign.domain.model.Product
import com.hninor.pruebamelidesign.domain.model.Seller
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@RunWith(AndroidJUnit4::class)
class ProductCardIntegrationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenProductCardIsDisplayedThenAllComponentsAreVisible() {
        // Given
        val product = Product(
            id = "1",
            title = "iPhone 15 Pro",
            brand = "Apple",
            price = 1299.99,
            originalPrice = 1499.99,
            discount = "13%",
            currency = "USD",
            images = listOf("image1.jpg"),
            condition = "Nuevo",
            availableQuantity = 10,
            soldQuantity = 150,
            rating = 4.8,
            reviews = 1250,
            isOfficial = true,
            freeShipping = true,
            arrivesTomorrow = true,
            colors = 3,
            permalink = "https://example.com",
            seller = Seller("seller1", "Apple Store", 4.8)
        )

        // When
        composeTestRule.setContent {
            ProductCardContent(product = product)
        }

        // Then
        composeTestRule.onNodeWithText("iPhone 15 Pro").assertIsDisplayed()
        composeTestRule.onNodeWithText("APPLE TIENDA OFICIAL").assertIsDisplayed()
        composeTestRule.onNodeWithText("4.8").assertIsDisplayed()
        composeTestRule.onNodeWithText("(1250)").assertIsDisplayed()
        composeTestRule.onNodeWithText("13%").assertIsDisplayed()
    }

    @Composable
    private fun ProductCardContent(product: Product) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.title)
            OfficialStoreBadge(brand = product.brand)
            RatingStars(rating = product.rating, reviews = product.reviews)
            ProductPriceDisplay(
                price = product.price,
                discount = product.discount
            )
        }
    }
} 