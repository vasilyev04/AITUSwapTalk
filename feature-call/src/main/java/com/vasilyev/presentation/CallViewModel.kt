package com.vasilyev.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vasilyev.domain.CallRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CallViewModel(
    private val callRepository: CallRepository
): ViewModel() {
    private val _state = MutableStateFlow(CallState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val name = callRepository.getOnlineUserNames()
            name.onSuccess {
                Log.d("userNames", it.toString())
            }.onFailure {
                Log.d("error", it.localizedMessage)
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