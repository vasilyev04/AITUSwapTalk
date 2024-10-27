@file:OptIn(ExperimentalPermissionsApi::class)

package com.vasilyev.presentation

import android.Manifest
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@Composable
fun CallScreen(
    state: CallState,
    onIntent: (CallIntent) -> Unit
){
    val microphonePermissionState = rememberPermissionState(Manifest.permission.RECORD_AUDIO)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Button(
            content = {
                Text("Request Permission")
            },
            onClick = {
                if (!microphonePermissionState.status.isGranted
                    && microphonePermissionState.status.shouldShowRationale
                ){

                } else if (!microphonePermissionState.status.isGranted) {
                    microphonePermissionState.launchPermissionRequest()
                } else {
                    //find connection
                }
            },
        )
    }
}