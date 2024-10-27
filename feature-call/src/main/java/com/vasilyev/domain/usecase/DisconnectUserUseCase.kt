package com.vasilyev.domain.usecase

import com.vasilyev.domain.model.User
import com.vasilyev.domain.repository.CallRepository

class DisconnectUserUseCase(
    private val callRepository: CallRepository
) {
    suspend operator fun invoke(
        user: User
    ) = callRepository.disconnectUser(user)
}