package com.vasilyev.firebase

interface FirestoreManager {
    suspend fun <T: FirestoreDocument> getListFromCollection(
        collectionName: String,
        mapToClass: Class<T>
    ): Result<List<T>>

    suspend fun <T : FirestoreDocument> getFromCollection(
        collectionName: String,
        documentId: String,
        mapToClass: Class<T>
    ): Result<T>

    fun <T : FirestoreDocument> addToCollection(
        collectionName: String,
        obj: T
    ): Result<String>

    fun removeFromCollection(
        collectionName: String,
        documentName: String
    ): Result<Unit>
}