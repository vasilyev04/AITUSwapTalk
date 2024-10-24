package com.vasilyev.domain

interface CallRepository {
    suspend fun getOnlineUserNames(): Result<List<User>>

    suspend fun addUserName(name: String): Result<Unit>
}