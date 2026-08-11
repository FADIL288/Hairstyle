package com.example.data.model

data class Hairstyle(
    val id: String,
    val name: String,
    val category: String,
    val suitableFaceShapes: List<FaceShape>,
    val targetGender: String, // "Men", "Women", "Unisex"
    val hairLength: String, // "Short", "Medium", "Long"
    val suitableTextures: List<String>, // "Straight", "Wavy", "Curly", "Coily", "Fine", "Thick"
    val difficulty: String, // "Easy (5 min)", "Medium (10 min)", "Advanced (15+ min)"
    val description: String,
    val whyItBalancesFace: String,
    val stylingSteps: List<String>,
    val barberInstructions: String,
    val recommendedProductIds: List<String>,
    val tags: List<String>,
    val isTrending: Boolean = false
)
