package com.vasilyev.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManagerImpl(
    private val firestore: FirebaseFirestore
): FirestoreManager {

    override suspend fun <T : FirestoreDocument> getListFromCollection(
        collectionName: String,
        mapToClass: Class<T>
    ): Result<List<T>> = runCatching {
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

    override fun <T : FirestoreDocument> addToCollection(
        collectionName: String,
        obj: T
    ): Result<String> = runCatching {
        val docRef = firestore
            .collection(collectionName)
            .document()
            .also { ref ->
                obj.id = ref.id
                ref.set(obj)
            }
        Result.success(docRef.id)
    }.getOrElse { error ->
        Result.failure(error)
    }

    override suspend fun <T : FirestoreDocument> getFromCollection(
        collectionName: String,
        documentId: String,
        mapToClass: Class<T>
    ): Result<T> = runCatching {
        val obj = firestore
            .collection(collectionName)
            .document(documentId)
            .get()
            .await()
            .toObject(mapToClass)
             ?: throw RuntimeException("Document not found")

        Result.success(obj)
    }.getOrElse { error ->
        Result.failure(error)
    }

    override fun removeFromCollection(
        collectionName: String,
        documentName: String
    ): Result<Unit> = runCatching{
        firestore
            .collection(collectionName)
            .document(documentName)
            .delete()

        Result.success(Unit)
    }.getOrElse { error ->
        Result.failure(error)
    }
}




