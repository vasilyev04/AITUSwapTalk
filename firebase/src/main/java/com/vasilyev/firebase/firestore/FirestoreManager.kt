package com.vasilyev.firebase.firestore

import com.vasilyev.firebase.FirebaseEntity

interface FirestoreManager {
    suspend fun <T: FirebaseEntity> getListFromCollection(
        collectionName: String,
        mapToClass: Class<T>
    ): Result<List<T>>

    suspend fun <T : FirebaseEntity> getFromCollection(
        collectionName: String,
        documentId: String,
        mapToClass: Class<T>
    ): Result<T>

    fun <T : FirebaseEntity> addToCollection(
        collectionName: String,
        obj: T
    ): Result<String>

    fun removeFromCollection(
        collectionName: String,
        documentName: String
    ): Result<Unit>
}