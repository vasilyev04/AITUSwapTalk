package com.vasilyev.di

import com.vasilyev.data.mapper.CallMapper
import com.vasilyev.data.repository.CallRepositoryImpl
import com.vasilyev.domain.repository.CallRepository
import com.vasilyev.domain.usecase.ConnectUserUseCase
import com.vasilyev.domain.usecase.GetOnlineUsersUseCase
import com.vasilyev.presentation.CallViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val callModule = module {
    factory<GetOnlineUsersUseCase> { GetOnlineUsersUseCase(get()) }
    factory<ConnectUserUseCase> { ConnectUserUseCase(get()) }

    single<CallRepository> {
        CallRepositoryImpl(
            firestoreManager = get(),
            callMapper = get(),
            realtimeDBManagerImpl = get()
        )
    }

    single<CallMapper> { CallMapper() }

    viewModel<CallViewModel> { CallViewModel(get(), get()) }
}