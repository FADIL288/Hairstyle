package com.example.data.network

import com.example.BuildConfig
import com.example.data.model.FaceShape
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GeminiApi {
    @POST("v1beta/models/gemini-3.5-flash:generateContent")
    suspend fun generateContent(
        @Query("key") apiKey: String,
        @Body requestBody: okhttp3.RequestBody
    ): okhttp3.ResponseBody
}

object GeminiClient {
    private const val BASE_URL = "https://generativelanguage.googleapis.com/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private val api: GeminiApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(GeminiApi::class.java)
    }

    suspend fun consultStylist(
        userQuery: String,
        faceShape: FaceShape?,
        hairTexture: String = "Wavy",
        genderPreference: String = "All"
    ): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        if (apiKey.isBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext getOfflineStylistResponse(userQuery, faceShape, hairTexture, genderPreference)
        }

        try {
            val systemPrompt = "You are a world-class master hair stylist, master barber, and facial geometry aesthetician. " +
                    "Give clear, concise, actionable, and encouraging hairstyle and styling product advice. " +
                    "Current user context: Face Shape = ${faceShape?.displayName ?: "Unknown"}, Hair Texture = $hairTexture, Preference = $genderPreference."

            val jsonBody = JSONObject().apply {
                val contentsArray = JSONArray()
                val contentObj = JSONObject().apply {
                    val partsArray = JSONArray()
                    partsArray.put(JSONObject().put("text", "$systemPrompt\n\nUser Question: $userQuery"))
                    put("parts", partsArray)
                }
                contentsArray.put(contentObj)
                put("contents", contentsArray)
            }

            val requestBody = jsonBody.toString().toRequestBody("application/json".toMediaType())
            val responseBody = api.generateContent(apiKey, requestBody)
            val jsonResponse = JSONObject(responseBody.string())

            val candidates = jsonResponse.optJSONArray("candidates")
            val firstCandidate = candidates?.optJSONObject(0)
            val content = firstCandidate?.optJSONObject("content")
            val parts = content?.optJSONArray("parts")
            val text = parts?.optJSONObject(0)?.optString("text")

            if (!text.isNullOrBlank()) {
                text
            } else {
                getOfflineStylistResponse(userQuery, faceShape, hairTexture, genderPreference)
            }
        } catch (e: Exception) {
            getOfflineStylistResponse(userQuery, faceShape, hairTexture, genderPreference)
        }
    }

    private fun getOfflineStylistResponse(
        query: String,
        faceShape: FaceShape?,
        texture: String,
        gender: String
    ): String {
        val q = query.lowercase()
        val shape = faceShape ?: FaceShape.OVAL

        return when {
            q.contains("product") || q.contains("clay") || q.contains("spray") || q.contains("pomade") -> {
                "For an ${shape.displayName} face shape with $texture hair, the ideal product regimen is:\n\n" +
                        "1. **Pre-Styler**: Use a lightweight *Sea Salt Spray* or *Volumizing Mousse* on towel-damp hair before blow drying. This gives 2x root lift and texture foundation.\n" +
                        "2. **Main Styler**: For matte, textured hold, warm a dime-sized amount of *High-Hold Matte Clay* between your palms and work backwards through dry hair. For clean, polished parts, choose a *Water-Soluble Pomade*.\n" +
                        "3. **Finishing Touch**: Lightly dust *Texture Powder* onto roots for instant reworkable volume without greasy build-up."
            }
            q.contains("barber") || q.contains("ask") || q.contains("tell") || q.contains("cut") -> {
                "Here is what to tell your barber/stylist for an ${shape.displayName} face:\n\n" +
                        "• **The Sides**: ${if (shape == FaceShape.ROUND || shape == FaceShape.SQUARE) "Ask for a clean taper or mid-to-high fade (starting with #1 or skin) to keep the sides slim." else "Ask for a softer scissor-taper (#3 or scissor-over-comb) to maintain natural side fullness."}\n" +
                        "• **The Top**: Request 3 to 4 inches with point-cutting and texturizing for piecey movement rather than a blunt shelf.\n" +
                        "• **Styling Goal**: Mention that you want to ${shape.flatteringGoals.lowercase()}."
            }
            q.contains("round") || shape == FaceShape.ROUND -> {
                "For a **Round Face Shape**:\n\n" +
                        "• **Best Hairstyles**: High Skin Fade Quiff, Textured Angular Crop, Long Layers with Curtain Bangs, or Asymmetrical Angled Bob.\n" +
                        "• **The Key Strategy**: Add vertical height at the crown and keep the sides close to the head to elongate your facial silhouette.\n" +
                        "• **Pro Tip**: Use a blow dryer with a round brush lifting the roots upward, then lock with high-hold matte clay."
            }
            q.contains("square") || shape == FaceShape.SQUARE -> {
                "For a **Square Face Shape**:\n\n" +
                        "• **Best Hairstyles**: Textured French Crop with soft fade, Soft Wavy Layers, Modern Wolf Cut, or Messy Side Sweep.\n" +
                        "• **The Key Strategy**: Soften the sharp 90-degree jawline angles with textured, wispy, or wavy movement.\n" +
                        "• **Pro Tip**: Avoid severe blunt boxes or ultra-slick center parts; opt for flexible styling paste."
            }
            q.contains("heart") || shape == FaceShape.HEART -> {
                "For a **Heart Face Shape**:\n\n" +
                        "• **Best Hairstyles**: Scissor Flow Cut, Chin-Length Textured Bob, Wispy Curtain Bangs, or Butterfly Layers.\n" +
                        "• **The Key Strategy**: Balance the wider forehead by creating fullness and width around the jawline and chin."
            }
            q.contains("diamond") || shape == FaceShape.DIAMOND -> {
                "For a **Diamond Face Shape**:\n\n" +
                        "• **Best Hairstyles**: Textured Brush-Up, Medium Scissor Shag, Shoulder Lob with Flicked Ends, or Side-Swept Soft Curls.\n" +
                        "• **The Key Strategy**: Retain fullness around the temples and jawline while softening prominent cheekbone width."
            }
            q.contains("oblong") || shape == FaceShape.OBLONG -> {
                "For an **Oblong Face Shape**:\n\n" +
                        "• **Best Hairstyles**: Classic Scissor Crew Cut, Eyebrow-Skimming Bangs with Horizontal Waves, or Voluminous French Bob.\n" +
                        "• **The Key Strategy**: Add width to the sides and use horizontal bangs to visually shorten face length. Avoid towering quiffs."
            }
            else -> {
                "For an **${shape.displayName} face shape** with **$texture hair**:\n\n" +
                        "• **Balancing Rule**: ${shape.flatteringGoals}\n" +
                        "• **Styles to Avoid**: ${shape.stylesToAvoid.joinToString(", ")}.\n" +
                        "• **Daily Routine**: Apply Sea Salt Spray on damp roots, blow dry in the desired direction, and lock the shape with a dime of Matte Clay or Curl Cream."
            }
        }
    }
}
