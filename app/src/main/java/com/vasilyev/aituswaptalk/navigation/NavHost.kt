package com.vasilyev.aituswaptalk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vasilyev.presentation.CallScreen
import com.vasilyev.presentation.CallViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun AITUSwapTalkNavHost(
    navController: NavHostController,
){
    NavHost(
        navController = navController,
        startDestination = Destination.CallScreen.route
    ){
        composable(Destination.CallScreen.route){
            val viewModel = koinViewModel<CallViewModel>()
            val state = viewModel.state.collectAsState()

            CallScreen(
                state = state.value,
                onIntent = { intent ->
                    viewModel.reduce(intent)
                }
            )
        }
    }
}