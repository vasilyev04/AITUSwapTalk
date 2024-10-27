package com.vasilyev.domain.usecase

import com.vasilyev.domain.repository.CallRepository

class GetOnlineUsersUseCase(
    private val callRepository: CallRepository
) {
    suspend operator fun invoke() = callRepository.getOnlineUsers()
}