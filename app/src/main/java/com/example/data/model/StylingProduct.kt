package com.example.data.model

enum class ProductCategory(val displayName: String, val badgeColorHex: Long) {
    PRE_STYLER("Pre-Styler", 0xFF3B82F6),
    MAIN_STYLER("Main Styler", 0xFFEAB308),
    POST_STYLER("Post-Styler / Finisher", 0xFF10B981),
    TREATMENT("Care & Treatment", 0xFF8B5CF6)
}

enum class ProductType(val title: String) {
    SEA_SALT_SPRAY("Sea Salt Spray"),
    MATTE_CLAY("Matte Styling Clay"),
    POMADE_WATER_BASED("Water-Based Pomade"),
    STYLING_PASTE("Texturizing Paste"),
    TEXTURE_POWDER("Volumizing Texture Powder"),
    HAIR_WAX("Pliable Hair Wax"),
    CURL_CREAM("Curl Defining Cream"),
    LEAVE_IN_CONDITIONER("Leave-In Conditioner"),
    HAIR_OIL("Argan & Jojoba Hair Oil"),
    HEAT_PROTECTANT("Heat Protectant Spray"),
    FINISHING_SPRAY("Flexible Hairspray"),
    VOLUMIZING_MOUSSE("Volumizing Mousse"),
    DRY_SHAMPOO("Purifying Dry Shampoo")
}

data class StylingProduct(
    val id: String,
    val name: String,
    val brandType: String,
    val category: ProductCategory,
    val productType: ProductType,
    val holdLevel: Int, // 1 to 5
    val shineLevel: Int, // 1 (Matte) to 5 (High Shine)
    val hairTypes: List<String>, // Straight, Wavy, Curly, Coily, Fine, Thick
    val keyIngredients: List<String>,
    val description: String,
    val howToApply: String,
    val bestForFaceShapes: List<FaceShape>,
    val priceTier: String, // "$", "$$", "$$$"
    val rating: Float = 4.8f,
    val tips: String
)
