package com.vasilyev.core.permissions

import com.vasilyev.core.resource.ResourceProvider
import com.vasilyev.core.ui.R
import org.koin.java.KoinJavaComponent.inject

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}

class NotificationPermissionTextProvider: PermissionTextProvider {
    private val recourseProvider: ResourceProvider by inject(ResourceProvider::class.java)

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined) {
            recourseProvider.getString(R.string.description_notification_declined)
        } else {
            recourseProvider.getString(R.string.description_notification_rationale)
        }
    }
}

class RecordAudioPermissionTextProvider: PermissionTextProvider {
    private val recourseProvider: ResourceProvider by inject(ResourceProvider::class.java)

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        return if(isPermanentlyDeclined) {
            recourseProvider.getString(R.string.description_audio_declined)
        } else {
            recourseProvider.getString(R.string.description_audio_rationale)
        }
    }
}