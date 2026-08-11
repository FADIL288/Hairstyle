package com.example.data.local

import com.example.data.model.FaceShape
import com.example.data.model.Hairstyle

object HairstyleCatalog {

    val hairstyles: List<Hairstyle> = listOf(
        // === ROUND FACE ===
        Hairstyle(
            id = "style_round_m1",
            name = "High Skin Fade with Textured Quiff",
            category = "Quiff & Fade",
            suitableFaceShapes = listOf(FaceShape.ROUND, FaceShape.SQUARE),
            targetGender = "Men",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Thick", "Medium"),
            difficulty = "Medium (10 min)",
            description = "A sharp, high skin fade on the sides combined with 3-4 inches of vertical textured volume on top.",
            whyItBalancesFace = "The tight sides slim the widest part of round cheeks, while the upward height on top draws the eye vertically, instantly elongating the face profile.",
            stylingSteps = listOf(
                "Apply 4 spritzes of Sea Salt Spray to towel-damp hair.",
                "Blow dry using a vent brush, directing the front hair upward and backward for maximum root lift.",
                "Warm a dime-sized amount of High-Hold Matte Clay in palms.",
                "Work into dry hair from roots to ends, piecing out textured sections with your fingertips.",
                "Finish with a light mist of flexible hairspray for all-day hold."
            ),
            barberInstructions = "Ask for a high skin fade (start with foil shaver/0 at the bottom, blending up to a #2). Leave 3.5 inches on top with point cutting and texturizing for piecey movement.",
            recommendedProductIds = listOf("prod_sea_salt", "prod_matte_clay", "prod_finish_spray"),
            tags = listOf("Skin Fade", "Quiff", "Elongating", "Voluminous"),
            isTrending = true
        ),

        Hairstyle(
            id = "style_round_m2",
            name = "Angular Textured French Crop",
            category = "Textured Crop",
            suitableFaceShapes = listOf(FaceShape.ROUND, FaceShape.HEART),
            targetGender = "Men",
            hairLength = "Short",
            suitableTextures = listOf("Straight", "Wavy", "Thick", "Coily"),
            difficulty = "Easy (5 min)",
            description = "A blunt or asymmetrical textured crop fringe paired with a tight mid-to-high fade.",
            whyItBalancesFace = "The angular, jagged fringe breaks the circular symmetry of a round face and introduces sharp, defined geometric lines.",
            stylingSteps = listOf(
                "Towel dry hair until slightly damp.",
                "Spritz a light mist of thickening tonic or sea salt spray.",
                "Blow dry forward using your fingers to create natural separation.",
                "Dust MicroDust Texture Powder onto the top and scrunch forward.",
                "Define the front fringe with a pinch of matte clay."
            ),
            barberInstructions = "Mid skin fade on back and sides with a textured crop on top (around 2 inches). Chop the fringe with point-cutting so it has an angular, jagged edge rather than a blunt line.",
            recommendedProductIds = listOf("prod_texture_powder", "prod_matte_clay", "prod_thickening_tonic"),
            tags = listOf("Low Maintenance", "Crop", "Angular", "Modern"),
            isTrending = true
        ),

        Hairstyle(
            id = "style_round_w1",
            name = "Long Layered Cut with Curtain Bangs",
            category = "Long Layers",
            suitableFaceShapes = listOf(FaceShape.ROUND, FaceShape.SQUARE),
            targetGender = "Women",
            hairLength = "Long",
            suitableTextures = listOf("Straight", "Wavy", "Curly", "Fine", "Thick"),
            difficulty = "Medium (10 min)",
            description = "Flowing long layers that cascade past the collarbone paired with cheekbone-grazing curtain bangs.",
            whyItBalancesFace = "The curtain bangs drape along the sides of the face, narrowing broad cheekbones, while vertical length pulls the eye down for an oval silhouette.",
            stylingSteps = listOf(
                "Prep damp hair with HydraSilk Leave-In Spray and Heat Shield.",
                "Blow dry bangs forward with a round ceramic brush, then sweep outward at the temples.",
                "Add soft loose waves to the bottom layers using a 1.25-inch curling wand.",
                "Smooth Moroccan Argan Hair Elixir through ends for silkiness.",
                "Shake out gently with fingers for an effortless, airy finish."
            ),
            barberInstructions = "Ask for long face-framing layers starting below the chin, with soft cheekbone-grazing curtain bangs styled with feathered texturizing.",
            recommendedProductIds = listOf("prod_leave_in", "prod_heat_shield", "prod_argan_oil"),
            tags = listOf("Curtain Bangs", "Face Framing", "Slimming", "Elegant"),
            isTrending = true
        ),

        Hairstyle(
            id = "style_round_w2",
            name = "Asymmetrical Angled Lob",
            category = "Modern Lob",
            suitableFaceShapes = listOf(FaceShape.ROUND, FaceShape.DIAMOND),
            targetGender = "Women",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Fine", "Medium"),
            difficulty = "Easy (5 min)",
            description = "A collarbone-grazing long bob that is slightly shorter in the back and sharply angled forward.",
            whyItBalancesFace = "The steep forward angle creates dramatic vertical and diagonal lines that cut through round facial fullness.",
            stylingSteps = listOf(
                "Apply Volumizing Mousse to damp roots for lift.",
                "Blow dry straight with a flat paddle brush, angling ends slightly inward.",
                "Apply 2 drops of Argan Oil to the ends for brilliant sheen.",
                "Optional: Deep side part to add asymmetrical balance."
            ),
            barberInstructions = "Ask for an A-line long bob hitting collarbone length in front and resting at the nape in the back, with interior texturizing to reduce weight.",
            recommendedProductIds = listOf("prod_vol_mousse", "prod_argan_oil", "prod_finish_spray"),
            tags = listOf("Chic", "Angled", "Sleek", "Professional")
        ),

        // === OVAL FACE ===
        Hairstyle(
            id = "style_oval_m1",
            name = "Modern Pompadour with Low Taper",
            category = "Classic Volume",
            suitableFaceShapes = listOf(FaceShape.OVAL, FaceShape.SQUARE),
            targetGender = "Men",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Thick"),
            difficulty = "Medium (10 min)",
            description = "High sweeping volume swept back cleanly with softly tapered sideburns and neckline.",
            whyItBalancesFace = "Oval faces have natural symmetry, making them the perfect canvas for bold, swept-back styles that showcase the jaw and hairline.",
            stylingSteps = listOf(
                "Apply Sea Salt Spray to damp hair from roots to ends.",
                "Blow dry backward using a round brush, directing hair upward and over the crown.",
                "Warm Water-Soluble Pomade or Matte Clay between hands.",
                "Rake through hair from front to back, shaping a sweeping curve with your comb.",
                "Lock in position with flexible finishing spray."
            ),
            barberInstructions = "Keep 4 inches of length in front tapering down to 3 inches at the crown. Low scissor taper or #2 fade on the sides with clean natural lines.",
            recommendedProductIds = listOf("prod_sea_salt", "prod_pomade_water", "prod_finish_spray"),
            tags = listOf("Timeless", "Gentleman", "Volume", "Sleek"),
            isTrending = true
        ),

        Hairstyle(
            id = "style_oval_w1",
            name = "Collarbone Blunt Lob with Soft Waves",
            category = "Lob & Waves",
            suitableFaceShapes = listOf(FaceShape.OVAL, FaceShape.HEART),
            targetGender = "Women",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Fine", "Thick"),
            difficulty = "Easy (5 min)",
            description = "A blunt-cut medium bob resting right at the collarbone, styled with subtle relaxed bends.",
            whyItBalancesFace = "Frames the naturally balanced oval jawline while maintaining healthy thickness at the base without overwhelming proportions.",
            stylingSteps = listOf(
                "Apply Leave-In Spray and Heat Shield to towel-dried hair.",
                "Rough dry to 80%, then create 3 large vertical twists with a flat iron.",
                "Spritz Sea Salt Spray and gently rake through with wide-tooth comb.",
                "Smooth a drop of Argan Oil onto mid-lengths for glass shine."
            ),
            barberInstructions = "Blunt baseline cut hitting right at the collarbone with very subtle point cutting on the ends for softness without removing weight.",
            recommendedProductIds = listOf("prod_leave_in", "prod_sea_salt", "prod_argan_oil"),
            tags = listOf("Trendy", "Versatile", "Soft Waves", "Collarbone")
        ),

        // === SQUARE FACE ===
        Hairstyle(
            id = "style_square_m1",
            name = "Textured French Crop with Soft Fade",
            category = "Soft Crop",
            suitableFaceShapes = listOf(FaceShape.SQUARE, FaceShape.OVAL),
            targetGender = "Men",
            hairLength = "Short",
            suitableTextures = listOf("Straight", "Wavy", "Thick", "Curly"),
            difficulty = "Easy (5 min)",
            description = "A short, heavily textured top with a wispy messy fringe that softens strong angular jawlines.",
            whyItBalancesFace = "The choppy, organic texture up top contrasts harmoniously with a strong, chiseled square jaw, providing effortless balance.",
            stylingSteps = listOf(
                "Towel dry hair and apply 2 pumps of Sea Salt Spray.",
                "Blow dry casually while ruffling hair with fingers.",
                "Emulsify a dime-sized amount of Texturizing Paste or Matte Clay.",
                "Work throughout hair to create messy separation with a piecey fringe."
            ),
            barberInstructions = "Ask for a mid taper fade on the sides (starting at #1) with 2 to 2.5 inches on top heavily razor-texturized for maximum movement and a wispy fringe.",
            recommendedProductIds = listOf("prod_sea_salt", "prod_texture_paste", "prod_texture_powder"),
            tags = listOf("Softening", "Textured", "Effortless", "Modern"),
            isTrending = true
        ),

        Hairstyle(
            id = "style_square_w1",
            name = "Soft Layered Beach Waves",
            category = "Romantic Waves",
            suitableFaceShapes = listOf(FaceShape.SQUARE, FaceShape.DIAMOND),
            targetGender = "Women",
            hairLength = "Long",
            suitableTextures = listOf("Wavy", "Straight", "Curly", "Thick"),
            difficulty = "Medium (10 min)",
            description = "Cascading, rounded layers with loose s-waves that curve softly around the jawbone.",
            whyItBalancesFace = "Soft curved waves diffuse the sharpness of a square jawline, creating a warm, romantic, and flattering frame.",
            stylingSteps = listOf(
                "Prep with Leave-In Conditioner and Heat Protectant Spray.",
                "Blow dry using a large barrel brush for gentle root lift.",
                "Wrap 2-inch sections around a curling wand away from the face, leaving 1 inch at the ends straight.",
                "Spritz with Sea Salt Spray and finger-comb for relaxed beach texture."
            ),
            barberInstructions = "Long rounded layers starting at the jawline with feathered perimeter cutting to soften corners.",
            recommendedProductIds = listOf("prod_leave_in", "prod_heat_shield", "prod_sea_salt"),
            tags = listOf("Romantic", "Beach Waves", "Jaw Softening", "Glam")
        ),

        Hairstyle(
            id = "style_square_u1",
            name = "Modern Wolf Cut with Feathered Layers",
            category = "Shag & Wolf Cut",
            suitableFaceShapes = listOf(FaceShape.SQUARE, FaceShape.OVAL, FaceShape.ROUND),
            targetGender = "Unisex",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Curly", "Thick"),
            difficulty = "Medium (10 min)",
            description = "A fusion of the classic shag and mullet with choppy crown volume, feathered side wings, and wispy tapered ends.",
            whyItBalancesFace = "The dynamic crown volume and feathered curtain layers wrap around angular temples and jaw corners, diffusing harsh angles.",
            stylingSteps = listOf(
                "Apply Sea Salt Spray and Volumizing Mousse into damp hair.",
                "Diffuser dry or air dry while scrunching crown sections upward.",
                "Work Texturizing Styling Paste into feathered ends to define wispy layers.",
                "Dust Texture Powder into roots for all-day rocker volume."
            ),
            barberInstructions = "Razor cut shag with short choppy layers at the crown, curtain bangs blended into cheekbone-skimming wings, and wispy layered lengths.",
            recommendedProductIds = listOf("prod_sea_salt", "prod_vol_mousse", "prod_texture_paste", "prod_texture_powder"),
            tags = listOf("Wolf Cut", "Edgy", "Unisex", "Feathered"),
            isTrending = true
        ),

        // === HEART FACE ===
        Hairstyle(
            id = "style_heart_m1",
            name = "Mid-Length Scissor Flow Cut",
            category = "Flow & Texture",
            suitableFaceShapes = listOf(FaceShape.HEART, FaceShape.DIAMOND),
            targetGender = "Men",
            hairLength = "Medium",
            suitableTextures = listOf("Wavy", "Straight", "Thick", "Medium"),
            difficulty = "Easy (5 min)",
            description = "A natural scissor-cut style with 4-5 inches of length pushed casually back and tucked behind the ears.",
            whyItBalancesFace = "The fullness around the ears and lower sides fills in the narrower pointed chin area, offsetting the wider forehead.",
            stylingSteps = listOf(
                "Apply Leave-In Conditioner Spray and a light pump of Sea Salt Spray.",
                "Comb back with fingers and allow to air dry 70%.",
                "Warm a pea-sized amount of Texturizing Paste in hands.",
                "Smooth backward over sides and crown, letting natural waves flow over the ears."
            ),
            barberInstructions = "Scissor-cut all over, leaving 4 to 5 inches on top and 2.5 to 3 inches on the sides with clean soft scissor-tapered edges (avoid high skin fades).",
            recommendedProductIds = listOf("prod_leave_in", "prod_sea_salt", "prod_texture_paste"),
            tags = listOf("Flow Cut", "Natural", "Scissor Work", "Casual"),
            isTrending = true
        ),

        Hairstyle(
            id = "style_heart_w1",
            name = "Chin-Length Textured Bob with Wispy Bangs",
            category = "French & Textured Bob",
            suitableFaceShapes = listOf(FaceShape.HEART, FaceShape.DIAMOND),
            targetGender = "Women",
            hairLength = "Short",
            suitableTextures = listOf("Straight", "Wavy", "Fine", "Medium"),
            difficulty = "Easy (5 min)",
            description = "A bob that hits squarely at chin level with textured ends and light, see-through wispy bangs.",
            whyItBalancesFace = "The chin-level width provides substantial fullness right where heart faces need it most (around the narrow jaw), while wispy bangs disguise forehead width.",
            stylingSteps = listOf(
                "Apply Volumizing Mousse to damp hair.",
                "Blow dry using a medium round brush, curving ends gently under at the chin.",
                "Style wispy bangs forward and blow dry on low heat for 10 seconds.",
                "Pinch ends with Texturizing Paste to give a playful French girl finish."
            ),
            barberInstructions = "Cut a chin-length textured bob with internal weight removal and lightweight eyelash-skimming wispy bangs.",
            recommendedProductIds = listOf("prod_vol_mousse", "prod_texture_paste", "prod_leave_in"),
            tags = listOf("Chin Bob", "French Style", "Wispy Bangs", "Flattering")
        ),

        Hairstyle(
            id = "style_heart_w2",
            name = "Voluminous Butterfly Layered Cut",
            category = "Butterfly Layers",
            suitableFaceShapes = listOf(FaceShape.HEART, FaceShape.OVAL, FaceShape.ROUND),
            targetGender = "Women",
            hairLength = "Long",
            suitableTextures = listOf("Straight", "Wavy", "Thick", "Medium"),
            difficulty = "Advanced (15+ min)",
            description = "Cascading winged layers that create the illusion of a short bob in front while retaining full long length in back.",
            whyItBalancesFace = "The winged chin-length layers flare outwards, instantly balancing a delicate, pointed chin with lush volume.",
            stylingSteps = listOf(
                "Prep with HydraSilk Leave-In and Heat Shield.",
                "Blow dry using velcro rollers or round hot brush, pinning crown sections up.",
                "Unroll after cooling for immense bounce and wing flare.",
                "Seal with Argan Oil and flexible finishing spray."
            ),
            barberInstructions = "Butterfly cut with short face layers cut at chin level and long flowing back layers, heavily blended.",
            recommendedProductIds = listOf("prod_leave_in", "prod_heat_shield", "prod_argan_oil", "prod_finish_spray"),
            tags = listOf("Butterfly Cut", "High Glamour", "Blowout", "Voluminous"),
            isTrending = true
        ),

        // === DIAMOND FACE ===
        Hairstyle(
            id = "style_diamond_m1",
            name = "Textured Brush-Up with Scissor Taper",
            category = "Brush-Up",
            suitableFaceShapes = listOf(FaceShape.DIAMOND, FaceShape.HEART),
            targetGender = "Men",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Thick"),
            difficulty = "Medium (10 min)",
            description = "Medium scissor-tapered sides with upward and slightly forward brushed texture that widens the forehead silhouette.",
            whyItBalancesFace = "By retaining hair density around the temples and brushing upward softly, it visually broadens the narrow forehead to harmonize with wide cheekbones.",
            stylingSteps = listOf(
                "Spritz Sea Salt Spray into damp roots.",
                "Blow dry upward and slightly outward at the temples.",
                "Warm Matte Clay in palms and work through roots to ends.",
                "Finger-comb into position for soft, matte lift."
            ),
            barberInstructions = "Keep sides at #3 or scissor-taper (do not shave to skin). Leave 3.5 inches on top with point texturizing for brush-up styling.",
            recommendedProductIds = listOf("prod_sea_salt", "prod_matte_clay"),
            tags = listOf("Brush Up", "Temple Width", "Sharp", "Modern")
        ),

        Hairstyle(
            id = "style_diamond_w1",
            name = "Shoulder-Length Lob with Flicked Ends & Side Bangs",
            category = "Flicked Lob",
            suitableFaceShapes = listOf(FaceShape.DIAMOND, FaceShape.SQUARE),
            targetGender = "Women",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Fine", "Thick"),
            difficulty = "Medium (10 min)",
            description = "A shoulder-grazing cut with side-swept bangs and outward flicked ends that create width at the chin and collarbone.",
            whyItBalancesFace = "Side bangs cover high cheekbone width, while the flicked ends add fullness around the narrow chin line.",
            stylingSteps = listOf(
                "Apply Volumizing Mousse through mid-lengths and ends.",
                "Blow dry with a round brush, turning the bottom 2 inches outward.",
                "Sweep bangs to one side with a cool shot of air.",
                "Mist AeroLock Finishing Spray to hold the flicked silhouette."
            ),
            barberInstructions = "Collarbone cut with long side-swept bangs blended into face framing layers, ends razor-cut to encourage outward flicking.",
            recommendedProductIds = listOf("prod_vol_mousse", "prod_finish_spray", "prod_leave_in"),
            tags = listOf("Retro Modern", "Flicked Ends", "Side Bangs", "Chic")
        ),

        // === OBLONG FACE ===
        Hairstyle(
            id = "style_oblong_m1",
            name = "Classic Crew Cut with Scissor Sides",
            category = "Classic Crew",
            suitableFaceShapes = listOf(FaceShape.OBLONG, FaceShape.OVAL),
            targetGender = "Men",
            hairLength = "Short",
            suitableTextures = listOf("Straight", "Wavy", "Thick", "Fine"),
            difficulty = "Easy (5 min)",
            description = "A balanced, proportional cut with fuller scissor-cut sides and a short, textured side-swept top (avoiding high top volume).",
            whyItBalancesFace = "Fuller sides add horizontal width, while the low-profile top prevents adding unwanted vertical height to an already long face.",
            stylingSteps = listOf(
                "Towel dry hair lightly.",
                "Emulsify a small amount of Texturizing Paste or Pomade.",
                "Sweep the front top diagonally across the forehead to break vertical lines.",
                "Pat sides neatly flat against the temples."
            ),
            barberInstructions = "Scissor cut sides (#4 or scissor-over-comb) keeping density. Keep the top short (1.5 to 2 inches) with a slight sweep in front; avoid high fades.",
            recommendedProductIds = listOf("prod_texture_paste", "prod_pomade_water"),
            tags = listOf("Proportional", "Low Profile", "Gentleman", "Clean")
        ),

        Hairstyle(
            id = "style_oblong_w1",
            name = "Eyebrow-Skimming Bangs with Voluminous Waves",
            category = "Bangs & Body Waves",
            suitableFaceShapes = listOf(FaceShape.OBLONG, FaceShape.DIAMOND),
            targetGender = "Women",
            hairLength = "Medium",
            suitableTextures = listOf("Straight", "Wavy", "Curly", "Thick"),
            difficulty = "Medium (10 min)",
            description = "Full, soft straight-across bangs resting right at the eyebrow line paired with wide horizontal beach waves.",
            whyItBalancesFace = "The horizontal line of the bangs cuts off one-third of the face height, instantly creating a harmonious, compact oval visual appearance.",
            stylingSteps = listOf(
                "Apply Volumizing Mousse to damp sides.",
                "Blow dry bangs downward using a flat comb to avoid puffiness.",
                "Create wide horizontal waves through the sides with a 1.5-inch curling iron.",
                "Scrunch with Sea Salt Spray and finish with Argan Oil on ends."
            ),
            barberInstructions = "Cut full eyebrow-skimming bangs straight across with soft edge beveling. Layer the sides to maximize horizontal width and volume.",
            recommendedProductIds = listOf("prod_vol_mousse", "prod_sea_salt", "prod_argan_oil"),
            tags = listOf("Eyebrow Bangs", "Face Shortening", "Horizontal Waves", "Statement"),
            isTrending = true
        ),

        Hairstyle(
            id = "style_oblong_w2",
            name = "Voluminous Chin-Length French Bob",
            category = "French Bob",
            suitableFaceShapes = listOf(FaceShape.OBLONG, FaceShape.HEART),
            targetGender = "Women",
            hairLength = "Short",
            suitableTextures = listOf("Straight", "Wavy", "Curly", "Fine", "Medium"),
            difficulty = "Easy (5 min)",
            description = "A jawline/lip-length chic French bob packed with horizontal body and cheek-grazing fullness.",
            whyItBalancesFace = "Brings the visual focal point up to the lips and cheekbones, dramatically reducing perceived face length.",
            stylingSteps = listOf(
                "Prep damp hair with Volumizing Mousse and Leave-In Spray.",
                "Rough dry using hands to amplify natural texture and volume.",
                "Apply Texturizing Paste to ends to create effortless, piecey movement."
            ),
            barberInstructions = "Classic French bob cut right at jawline level with subtle internal layers for natural round bounce and fullness.",
            recommendedProductIds = listOf("prod_vol_mousse", "prod_texture_paste", "prod_leave_in"),
            tags = listOf("French Bob", "Lip Length", "Volume", "Iconic")
        )
    )

    fun getHairstylesForFaceShape(faceShape: FaceShape): List<Hairstyle> {
        return hairstyles.filter { it.suitableFaceShapes.contains(faceShape) }
    }

    fun getHairstyleById(id: String): Hairstyle? {
        return hairstyles.find { it.id == id }
    }

    fun filterHairstyles(
        faceShape: FaceShape? = null,
        gender: String? = null,
        length: String? = null,
        texture: String? = null,
        searchQuery: String = ""
    ): List<Hairstyle> {
        return hairstyles.filter { style ->
            val matchesShape = faceShape == null || style.suitableFaceShapes.contains(faceShape)
            val matchesGender = gender == null || gender == "All" || style.targetGender.equals(gender, ignoreCase = true) || style.targetGender.equals("Unisex", ignoreCase = true)
            val matchesLength = length == null || length == "All" || style.hairLength.equals(length, ignoreCase = true)
            val matchesTexture = texture == null || texture == "All" || style.suitableTextures.any { it.equals(texture, ignoreCase = true) }
            val matchesSearch = searchQuery.isBlank() ||
                    style.name.contains(searchQuery, ignoreCase = true) ||
                    style.category.contains(searchQuery, ignoreCase = true) ||
                    style.tags.any { it.contains(searchQuery, ignoreCase = true) }

            matchesShape && matchesGender && matchesLength && matchesTexture && matchesSearch
        }
    }
}
