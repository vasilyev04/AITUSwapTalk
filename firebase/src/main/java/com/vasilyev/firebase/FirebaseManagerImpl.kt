package com.vasilyev.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirebaseManagerImpl(
    private val firestore: FirebaseFirestore
): FirebaseManager{

    override suspend fun <T> getListFromCollection(
        collectionName: String,
        mapToClass: Class<T>
    ): Result<List<T>>
        = runCatching {
            val list = firestore
                .collection(collectionName)
                .get()
                .await()
                .documents
                .mapNotNull { it.toObject(mapToClass) }
            Result.success(list)
        }.getOrElse { error ->
            Result.failure(error)
        }

    override fun addToCollection(
        collectionName: String, obj: Any
    ): Result<Unit> = runCatching {
        firestore
            .collection(collectionName)
            .add(obj)

        Result.success(Unit)
    }.getOrElse { error ->
        Result.failure(error)
    }
}




