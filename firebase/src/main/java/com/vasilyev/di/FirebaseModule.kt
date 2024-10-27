package com.vasilyev.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.vasilyev.firebase.firestore.FirestoreManager
import com.vasilyev.firebase.firestore.FirestoreManagerImpl
import com.vasilyev.firebase.realtime.RealtimeDBManagerImpl
import org.koin.dsl.module

val firebaseModule = module {
    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }

    single<FirebaseDatabase> { FirebaseDatabase.getInstance() }

    single<FirestoreManager>{
        FirestoreManagerImpl(firestore = get())
    }

    single<RealtimeDBManagerImpl> { RealtimeDBManagerImpl(get()) }
}