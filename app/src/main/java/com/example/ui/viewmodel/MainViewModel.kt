package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.database.AppDatabase
import com.example.data.database.AppRepository
import com.example.data.database.SavedHairstyleEntity
import com.example.data.database.SavedProductEntity
import com.example.data.database.ScanHistoryEntity
import com.example.data.database.UserProfileEntity
import com.example.data.local.FaceShapeAnalyzer
import com.example.data.local.HairstyleCatalog
import com.example.data.local.ProductCatalog
import com.example.data.model.FaceAnalysisResult
import com.example.data.model.FaceShape
import com.example.data.model.Hairstyle
import com.example.data.model.ProductCategory
import com.example.data.model.StylingProduct
import com.example.data.network.GeminiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppNavTab(val title: String) {
    HOME("Home"),
    DETECTOR("Face Scan"),
    HAIRSTYLES("Hairstyles"),
    PRODUCTS("Products"),
    ROUTINE("Routine"),
    AI_CONSULTANT("AI Stylist"),
    FACE_GUIDE("Face Shapes")
}

data class ChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val sender: String, // "User" or "AI Stylist"
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AppRepository

    init {
        val db = AppDatabase.getInstance(application)
        repository = AppRepository(db.appDao())
    }

    // Active Navigation Tab
    private val _currentTab = MutableStateFlow(AppNavTab.HOME)
    val currentTab: StateFlow<AppNavTab> = _currentTab.asStateFlow()

    // Selected Active Face Shape
    private val _selectedFaceShape = MutableStateFlow(FaceShape.OVAL)
    val selectedFaceShape: StateFlow<FaceShape> = _selectedFaceShape.asStateFlow()

    // Active Filter States
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _genderFilter = MutableStateFlow("All")
    val genderFilter: StateFlow<String> = _genderFilter.asStateFlow()

    private val _lengthFilter = MutableStateFlow("All")
    val lengthFilter: StateFlow<String> = _lengthFilter.asStateFlow()

    private val _textureFilter = MutableStateFlow("All")
    val textureFilter: StateFlow<String> = _textureFilter.asStateFlow()

    private val _productCategoryFilter = MutableStateFlow<ProductCategory?>(null)
    val productCategoryFilter: StateFlow<ProductCategory?> = _productCategoryFilter.asStateFlow()

    // Analysis Result
    private val _latestAnalysis = MutableStateFlow<FaceAnalysisResult?>(null)
    val latestAnalysis: StateFlow<FaceAnalysisResult?> = _latestAnalysis.asStateFlow()

    // Detailed Item Selection
    private val _selectedHairstyle = MutableStateFlow<Hairstyle?>(null)
    val selectedHairstyle: StateFlow<Hairstyle?> = _selectedHairstyle.asStateFlow()

    private val _selectedProduct = MutableStateFlow<StylingProduct?>(null)
    val selectedProduct: StateFlow<StylingProduct?> = _selectedProduct.asStateFlow()

    // AI Chat State
    private val _chatMessages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                sender = "AI Stylist",
                message = "Welcome to your bespoke Salon & Face Shape Studio! I'm your AI Master Stylist. Select your face shape or scan your selfie, and ask me anything about cuts, fading, layering, and styling products!"
            )
        )
    )
    val chatMessages: StateFlow<List<ChatMessage>> = _chatMessages.asStateFlow()

    private val _isAiGenerating = MutableStateFlow(false)
    val isAiGenerating: StateFlow<Boolean> = _isAiGenerating.asStateFlow()

    // Room Database Observables
    val savedHairstyles: StateFlow<List<SavedHairstyleEntity>> = repository.savedHairstyles
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val savedProducts: StateFlow<List<SavedProductEntity>> = repository.savedProducts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val routineProducts: StateFlow<List<SavedProductEntity>> = repository.routineProducts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val scanHistory: StateFlow<List<ScanHistoryEntity>> = repository.scanHistory
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val userProfile: StateFlow<UserProfileEntity?> = repository.userProfile
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    // Quiz Diagnostic State
    private val _quizAnswers = MutableStateFlow(FaceShapeAnalyzer.QuizAnswers())
    val quizAnswers: StateFlow<FaceShapeAnalyzer.QuizAnswers> = _quizAnswers.asStateFlow()

    fun setTab(tab: AppNavTab) {
        _currentTab.value = tab
    }

    fun selectFaceShape(faceShape: FaceShape) {
        _selectedFaceShape.value = faceShape
        viewModelScope.launch {
            repository.saveProfile(
                UserProfileEntity(
                    selectedFaceShapeName = faceShape.name,
                    preferredGender = _genderFilter.value,
                    hairTexture = _textureFilter.value,
                    hairLength = _lengthFilter.value
                )
            )
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setGenderFilter(gender: String) {
        _genderFilter.value = gender
    }

    fun setLengthFilter(length: String) {
        _lengthFilter.value = length
    }

    fun setTextureFilter(texture: String) {
        _textureFilter.value = texture
    }

    fun setProductCategoryFilter(category: ProductCategory?) {
        _productCategoryFilter.value = category
    }

    fun selectHairstyleForDetails(hairstyle: Hairstyle?) {
        _selectedHairstyle.value = hairstyle
    }

    fun selectProductForDetails(product: StylingProduct?) {
        _selectedProduct.value = product
    }

    fun updateQuizAnswers(answers: FaceShapeAnalyzer.QuizAnswers) {
        _quizAnswers.value = answers
    }

    fun submitQuizAnalysis() {
        val result = FaceShapeAnalyzer.analyzeQuiz(_quizAnswers.value)
        _latestAnalysis.value = result
        _selectedFaceShape.value = result.detectedShape

        viewModelScope.launch {
            repository.saveScan(
                ScanHistoryEntity(
                    faceShapeName = result.detectedShape.name,
                    confidence = result.confidence,
                    lengthToWidthRatio = result.lengthToWidthRatio,
                    notes = "Diagnostic Assessment Quiz"
                )
            )
            repository.saveProfile(
                UserProfileEntity(
                    selectedFaceShapeName = result.detectedShape.name,
                    preferredGender = _genderFilter.value,
                    hairTexture = _textureFilter.value,
                    hairLength = _lengthFilter.value
                )
            )
        }
    }

    fun submitMeasurementAnalysis(
        faceLength: Float,
        cheekboneWidth: Float,
        foreheadWidth: Float,
        jawlineLength: Float
    ) {
        val result = FaceShapeAnalyzer.analyzeMeasurements(
            faceLength, cheekboneWidth, foreheadWidth, jawlineLength
        )
        _latestAnalysis.value = result
        _selectedFaceShape.value = result.detectedShape

        viewModelScope.launch {
            repository.saveScan(
                ScanHistoryEntity(
                    faceShapeName = result.detectedShape.name,
                    confidence = result.confidence,
                    lengthToWidthRatio = result.lengthToWidthRatio,
                    notes = "Camera Facial Landmark Analysis"
                )
            )
            repository.saveProfile(
                UserProfileEntity(
                    selectedFaceShapeName = result.detectedShape.name,
                    preferredGender = _genderFilter.value,
                    hairTexture = _textureFilter.value,
                    hairLength = _lengthFilter.value
                )
            )
        }
    }

    fun toggleSaveHairstyle(hairstyle: Hairstyle) {
        viewModelScope.launch {
            val isCurrentlySaved = savedHairstyles.value.any { it.hairstyleId == hairstyle.id }
            repository.toggleSaveHairstyle(
                SavedHairstyleEntity(
                    hairstyleId = hairstyle.id,
                    name = hairstyle.name,
                    category = hairstyle.category,
                    faceShapeName = _selectedFaceShape.value.name
                ),
                isCurrentlySaved
            )
        }
    }

    fun toggleSaveProduct(product: StylingProduct) {
        viewModelScope.launch {
            val isCurrentlySaved = savedProducts.value.any { it.productId == product.id }
            repository.toggleSaveProduct(
                SavedProductEntity(
                    productId = product.id,
                    name = product.name,
                    categoryName = product.category.displayName,
                    isInRoutine = false
                ),
                isCurrentlySaved
            )
        }
    }

    fun toggleProductInRoutine(product: StylingProduct) {
        viewModelScope.launch {
            val existing = savedProducts.value.find { it.productId == product.id }
            val inRoutine = existing?.isInRoutine == true
            if (existing == null) {
                repository.toggleSaveProduct(
                    SavedProductEntity(
                        productId = product.id,
                        name = product.name,
                        categoryName = product.category.displayName,
                        isInRoutine = true
                    ),
                    isCurrentlySaved = false
                )
            } else {
                repository.toggleRoutineStatus(product.id, !inRoutine)
            }
        }
    }

    fun deleteScanHistory(id: Long) {
        viewModelScope.launch {
            repository.deleteScan(id)
        }
    }

    fun sendAiStylistMessage(userMessageText: String) {
        if (userMessageText.isBlank()) return

        val userMsg = ChatMessage(sender = "User", message = userMessageText)
        _chatMessages.value = _chatMessages.value + userMsg
        _isAiGenerating.value = true

        viewModelScope.launch {
            val reply = GeminiClient.consultStylist(
                userQuery = userMessageText,
                faceShape = _selectedFaceShape.value,
                hairTexture = _textureFilter.value,
                genderPreference = _genderFilter.value
            )
            _chatMessages.value = _chatMessages.value + ChatMessage(sender = "AI Stylist", message = reply)
            _isAiGenerating.value = false
        }
    }
}
