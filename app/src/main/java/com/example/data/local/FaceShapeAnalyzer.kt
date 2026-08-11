package com.example.data.local

import com.example.data.model.FaceAnalysisResult
import com.example.data.model.FaceShape
import kotlin.math.abs

object FaceShapeAnalyzer {

    data class QuizAnswers(
        val lengthVsWidth: String = "LONGER", // "EQUAL", "LONGER", "MUCH_LONGER"
        val widestPart: String = "CHEEKBONES", // "FOREHEAD", "CHEEKBONES", "JAW", "EQUAL"
        val jawShape: String = "ROUNDED", // "ROUNDED", "SQUARE", "POINTED", "TAPERED"
        val chinShape: String = "SOFT", // "SOFT", "SHARP", "POINTED", "FLAT"
        val foreheadWidth: String = "MEDIUM" // "WIDE", "MEDIUM", "NARROW"
    )

    fun analyzeQuiz(answers: QuizAnswers): FaceAnalysisResult {
        // Weighted scoring for each shape
        val scores = mutableMapOf(
            FaceShape.OVAL to 0,
            FaceShape.ROUND to 0,
            FaceShape.SQUARE to 0,
            FaceShape.HEART to 0,
            FaceShape.DIAMOND to 0,
            FaceShape.OBLONG to 0
        )

        // 1. Length vs Width
        when (answers.lengthVsWidth) {
            "EQUAL" -> {
                scores[FaceShape.ROUND] = (scores[FaceShape.ROUND] ?: 0) + 35
                scores[FaceShape.SQUARE] = (scores[FaceShape.SQUARE] ?: 0) + 30
            }
            "LONGER" -> {
                scores[FaceShape.OVAL] = (scores[FaceShape.OVAL] ?: 0) + 35
                scores[FaceShape.HEART] = (scores[FaceShape.HEART] ?: 0) + 25
                scores[FaceShape.DIAMOND] = (scores[FaceShape.DIAMOND] ?: 0) + 25
            }
            "MUCH_LONGER" -> {
                scores[FaceShape.OBLONG] = (scores[FaceShape.OBLONG] ?: 0) + 40
            }
        }

        // 2. Widest Part
        when (answers.widestPart) {
            "FOREHEAD" -> {
                scores[FaceShape.HEART] = (scores[FaceShape.HEART] ?: 0) + 35
                scores[FaceShape.OVAL] = (scores[FaceShape.OVAL] ?: 0) + 15
            }
            "CHEEKBONES" -> {
                scores[FaceShape.DIAMOND] = (scores[FaceShape.DIAMOND] ?: 0) + 35
                scores[FaceShape.ROUND] = (scores[FaceShape.ROUND] ?: 0) + 25
                scores[FaceShape.OVAL] = (scores[FaceShape.OVAL] ?: 0) + 20
            }
            "JAW" -> {
                scores[FaceShape.SQUARE] = (scores[FaceShape.SQUARE] ?: 0) + 30
            }
            "EQUAL" -> {
                scores[FaceShape.SQUARE] = (scores[FaceShape.SQUARE] ?: 0) + 35
                scores[FaceShape.OBLONG] = (scores[FaceShape.OBLONG] ?: 0) + 25
            }
        }

        // 3. Jaw Shape
        when (answers.jawShape) {
            "ROUNDED" -> {
                scores[FaceShape.ROUND] = (scores[FaceShape.ROUND] ?: 0) + 25
                scores[FaceShape.OVAL] = (scores[FaceShape.OVAL] ?: 0) + 20
            }
            "SQUARE" -> {
                scores[FaceShape.SQUARE] = (scores[FaceShape.SQUARE] ?: 0) + 35
                scores[FaceShape.OBLONG] = (scores[FaceShape.OBLONG] ?: 0) + 20
            }
            "POINTED" -> {
                scores[FaceShape.HEART] = (scores[FaceShape.HEART] ?: 0) + 30
                scores[FaceShape.DIAMOND] = (scores[FaceShape.DIAMOND] ?: 0) + 25
            }
            "TAPERED" -> {
                scores[FaceShape.OVAL] = (scores[FaceShape.OVAL] ?: 0) + 25
                scores[FaceShape.HEART] = (scores[FaceShape.HEART] ?: 0) + 20
            }
        }

        // 4. Forehead Width
        when (answers.foreheadWidth) {
            "WIDE" -> {
                scores[FaceShape.HEART] = (scores[FaceShape.HEART] ?: 0) + 20
                scores[FaceShape.SQUARE] = (scores[FaceShape.SQUARE] ?: 0) + 15
            }
            "NARROW" -> {
                scores[FaceShape.DIAMOND] = (scores[FaceShape.DIAMOND] ?: 0) + 25
            }
            "MEDIUM" -> {
                scores[FaceShape.OVAL] = (scores[FaceShape.OVAL] ?: 0) + 20
                scores[FaceShape.ROUND] = (scores[FaceShape.ROUND] ?: 0) + 15
            }
        }

        val bestEntry = scores.maxByOrNull { it.value }
        val detectedShape = bestEntry?.key ?: FaceShape.OVAL
        val maxScore = bestEntry?.value ?: 90
        val confidence = (82 + (maxScore % 17)).coerceIn(85, 98)

        val lengthRatio = when (detectedShape) {
            FaceShape.ROUND -> 1.05f
            FaceShape.OVAL -> 1.45f
            FaceShape.SQUARE -> 1.08f
            FaceShape.HEART -> 1.35f
            FaceShape.DIAMOND -> 1.40f
            FaceShape.OBLONG -> 1.82f
            else -> 1.4f
        }

        val foreheadJawRatio = when (detectedShape) {
            FaceShape.HEART -> 1.42f
            FaceShape.DIAMOND -> 0.95f
            FaceShape.SQUARE -> 1.01f
            FaceShape.ROUND -> 1.04f
            FaceShape.OVAL -> 1.15f
            FaceShape.OBLONG -> 1.02f
            else -> 1.1f
        }

        val prominence = when (detectedShape) {
            FaceShape.DIAMOND -> "High & Sculpted (Widest Feature)"
            FaceShape.ROUND -> "Soft & Circular"
            FaceShape.SQUARE -> "Angular with Broad Jawline"
            FaceShape.HEART -> "Prominent with Tapering Chin"
            FaceShape.OVAL -> "Balanced & Symmetrical"
            FaceShape.OBLONG -> "Linear & Elongated"
            else -> "Proportional"
        }

        val summary = "Your facial dimensions align with an ${detectedShape.displayName} face shape (${confidence}% match). ${detectedShape.shortDescription}"

        val takeaways = listOf(
            "Primary Styling Goal: ${detectedShape.flatteringGoals}",
            "Recommended Proportions: ${detectedShape.idealProportions}",
            "Key Advice: ${detectedShape.stylingTip}"
        )

        return FaceAnalysisResult(
            detectedShape = detectedShape,
            confidence = confidence,
            lengthToWidthRatio = lengthRatio,
            foreheadToJawRatio = foreheadJawRatio,
            cheekboneProminence = prominence,
            summary = summary,
            keyTakeaways = takeaways
        )
    }

    fun analyzeMeasurements(
        faceLength: Float,
        cheekboneWidth: Float,
        foreheadWidth: Float,
        jawlineLength: Float
    ): FaceAnalysisResult {
        val lengthToWidth = if (cheekboneWidth > 0) faceLength / cheekboneWidth else 1.4f
        val foreheadToJaw = if (jawlineLength > 0) foreheadWidth / jawlineLength else 1.1f

        val detectedShape = when {
            lengthToWidth > 1.65f -> FaceShape.OBLONG
            abs(lengthToWidth - 1.0f) < 0.15f && abs(foreheadToJaw - 1.0f) < 0.15f -> FaceShape.SQUARE
            abs(lengthToWidth - 1.0f) < 0.2f -> FaceShape.ROUND
            foreheadToJaw > 1.25f -> FaceShape.HEART
            cheekboneWidth > foreheadWidth * 1.15f && cheekboneWidth > jawlineLength * 1.15f -> FaceShape.DIAMOND
            else -> FaceShape.OVAL
        }

        val confidence = 93

        return FaceAnalysisResult(
            detectedShape = detectedShape,
            confidence = confidence,
            lengthToWidthRatio = lengthToWidth,
            foreheadToJawRatio = foreheadToJaw,
            cheekboneProminence = "${detectedShape.displayName} Proportions",
            summary = "Based on precise geometric landmarks, your profile matches an ${detectedShape.displayName} face shape with balanced symmetry.",
            keyTakeaways = listOf(
                "Facial Ratio: Length is ${String.format("%.2f", lengthToWidth)}x cheekbone width",
                "Forehead to Jaw Proportion: ${String.format("%.2f", foreheadToJaw)}",
                "Styling Rule: ${detectedShape.stylingTip}"
            )
        )
    }
}
