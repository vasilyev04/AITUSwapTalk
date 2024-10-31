package com.vasilyev.presentation

import com.vasilyev.core.utils.Permission


sealed interface CallIntent {
    data object OnStartCall: CallIntent

    data object OnDismissPermissionDialog: CallIntent

    data class OnPermissionResult(
        val permission: Permission,
        val isGranted: Boolean
    ): CallIntent
}