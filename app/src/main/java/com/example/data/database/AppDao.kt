package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    // === Hairstyles ===
    @Query("SELECT * FROM saved_hairstyles ORDER BY savedAt DESC")
    fun getSavedHairstyles(): Flow<List<SavedHairstyleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHairstyle(hairstyle: SavedHairstyleEntity)

    @Query("DELETE FROM saved_hairstyles WHERE hairstyleId = :hairstyleId")
    suspend fun removeHairstyle(hairstyleId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_hairstyles WHERE hairstyleId = :hairstyleId)")
    fun isHairstyleSaved(hairstyleId: String): Flow<Boolean>

    // === Products & Routine ===
    @Query("SELECT * FROM saved_products ORDER BY routineOrder ASC, savedAt DESC")
    fun getSavedProducts(): Flow<List<SavedProductEntity>>

    @Query("SELECT * FROM saved_products WHERE isInRoutine = 1 ORDER BY routineOrder ASC")
    fun getRoutineProducts(): Flow<List<SavedProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: SavedProductEntity)

    @Query("DELETE FROM saved_products WHERE productId = :productId")
    suspend fun removeProduct(productId: String)

    @Query("UPDATE saved_products SET isInRoutine = :inRoutine WHERE productId = :productId")
    suspend fun updateRoutineStatus(productId: String, inRoutine: Boolean)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_products WHERE productId = :productId)")
    fun isProductSaved(productId: String): Flow<Boolean>

    // === Scan History ===
    @Query("SELECT * FROM scan_history ORDER BY scanDate DESC")
    fun getScanHistory(): Flow<List<ScanHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScanHistory(scan: ScanHistoryEntity): Long

    @Query("DELETE FROM scan_history WHERE id = :id")
    suspend fun deleteScanHistory(id: Long)

    @Query("DELETE FROM scan_history")
    suspend fun clearScanHistory()

    // === User Profile ===
    @Query("SELECT * FROM user_profile WHERE id = 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)
}
