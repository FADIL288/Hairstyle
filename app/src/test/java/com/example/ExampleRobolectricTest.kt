package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.FaceShapeAnalyzer
import com.example.data.local.HairstyleCatalog
import com.example.data.local.ProductCatalog
import com.example.data.model.FaceShape
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("FaceShape Hair & Styling", appName)
    }

    @Test
    fun `test face shape quiz analyzer detects round face`() {
        val answers = FaceShapeAnalyzer.QuizAnswers(
            lengthVsWidth = "EQUAL",
            widestPart = "CHEEKBONES",
            jawShape = "ROUNDED",
            chinShape = "SOFT",
            foreheadWidth = "MEDIUM"
        )
        val result = FaceShapeAnalyzer.analyzeQuiz(answers)
        assertEquals(FaceShape.ROUND, result.detectedShape)
        assertTrue(result.confidence in 85..99)
    }

    @Test
    fun `test face shape quiz analyzer detects square face`() {
        val answers = FaceShapeAnalyzer.QuizAnswers(
            lengthVsWidth = "EQUAL",
            widestPart = "JAW",
            jawShape = "SQUARE",
            chinShape = "FLAT",
            foreheadWidth = "WIDE"
        )
        val result = FaceShapeAnalyzer.analyzeQuiz(answers)
        assertEquals(FaceShape.SQUARE, result.detectedShape)
    }

    @Test
    fun `test hairstyle catalog contains hairstyles for all face shapes`() {
        FaceShape.entries.forEach { shape ->
            val styles = HairstyleCatalog.getHairstylesForFaceShape(shape)
            assertTrue("Hairstyles should exist for ${shape.name}", styles.isNotEmpty())
        }
    }

    @Test
    fun `test product catalog contains styling products`() {
        val products = ProductCatalog.products
        assertTrue("Product catalog should not be empty", products.isNotEmpty())
        val seaSalt = ProductCatalog.getProductById("prod_sea_salt")
        assertNotNull(seaSalt)
        assertEquals(1, seaSalt?.shineLevel)
    }
}
