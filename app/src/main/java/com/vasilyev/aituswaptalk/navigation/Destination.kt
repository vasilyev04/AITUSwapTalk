package com.vasilyev.aituswaptalk.navigation

sealed class Destination(val route: String) {
    data object CallScreen: Destination("call")
}