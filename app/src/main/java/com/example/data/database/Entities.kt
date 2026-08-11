package com.example.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_hairstyles")
data class SavedHairstyleEntity(
    @PrimaryKey val hairstyleId: String,
    val name: String,
    val category: String,
    val faceShapeName: String,
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "saved_products")
data class SavedProductEntity(
    @PrimaryKey val productId: String,
    val name: String,
    val categoryName: String,
    val isInRoutine: Boolean = false,
    val routineOrder: Int = 0,
    val savedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "scan_history")
data class ScanHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val faceShapeName: String,
    val confidence: Int,
    val lengthToWidthRatio: Float,
    val scanDate: Long = System.currentTimeMillis(),
    val notes: String = ""
)

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val selectedFaceShapeName: String = "OVAL",
    val preferredGender: String = "All",
    val hairTexture: String = "Wavy",
    val hairLength: String = "Medium",
    val userName: String = "Styling Aficionado"
)
