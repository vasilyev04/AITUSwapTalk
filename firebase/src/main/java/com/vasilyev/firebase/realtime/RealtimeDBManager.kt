package com.vasilyev.firebase.realtime

import com.vasilyev.firebase.FirebaseEntity
import kotlinx.coroutines.flow.Flow

interface RealtimeDBManager {
    suspend fun <T : FirebaseEntity> getEntryList(
        reference: String,
        mapToClass: Class<T>
    ): Result<List<T>>

    fun <T : FirebaseEntity> getFlowEntryList(
        reference: String,
        mapToClass: Class<T>
    ): Flow<List<T>>
}