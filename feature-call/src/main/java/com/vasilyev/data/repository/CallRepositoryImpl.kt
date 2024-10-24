package com.vasilyev.data.repository

import com.vasilyev.domain.CallRepository
import com.vasilyev.domain.User
import com.vasilyev.firebase.FirebaseManager

class CallRepositoryImpl(
    private val firebaseManager: FirebaseManager
): CallRepository {

    companion object{
        private const val ONLINE_USERS_COLLECTION = "online_users"
    }

    override suspend fun getOnlineUserNames(): Result<List<User>> =
        firebaseManager.getListFromCollection(
            ONLINE_USERS_COLLECTION,
            User::class.java
        )

    override suspend fun addUserName(name: String): Result<Unit> =
        firebaseManager.addToCollection(ONLINE_USERS_COLLECTION, User(name))
}