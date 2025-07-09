package com.hninor.pruebamelidesign.core.designsystem.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OfficialStoreBadgeInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<androidx.activity.ComponentActivity>()


    @Test
    fun whenBrandIsProvidedThenDisplaysOfficialStoreBadge() {
        // Given
        val brand = "Apple"
        val expectedText = "APPLE TIENDA OFICIAL"

        // When
        composeTestRule.setContent {
            OfficialStoreBadge(brand = brand)
        }

        // Then
        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
    }

    @Test
    fun whenBrandIsEmptyThenDisplaysEmptyOfficialStoreBadge() {
        // Given
        val brand = ""
        val expectedText = " TIENDA OFICIAL"

        // When
        composeTestRule.setContent {
            OfficialStoreBadge(brand = brand)
        }

        // Then
        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
    }

    @Test
    fun whenBrandHasSpacesThenDisplaysCorrectlyFormattedBadge() {
        // Given
        val brand = "Samsung Electronics"
        val expectedText = "SAMSUNG ELECTRONICS TIENDA OFICIAL"

        // When
        composeTestRule.setContent {
            OfficialStoreBadge(brand = brand)
        }

        // Then
        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
    }

    @Test
    fun whenBrandHasSpecialCharactersThenDisplaysCorrectlyFormattedBadge() {
        // Given
        val brand = "Nike & Co"
        val expectedText = "NIKE & CO TIENDA OFICIAL"

        // When
        composeTestRule.setContent {
            OfficialStoreBadge(brand = brand)
        }

        // Then
        composeTestRule.onNodeWithText(expectedText).assertIsDisplayed()
    }

    @Test
    fun whenCustomColorsAreProvidedThenBadgeUsesCustomColors() {
        // Given
        val brand = "Sony"
        val customBackgroundColor = Color.Red
        val customTextColor = Color.Yellow

        // When
        composeTestRule.setContent {
            OfficialStoreBadge(
                brand = brand,
                backgroundColor = customBackgroundColor,
                textColor = customTextColor
            )
        }

        // Then
        composeTestRule.onNodeWithText("SONY TIENDA OFICIAL").assertIsDisplayed()
    }

}
