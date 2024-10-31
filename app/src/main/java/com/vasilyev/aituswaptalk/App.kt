package com.vasilyev.aituswaptalk

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.vasilyev.aituswaptalk.navigation.AITUSwapTalkNavHost
import com.vasilyev.core.ui.theme.AITUSwapTalkTheme

@Composable
fun App(){
    val navHostController = rememberNavController()
    AITUSwapTalkTheme(darkTheme = false) {
        Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
            AITUSwapTalkNavHost(navController = navHostController)
        }
    }
}