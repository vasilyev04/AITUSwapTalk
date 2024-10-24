package com.vasilyev.firebase

interface FirebaseManager {
    suspend fun <T> getListFromCollection(
        collectionName: String,
        mapToClass: Class<T>
    ): Result<List<T>>

    fun addToCollection(
        collectionName: String,
        obj: Any
    ): Result<Unit>
}