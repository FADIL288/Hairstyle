package com.example.data.database

import kotlinx.coroutines.flow.Flow

class AppRepository(private val dao: AppDao) {

    val savedHairstyles: Flow<List<SavedHairstyleEntity>> = dao.getSavedHairstyles()
    val savedProducts: Flow<List<SavedProductEntity>> = dao.getSavedProducts()
    val routineProducts: Flow<List<SavedProductEntity>> = dao.getRoutineProducts()
    val scanHistory: Flow<List<ScanHistoryEntity>> = dao.getScanHistory()
    val userProfile: Flow<UserProfileEntity?> = dao.getUserProfile()

    suspend fun toggleSaveHairstyle(entity: SavedHairstyleEntity, isCurrentlySaved: Boolean) {
        if (isCurrentlySaved) {
            dao.removeHairstyle(entity.hairstyleId)
        } else {
            dao.saveHairstyle(entity)
        }
    }

    fun isHairstyleSaved(id: String): Flow<Boolean> = dao.isHairstyleSaved(id)

    suspend fun toggleSaveProduct(entity: SavedProductEntity, isCurrentlySaved: Boolean) {
        if (isCurrentlySaved) {
            dao.removeProduct(entity.productId)
        } else {
            dao.saveProduct(entity)
        }
    }

    suspend fun toggleRoutineStatus(productId: String, inRoutine: Boolean) {
        dao.updateRoutineStatus(productId, inRoutine)
    }

    fun isProductSaved(id: String): Flow<Boolean> = dao.isProductSaved(id)

    suspend fun saveScan(scan: ScanHistoryEntity) {
        dao.insertScanHistory(scan)
    }

    suspend fun deleteScan(id: Long) {
        dao.deleteScanHistory(id)
    }

    suspend fun saveProfile(profile: UserProfileEntity) {
        dao.saveUserProfile(profile)
    }
}
