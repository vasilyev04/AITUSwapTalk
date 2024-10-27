package com.vasilyev.data.repository

import com.vasilyev.data.mapper.CallMapper
import com.vasilyev.data.model.UserDto
import com.vasilyev.domain.repository.CallRepository
import com.vasilyev.domain.model.User
import com.vasilyev.firebase.firestore.FirestoreManager
import com.vasilyev.firebase.realtime.RealtimeDBManagerImpl

internal class CallRepositoryImpl(
    private val firestoreManager: FirestoreManager,
    private val realtimeDBManagerImpl: RealtimeDBManagerImpl,
    private val callMapper: CallMapper
) : CallRepository {

    companion object {
        private const val ONLINE_USERS_COLLECTION = "online_users"
    }

    override suspend fun getOnlineUsers(): Result<List<User>> {
        val result = firestoreManager.getListFromCollection(
            collectionName = ONLINE_USERS_COLLECTION,
            mapToClass = UserDto::class.java
        )

        if (result.isSuccess) {
            val usersDto = result.getOrNull()
                ?: return Result.success(emptyList())

            val users = usersDto.map {
                callMapper.mapUserDtoToUser(it)
            }
            return Result.success(users)
        } else {
            return Result.failure(
                result.exceptionOrNull()
                    ?: RuntimeException("Unknown Exception")
            )
        }
    }

    override suspend fun connectUserWithName(userName: String): Result<User> {
        val userDto = UserDto(name = userName)

        val result = firestoreManager.addToCollection(
            collectionName = ONLINE_USERS_COLLECTION,
            obj = userDto
        )

        if (result.isSuccess) {
            val id = result.getOrNull()
                ?: return Result.failure(RuntimeException("User id is null"))

            return Result.success(
                callMapper.mapUserDtoToUser(
                    userDto.copy(id = id)
                )
            )
        } else {
            return Result.failure(
                result.exceptionOrNull()
                    ?: RuntimeException("Unknown Exception")
            )
        }
    }

    override suspend fun disconnectUser(user: User): Result<Unit> =
        firestoreManager.removeFromCollection(ONLINE_USERS_COLLECTION, user.id)
}