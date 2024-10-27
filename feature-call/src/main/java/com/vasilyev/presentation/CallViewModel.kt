package com.vasilyev.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilyev.domain.usecase.ConnectUserUseCase
import com.vasilyev.domain.usecase.GetOnlineUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CallViewModel(
    private val connectUserUseCase: ConnectUserUseCase,
    private val getOnlineUsersUseCase: GetOnlineUsersUseCase
): ViewModel() {
    private val _state = MutableStateFlow(CallState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val usersResult = getOnlineUsersUseCase()

            usersResult.onSuccess { list ->
                list.forEach {
                    Log.d("userNames", it.toString())
                }
            }.onFailure {
                Log.d("userNameserror", it.localizedMessage)
            }
        }
    }

    fun reduce(intent: CallIntent){
        when(intent){
            is CallIntent.OnStartCall -> {
                Log.d("CallIntent", "OnStartCall")
            }
        }
    }
}