package com.example.data.local

import com.example.data.model.FaceShape
import com.example.data.model.ProductCategory
import com.example.data.model.ProductType
import com.example.data.model.StylingProduct

object ProductCatalog {

    val products: List<StylingProduct> = listOf(
        StylingProduct(
            id = "prod_sea_salt",
            name = "Oceanic Texturizing Sea Salt Spray",
            brandType = "Artisan Barber Lab",
            category = ProductCategory.PRE_STYLER,
            productType = ProductType.SEA_SALT_SPRAY,
            holdLevel = 2,
            shineLevel = 1,
            hairTypes = listOf("Straight", "Wavy", "Fine", "Medium"),
            keyIngredients = listOf("Atlantic Sea Salt", "Kelp Extract", "Epsom Salt", "Aloe Vera"),
            description = "Infuses lightweight gritty texture, natural beachy waves, and remarkable root volume without drying out the scalp.",
            howToApply = "Spritz 4-6 pumps evenly into towel-damp hair from roots to ends. Blow dry with a round brush or scrunch and air dry for messy movement.",
            bestForFaceShapes = listOf(FaceShape.ROUND, FaceShape.SQUARE, FaceShape.HEART, FaceShape.DIAMOND),
            priceTier = "$$",
            rating = 4.9f,
            tips = "Ideal foundation before applying matte clay or pomade to add 2x volume and hold."
        ),

        StylingProduct(
            id = "prod_matte_clay",
            name = "SculptCraft High-Hold Matte Clay",
            brandType = "Salon Atelier",
            category = ProductCategory.MAIN_STYLER,
            productType = ProductType.MATTE_CLAY,
            holdLevel = 5,
            shineLevel = 1,
            hairTypes = listOf("Straight", "Wavy", "Thick", "Medium"),
            keyIngredients = listOf("Bentonite Clay", "Beeswax", "Kaolin", "Shea Butter"),
            description = "Maximum all-day pliable hold with a completely zero-shine, natural textured finish. Reshapeable throughout the day.",
            howToApply = "Rub a dime-sized amount vigorously between palms until completely warm and clear. Work backward through dry hair, then style with fingers or wide comb.",
            bestForFaceShapes = listOf(FaceShape.ROUND, FaceShape.OVAL, FaceShape.SQUARE),
            priceTier = "$$",
            rating = 4.8f,
            tips = "Apply strictly to dry hair for best matte hold; works great for textured crops and quiffs."
        ),

        StylingProduct(
            id = "prod_vol_mousse",
            name = "AirLift Weightless Volumizing Mousse",
            brandType = "Haute Coiffure",
            category = ProductCategory.PRE_STYLER,
            productType = ProductType.VOLUMIZING_MOUSSE,
            holdLevel = 3,
            shineLevel = 2,
            hairTypes = listOf("Fine", "Medium", "Straight", "Wavy"),
            keyIngredients = listOf("Hydrolyzed Wheat Protein", "Pro-Vitamin B5", "Bamboo Extract"),
            description = "Featherlight foam that expands each hair strand, adding bouncy body, structural fullness, and thermal protection.",
            howToApply = "Dispense an egg-sized ball into palms. Distribute evenly through damp roots to mid-lengths before blow drying upside down.",
            bestForFaceShapes = listOf(FaceShape.OBLONG, FaceShape.DIAMOND, FaceShape.HEART),
            priceTier = "$$",
            rating = 4.7f,
            tips = "Crucial for adding side fullness on Oblong and Diamond face shapes."
        ),

        StylingProduct(
            id = "prod_pomade_water",
            name = "Crown Classic Water-Soluble Pomade",
            brandType = "Heritage Grooming",
            category = ProductCategory.MAIN_STYLER,
            productType = ProductType.POMADE_WATER_BASED,
            holdLevel = 4,
            shineLevel = 4,
            hairTypes = listOf("Straight", "Wavy", "Thick", "Medium"),
            keyIngredients = listOf("Purified Water Base", "Castor Oil", "Glycerin", "Cedarwood Extract"),
            description = "Crisp, defined hold with a sophisticated medium-high sheen. Rinses out effortlessly with warm water in one wash.",
            howToApply = "Warm a nickel-sized amount in palms. Distribute from roots upward into damp hair for higher shine, or dry hair for firmer hold. Comb into place.",
            bestForFaceShapes = listOf(FaceShape.OVAL, FaceShape.SQUARE, FaceShape.OBLONG),
            priceTier = "$$",
            rating = 4.9f,
            tips = "The gold standard for classic side-parts, slick backs, and pompadours."
        ),

        StylingProduct(
            id = "prod_texture_paste",
            name = "FlexForm Texturizing Styling Paste",
            brandType = "Studio Neo",
            category = ProductCategory.MAIN_STYLER,
            productType = ProductType.STYLING_PASTE,
            holdLevel = 3,
            shineLevel = 2,
            hairTypes = listOf("Wavy", "Straight", "Medium", "Short"),
            keyIngredients = listOf("Carnauba Wax", "Lanolin", "Jojoba Esters"),
            description = "Medium versatile hold with a low-luster satin finish. Keeps layers piecey, touchable, and soft without any crunchiness.",
            howToApply = "Emulsify a pea-sized amount between fingers and piece out individual sections, bangs, or layered ends.",
            bestForFaceShapes = listOf(FaceShape.SQUARE, FaceShape.HEART, FaceShape.DIAMOND),
            priceTier = "$$",
            rating = 4.6f,
            tips = "Softens sharp jawlines by creating wispy, layered movement."
        ),

        StylingProduct(
            id = "prod_texture_powder",
            name = "MicroDust Instant Volumizing Powder",
            brandType = "Vertex Pro",
            category = ProductCategory.POST_STYLER,
            productType = ProductType.TEXTURE_POWDER,
            holdLevel = 4,
            shineLevel = 1,
            hairTypes = listOf("Fine", "Thin", "Straight", "Wavy"),
            keyIngredients = listOf("Silica Silylate", "Rice Starch", "Tapioca Root"),
            description = "Gravity-defying root lift and immediate matte separation. Absorbs excess oils while providing incredible reworkable grip.",
            howToApply = "Tap lightly onto dry roots where vertical height is desired. Massage with fingertips to activate dramatic lift.",
            bestForFaceShapes = listOf(FaceShape.ROUND, FaceShape.HEART),
            priceTier = "$",
            rating = 4.9f,
            tips = "Instantly turns flat, lifeless hair into a voluminous textured fringe or messy crop."
        ),

        StylingProduct(
            id = "prod_curl_cream",
            name = "VelvetCurl Defining Moisture Cream",
            brandType = "Botanique Haircare",
            category = ProductCategory.MAIN_STYLER,
            productType = ProductType.CURL_CREAM,
            holdLevel = 2,
            shineLevel = 3,
            hairTypes = listOf("Wavy", "Curly", "Coily", "Thick"),
            keyIngredients = listOf("Raw Shea Butter", "Avocado Oil", "Flaxseed Gel", "Coconut Nectar"),
            description = "Hydrates, defines spirals, and eliminates frizz without weighing curls down. Delivers soft, springy, clumped curls with healthy bounce.",
            howToApply = "Apply generously to soaking wet hair in sections using praying hands or raking technique. Scrunch upward and air dry or diffuse.",
            bestForFaceShapes = listOf(FaceShape.SQUARE, FaceShape.OBLONG, FaceShape.DIAMOND),
            priceTier = "$$",
            rating = 4.8f,
            tips = "Adds horizontal balance and soft framing around the cheekbones."
        ),

        StylingProduct(
            id = "prod_leave_in",
            name = "HydraSilk Multi-Action Leave-In Spray",
            brandType = "SilkTherapy Lab",
            category = ProductCategory.TREATMENT,
            productType = ProductType.LEAVE_IN_CONDITIONER,
            holdLevel = 1,
            shineLevel = 3,
            hairTypes = listOf("Straight", "Wavy", "Curly", "Coily", "Fine", "Thick"),
            keyIngredients = listOf("Silk Amino Acids", "Hyaluronic Acid", "Argan Oil", "Green Tea"),
            description = "Detangles, softens, protects against UV and humidity, and infuses essential moisture for silky, manageable strands.",
            howToApply = "Mist 5-8 sprays evenly over damp hair before combing or layering other stylers. Do not rinse.",
            bestForFaceShapes = listOf(FaceShape.OVAL, FaceShape.ROUND, FaceShape.SQUARE, FaceShape.HEART, FaceShape.DIAMOND, FaceShape.OBLONG),
            priceTier = "$$",
            rating = 4.9f,
            tips = "Every styling routine's mandatory priming step for healthy cuticle sealing."
        ),

        StylingProduct(
            id = "prod_argan_oil",
            name = "Moroccan Gold Pure Argan Hair Elixir",
            brandType = "Oasis Luxury",
            category = ProductCategory.POST_STYLER,
            productType = ProductType.HAIR_OIL,
            holdLevel = 1,
            shineLevel = 5,
            hairTypes = listOf("Thick", "Medium", "Wavy", "Curly", "Coily"),
            keyIngredients = listOf("100% Cold-Pressed Argan Oil", "Sweet Almond Oil", "Vitamin E"),
            description = "Rich antioxidant elixir that tames flyaways, seals split ends, and leaves hair with a radiant, glass-like salon luster.",
            howToApply = "Rub 2-3 drops between palms and gently smooth over dry mid-lengths and ends as the final finishing step.",
            bestForFaceShapes = listOf(FaceShape.OVAL, FaceShape.HEART, FaceShape.DIAMOND),
            priceTier = "$$$",
            rating = 4.8f,
            tips = "Use sparingly; a tiny drop goes a long way in adding brilliant shine."
        ),

        StylingProduct(
            id = "prod_heat_shield",
            name = "ThermaGuard 450° Heat Shield",
            brandType = "Haute Coiffure",
            category = ProductCategory.PRE_STYLER,
            productType = ProductType.HEAT_PROTECTANT,
            holdLevel = 1,
            shineLevel = 2,
            hairTypes = listOf("Straight", "Wavy", "Curly", "Fine", "Thick"),
            keyIngredients = listOf("Hydrolyzed Keratin", "Silicon Micro-Polymers", "Marula Oil"),
            description = "Shields fragile hair strands up to 450°F / 232°C from blow dryers, straighteners, and curling wands while locking in moisture.",
            howToApply = "Section damp or dry hair and spray evenly 6 inches away before applying any heat styling tool.",
            bestForFaceShapes = listOf(FaceShape.OVAL, FaceShape.ROUND, FaceShape.SQUARE, FaceShape.HEART, FaceShape.DIAMOND, FaceShape.OBLONG),
            priceTier = "$",
            rating = 4.7f,
            tips = "Always prep with heat protection prior to using high-heat blow drying for maximum volume."
        ),

        StylingProduct(
            id = "prod_finish_spray",
            name = "AeroLock Flexible Touch Finishing Spray",
            brandType = "Salon Atelier",
            category = ProductCategory.POST_STYLER,
            productType = ProductType.FINISHING_SPRAY,
            holdLevel = 4,
            shineLevel = 2,
            hairTypes = listOf("Straight", "Wavy", "Curly", "Fine", "Thick"),
            keyIngredients = listOf("Micro-Polymers", "Pro-Vitamin B5", "Sunscreen UV Filters"),
            description = "All-day humidity resistance and flexible shape retention without stiffness, flaking, or residue. Brushable finish.",
            howToApply = "Hold can 10-12 inches away from finished hairstyle and mist in sweeping motions to lock the look.",
            bestForFaceShapes = listOf(FaceShape.OVAL, FaceShape.ROUND, FaceShape.SQUARE, FaceShape.OBLONG),
            priceTier = "$$",
            rating = 4.8f,
            tips = "Lock in volume after blow drying to prevent gravity from pulling down your shape."
        ),

        StylingProduct(
            id = "prod_thickening_tonic",
            name = "DenseFiber Botanical Thickening Tonic",
            brandType = "Barber Apothecary",
            category = ProductCategory.PRE_STYLER,
            productType = ProductType.SEA_SALT_SPRAY,
            holdLevel = 2,
            shineLevel = 2,
            hairTypes = listOf("Fine", "Thin", "Straight"),
            keyIngredients = listOf("Biotin", "Saw Palmetto", "Caffeine", "Peppermint Oil"),
            description = "Fulfills the need for thicker-looking individual hair shafts, creating a noticeably denser, fuller scalp appearance.",
            howToApply = "Spray directly onto damp scalp and hair roots. Comb through from front to back, then blow dry.",
            bestForFaceShapes = listOf(FaceShape.OBLONG, FaceShape.ROUND, FaceShape.HEART),
            priceTier = "$$",
            rating = 4.7f,
            tips = "Pairs exceptionally well with matte clay for guys wanting high texture and coverage."
        )
    )

    fun getProductById(id: String): StylingProduct? {
        return products.find { it.id == id }
    }

    fun getProductsForFaceShape(faceShape: FaceShape): List<StylingProduct> {
        return products.filter { it.bestForFaceShapes.contains(faceShape) }
    }

    fun getProductsByCategory(category: ProductCategory): List<StylingProduct> {
        return products.filter { it.category == category }
    }
}
