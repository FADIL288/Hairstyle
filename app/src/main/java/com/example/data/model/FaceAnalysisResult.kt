package com.example.data.model

data class FaceAnalysisResult(
    val id: String = java.util.UUID.randomUUID().toString(),
    val detectedShape: FaceShape,
    val confidence: Int, // e.g. 94%
    val lengthToWidthRatio: Float,
    val foreheadToJawRatio: Float,
    val cheekboneProminence: String,
    val summary: String,
    val keyTakeaways: List<String>,
    val timestamp: Long = System.currentTimeMillis()
)

data class RoutineStep(
    val stepNumber: Int,
    val stage: String, // "Pre-Styling", "Form & Shape", "Main Styling", "Finish & Lock"
    val title: String,
    val description: String,
    val suggestedProduct: String,
    val durationSeconds: Int,
    val isCompleted: Boolean = false
)
