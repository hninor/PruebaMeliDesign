package com.hninor.pruebamelidesign.core.designsystem.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RatingStarsInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenRatingIsZeroThenDisplaysZeroStars() {
        // Given
        val rating = 0.0
        val reviews = 10

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("0.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(10)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsFiveThenDisplaysFiveFullStars() {
        // Given
        val rating = 5.0
        val reviews = 50

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("5.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(50)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsThreePointFiveThenDisplaysThreeFullStarsAndOneHalfStar() {
        // Given
        val rating = 3.5
        val reviews = 25

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("3.5").assertIsDisplayed()
        composeTestRule.onNodeWithText("(25)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsTwoPointThreeThenDisplaysTwoFullStarsAndOneHalfStar() {
        // Given
        val rating = 2.3
        val reviews = 15

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("2.3").assertIsDisplayed()
        composeTestRule.onNodeWithText("(15)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsFourPointSevenThenDisplaysFourFullStarsAndOneHalfStar() {
        // Given
        val rating = 4.7
        val reviews = 100

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("4.7").assertIsDisplayed()
        composeTestRule.onNodeWithText("(100)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsOnePointEightThenDisplaysOneFullStarAndOneHalfStar() {
        // Given
        val rating = 1.8
        val reviews = 5

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("1.8").assertIsDisplayed()
        composeTestRule.onNodeWithText("(5)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsZeroPointTwoThenDisplaysNoFullStarsAndOneHalfStar() {
        // Given
        val rating = 0.2
        val reviews = 3

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("0.2").assertIsDisplayed()
        composeTestRule.onNodeWithText("(3)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsZeroPointEightThenDisplaysNoFullStarsAndOneHalfStar() {
        // Given
        val rating = 0.8
        val reviews = 8

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("0.8").assertIsDisplayed()
        composeTestRule.onNodeWithText("(8)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsZeroPointOneThenDisplaysNoStars() {
        // Given
        val rating = 0.1
        val reviews = 1

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("0.1").assertIsDisplayed()
        composeTestRule.onNodeWithText("(1)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsFourPointNineThenDisplaysFourFullStarsAndOneHalfStar() {
        // Given
        val rating = 4.9
        val reviews = 200

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("4.9").assertIsDisplayed()
        composeTestRule.onNodeWithText("(200)").assertIsDisplayed()
    }

    @Test
    fun whenReviewsIsNullThenDisplaysOnlyRating() {
        // Given
        val rating = 3.0
        val reviews: Int? = null

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("3.0").assertIsDisplayed()
        // No reviews text should be displayed
    }

    @Test
    fun whenRatingIsNegativeThenDisplaysZeroStars() {
        // Given
        val rating = -1.0
        val reviews = 5

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("-1.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(5)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsGreaterThanFiveThenDisplaysFiveFullStars() {
        // Given
        val rating = 6.0
        val reviews = 10

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("6.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(10)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsOnePointZeroThenDisplaysOneFullStar() {
        // Given
        val rating = 1.0
        val reviews = 20

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("1.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(20)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsTwoPointZeroThenDisplaysTwoFullStars() {
        // Given
        val rating = 2.0
        val reviews = 30

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("2.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(30)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsThreePointZeroThenDisplaysThreeFullStars() {
        // Given
        val rating = 3.0
        val reviews = 40

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("3.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(40)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsFourPointZeroThenDisplaysFourFullStars() {
        // Given
        val rating = 4.0
        val reviews = 50

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("4.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(50)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsZeroPointFiveThenDisplaysOneHalfStar() {
        // Given
        val rating = 0.5
        val reviews = 5

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("0.5").assertIsDisplayed()
        composeTestRule.onNodeWithText("(5)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsOnePointFiveThenDisplaysOneFullStarAndOneHalfStar() {
        // Given
        val rating = 1.5
        val reviews = 15

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("1.5").assertIsDisplayed()
        composeTestRule.onNodeWithText("(15)").assertIsDisplayed()
    }

    @Test
    fun whenRatingIsTwoPointFiveThenDisplaysTwoFullStarsAndOneHalfStar() {
        // Given
        val rating = 2.5
        val reviews = 25

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("2.5").assertIsDisplayed()
        composeTestRule.onNodeWithText("(25)").assertIsDisplayed()
    }


    @Test
    fun whenRatingIsFourPointFiveThenDisplaysFourFullStarsAndOneHalfStar() {
        // Given
        val rating = 4.5
        val reviews = 45

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("4.5").assertIsDisplayed()
        composeTestRule.onNodeWithText("(45)").assertIsDisplayed()
    }

    @Test
    fun whenRatingHasManyDecimalPlacesThenDisplaysFormattedRating() {
        // Given
        val rating = 3.14159
        val reviews = 100

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("3.1").assertIsDisplayed()
        composeTestRule.onNodeWithText("(100)").assertIsDisplayed()
    }

    @Test
    fun whenReviewsIsZeroThenDisplaysZeroReviews() {
        // Given
        val rating = 4.0
        val reviews = 0

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("4.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("(0)").assertIsDisplayed()
    }

    @Test
    fun whenReviewsIsVeryLargeThenDisplaysLargeNumber() {
        // Given
        val rating = 4.5
        val reviews = 999999

        // When
        composeTestRule.setContent {
            RatingStars(rating = rating, reviews = reviews)
        }

        // Then
        composeTestRule.onNodeWithText("4.5").assertIsDisplayed()
        composeTestRule.onNodeWithText("(999999)").assertIsDisplayed()
    }
} 