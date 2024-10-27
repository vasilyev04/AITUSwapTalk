package com.vasilyev.domain.usecase

import com.vasilyev.domain.repository.CallRepository

class ConnectUserUseCase(
    private val callRepository: CallRepository
) {
    suspend operator fun invoke(
        userName: String
    ) = callRepository.connectUserWithName(userName)
}