package com.vasilyev.firebase.realtime

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.snapshots
import com.vasilyev.firebase.FirebaseEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class RealtimeDBManagerImpl(
    private val realtimeDatabase: FirebaseDatabase
): RealtimeDBManager {

    override suspend fun <T : FirebaseEntity> getEntryList(
        reference: String,
        mapToClass: Class<T>
    ): Result<List<T>> = runCatching {
        val list = realtimeDatabase
            .getReference(reference)
            .get()
            .await()
            .children
            .mapNotNull { it.getValue(mapToClass) }

        Result.success(list)
    }.getOrElse { error ->
        Result.failure(error)
    }

    override fun <T : FirebaseEntity> getFlowEntryList(
        reference: String,
        mapToClass: Class<T>
    ): Flow<List<T>> = realtimeDatabase
        .getReference(reference)
        .snapshots
        .map { snapshot ->
            snapshot
                .children
                .mapNotNull { it.getValue(mapToClass) }
        }
}