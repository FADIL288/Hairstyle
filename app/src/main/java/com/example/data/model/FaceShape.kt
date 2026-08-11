package com.example.data.model

enum class FaceShape(
    val displayName: String,
    val shortDescription: String,
    val characteristics: List<String>,
    val flatteringGoals: String,
    val flatteringPrinciples: List<String>,
    val stylesToAvoid: List<String>,
    val idealProportions: String,
    val iconicExamples: String,
    val stylingTip: String
) {
    OVAL(
        displayName = "Oval",
        shortDescription = "Balanced proportions with gentle curves and slightly narrower jaw.",
        characteristics = listOf(
            "Face length is roughly 1.5x the width",
            "Forehead is slightly wider than the rounded jawline",
            "No sharp or prominent angles",
            "Cheekbones are the widest part of the face"
        ),
        flatteringGoals = "Maintain natural visual balance; nearly all hairstyles work well.",
        flatteringPrinciples = listOf(
            "Sleek classic side parts & scissor tapers",
            "Medium-length pompadours with low taper",
            "Collarbone blunt lobs with gentle beach waves",
            "Textured layered shag cuts"
        ),
        stylesToAvoid = listOf(
            "Heavy forward bangs that hide the forehead and shorten the face",
            "Excessive height on top that overly elongates the silhouette"
        ),
        idealProportions = "Length : Width ratio ~ 1.5 : 1. Forehead : Cheekbone : Jaw ratio ~ 1.1 : 1.2 : 0.9",
        iconicExamples = "George Clooney, Beyoncé, Jessica Alba, Chris Hemsworth",
        stylingTip = "Keep hair off the forehead to show off balanced symmetry. Medium texture with soft separation works best."
    ),

    ROUND(
        displayName = "Round",
        shortDescription = "Equal length and width with soft, circular cheekbones and rounded jaw.",
        characteristics = listOf(
            "Face width and length are approximately equal",
            "Soft curved cheekbones with no sharp angles",
            "Rounded chin and jawline",
            "Fuller cheeks creating a youthful appearance"
        ),
        flatteringGoals = "Create vertical height and diagonal angles to elongate the face silhouette.",
        flatteringPrinciples = listOf(
            "High skin fade with textured quiff for vertical height",
            "Angular French crop with choppy fringe",
            "Long layered cut with cheekbone-grazing curtain bangs",
            "Asymmetrical angled lob to create diagonal lines"
        ),
        stylesToAvoid = listOf(
            "Chin-length blunt bobs that accentuate roundness",
            "Wide voluminous curls at ear level",
            "Slicked-back styles with flat tops and wide sides"
        ),
        idealProportions = "Length : Width ratio ~ 1.0 : 1.0. Jaw width is close to cheekbone width.",
        iconicExamples = "Selena Gomez, Leonardo DiCaprio, Chrissy Teigen, Zac Efron",
        stylingTip = "Use matte clay or sea salt spray at the roots for vertical lift. Keep sides tight with fades or long layers."
    ),

    SQUARE(
        displayName = "Square",
        shortDescription = "Strong, angular jawline with equal width at forehead, cheekbones, and jaw.",
        characteristics = listOf(
            "Forehead, cheekbones, and jaw have almost identical width",
            "Sharp, well-defined 90-degree angular jawline",
            "Straight sides of the face",
            "Broad and flat chin structure"
        ),
        flatteringGoals = "Soften the angular jawline with texture, soft layers, or side parts.",
        flatteringPrinciples = listOf(
            "Textured French crop with soft taper fade",
            "Soft layered beach waves cascading past shoulders",
            "Modern wolf cut with feathered crown layers",
            "Messy mid-length scissor side sweeps"
        ),
        stylesToAvoid = listOf(
            "Boxy blunt cuts that emphasize square corners",
            "Severe center parts with flat straight hair",
            "Hard straight-across geometric bangs"
        ),
        idealProportions = "Forehead : Cheekbone : Jaw ratio ~ 1.0 : 1.0 : 1.0. Strong jaw angle.",
        iconicExamples = "Brad Pitt, Angelina Jolie, Henry Cavill, Olivia Wilde",
        stylingTip = "Opt for textured crops, soft side-sweeps, or wispy layers. Use styling paste to create touchable movement."
    ),

    HEART(
        displayName = "Heart / Inverted Triangle",
        shortDescription = "Broad forehead and high cheekbones tapering down to a pointed chin.",
        characteristics = listOf(
            "Forehead is the widest part of the face",
            "High, prominent cheekbones",
            "Narrow, tapered jawline ending in a pointed chin",
            "May feature a widow's peak hairline"
        ),
        flatteringGoals = "Add width and fullness around the jawline to balance the broader upper face.",
        flatteringPrinciples = listOf(
            "Mid-length scissor flow cut tucked behind ears",
            "Chin-length textured bob with wispy bangs",
            "Voluminous butterfly layered cut",
            "Side-swept curtain bangs with shaggy ends"
        ),
        stylesToAvoid = listOf(
            "Excessive top volume that widens the forehead",
            "High slicked ponytails with bare sides",
            "Severe taper fades with ultra-short sides"
        ),
        idealProportions = "Forehead : Cheekbone : Jaw ratio ~ 1.3 : 1.1 : 0.7. Pointed chin angle.",
        iconicExamples = "Reese Witherspoon, Ryan Gosling, Scarlett Johansson, Timothée Chalamet",
        stylingTip = "Mid-length bobs, textured side fringes, and flow cuts fill in the narrower lower half. Use curl creams or light pastes."
    ),

    DIAMOND(
        displayName = "Diamond",
        shortDescription = "Wide high cheekbones with narrow forehead and delicate pointed jaw.",
        characteristics = listOf(
            "Cheekbones are noticeably the widest feature",
            "Narrow forehead and hairline",
            "Narrow, tapered chin",
            "Sculpted, high facial bone structure"
        ),
        flatteringGoals = "Add fullness at the forehead and chin while softening cheekbone width.",
        flatteringPrinciples = listOf(
            "Textured brush-up with scissor taper",
            "Shoulder-length lob with flicked ends & side bangs",
            "Side-swept voluminous soft curls",
            "Messy medium-length layered flow"
        ),
        stylesToAvoid = listOf(
            "Severe slicked-back styles that expose temples",
            "Middle parts that leave flat hair directly over cheekbones",
            "Very short crops with bare sides"
        ),
        idealProportions = "Forehead : Cheekbone : Jaw ratio ~ 0.8 : 1.3 : 0.8. Diamond contour.",
        iconicExamples = "Johnny Depp, Halle Berry, Robert Pattinson, Jennifer Lopez",
        stylingTip = "Fringes, side-swept bangs, and shoulder-length wavy cuts add width at the top and bottom. Use sea salt texturizers."
    ),

    OBLONG(
        displayName = "Oblong / Rectangular",
        shortDescription = "Face is noticeably longer than wide with straight sides and long jawline.",
        characteristics = listOf(
            "Face length is significantly greater than width",
            "Straight cheek lines from temple to jaw",
            "Tall forehead and extended chin line",
            "Uniform width across forehead and jaw"
        ),
        flatteringGoals = "Add width and horizontal volume to make the face appear shorter and balanced.",
        flatteringPrinciples = listOf(
            "Classic scissor crew cut with fuller sides",
            "Eyebrow-skimming bangs with horizontal waves",
            "Voluminous chin-length French bob",
            "Medium curly shag with wide lateral body"
        ),
        stylesToAvoid = listOf(
            "Towering pompadours or high quiffs that add vertical length",
            "Skin fades with long hair on top",
            "Center parted ultra-long flat hair"
        ),
        idealProportions = "Length : Width ratio ~ 1.8 : 1.0. Extended vertical dimension.",
        iconicExamples = "Adam Levine, Sarah Jessica Parker, Ben Affleck, Liv Tyler",
        stylingTip = "Incorporate eyebrow-skimming bangs, side sweeps, or horizontal wave volume. Use volumizing mousse on the sides."
    );

    companion object {
        fun fromName(name: String?): FaceShape {
            return entries.find { it.name.equals(name, ignoreCase = true) } ?: OVAL
        }
    }
}
