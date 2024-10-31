package com.vasilyev.presentation

import androidx.compose.runtime.Stable
import com.vasilyev.core.utils.Permission

@Stable
data class CallState(
    val onlineUsersCount: Int = 0,
    val visiblePermissionDialogQueue: List<Permission> = emptyList()
)