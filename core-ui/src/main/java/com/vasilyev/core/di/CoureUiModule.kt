package com.vasilyev.core.di

import com.vasilyev.core.resource.AndroidResourceProvider
import com.vasilyev.core.resource.ResourceProvider
import org.koin.dsl.module

val coreUiModule = module {
    single<ResourceProvider> {
        AndroidResourceProvider(get())
    }
}