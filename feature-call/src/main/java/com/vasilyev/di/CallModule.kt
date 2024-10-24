package com.vasilyev.di

import com.vasilyev.data.repository.CallRepositoryImpl
import com.vasilyev.domain.CallRepository
import com.vasilyev.presentation.CallViewModel
import org.koin.dsl.module

val callModule = module {
    single<CallRepository> { CallRepositoryImpl(firebaseManager = get()) }

    single<CallViewModel> { CallViewModel(callRepository = get()) }
}