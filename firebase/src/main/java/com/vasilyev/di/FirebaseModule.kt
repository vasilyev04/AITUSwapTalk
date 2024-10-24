package com.vasilyev.di

import com.google.firebase.firestore.FirebaseFirestore
import com.vasilyev.firebase.FirebaseManager
import com.vasilyev.firebase.FirebaseManagerImpl
import org.koin.dsl.module

val firebaseModule = module {
    single<FirebaseFirestore> {
        FirebaseFirestore.getInstance()
    }

    single<FirebaseManager>{
        FirebaseManagerImpl(firestore = get())
    }
}