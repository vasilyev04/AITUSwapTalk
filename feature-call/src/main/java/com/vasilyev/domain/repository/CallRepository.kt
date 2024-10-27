package com.vasilyev.domain.repository

import com.vasilyev.domain.model.User

interface CallRepository {
    suspend fun getOnlineUsers(): Result<List<User>>

    suspend fun connectUserWithName(userName: String): Result<User>

    suspend fun disconnectUser(user: User): Result<Unit>
}