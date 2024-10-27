package com.vasilyev.presentation

sealed interface CallIntent{
    data object OnStartCall: CallIntent
}