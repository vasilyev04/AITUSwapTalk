package com.vasilyev.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vasilyev.core.utils.Permission
import com.vasilyev.domain.usecase.ConnectUserUseCase
import com.vasilyev.domain.usecase.DisconnectUserUseCase
import com.vasilyev.domain.usecase.GetOnlineUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CallViewModel(
    private val connectUserUseCase: ConnectUserUseCase,
    private val disconnectUserUseCase: DisconnectUserUseCase,
    private val getOnlineUsersUseCase: GetOnlineUsersUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CallState())
    val state = _state.asStateFlow()

    fun reduce(intent: CallIntent){
        when(intent){
            is CallIntent.OnStartCall -> {
                Log.d("CallIntent", "OnStartCall")
            }

            is CallIntent.OnDismissPermissionDialog -> {
                dismissPermissionDialog()
            }

            is CallIntent.OnPermissionResult -> {
                onPermissionResult(intent.permission, intent.isGranted)
            }
        }
    }

    private fun dismissPermissionDialog(){
        val permissionQueue = getPermissionQueue().apply {
            removeFirst()
        }

        _state.update {
            it.copy(
                visiblePermissionDialogQueue = permissionQueue.toList()
            )
        }
    }

    private fun onPermissionResult(
        permission: Permission,
        isGranted: Boolean
    ){
        val permissionsQueue = getPermissionQueue()

        if(!isGranted && !permissionsQueue.contains(permission)){
            permissionsQueue.add(permission)

            _state.update {
                it.copy(
                    visiblePermissionDialogQueue = permissionsQueue.toList()
                )
            }
        }
    }

    private fun getPermissionQueue() = state
        .value
        .visiblePermissionDialogQueue
        .toMutableList()
}