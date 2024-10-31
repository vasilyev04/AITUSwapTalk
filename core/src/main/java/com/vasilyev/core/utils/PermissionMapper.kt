package com.vasilyev.core.utils

import android.Manifest

fun Permission.mapToString(): String {
    return when (this) {
        Permission.RECORD_AUDIO -> Manifest.permission.RECORD_AUDIO
        Permission.POST_NOTIFICATIONS -> Manifest.permission.POST_NOTIFICATIONS
    }
}

fun String.mapToPermission(): Permission {
    return when (this) {
        Manifest.permission.RECORD_AUDIO -> Permission.RECORD_AUDIO
        Manifest.permission.POST_NOTIFICATIONS -> Permission.POST_NOTIFICATIONS
        else -> throw RuntimeException("Undefined permission")
    }
}