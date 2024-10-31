package com.vasilyev.presentation

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.vasilyev.core.components.PermissionDialog
import com.vasilyev.core.permissions.NotificationPermissionTextProvider
import com.vasilyev.core.permissions.RecordAudioPermissionTextProvider
import com.vasilyev.core.utils.Permission
import com.vasilyev.core.utils.mapToPermission
import com.vasilyev.core.utils.mapToString

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
private val permissionsToRequestTiramisu = arrayOf(
    Manifest.permission.RECORD_AUDIO,
    Manifest.permission.POST_NOTIFICATIONS
)

private val permissionsToRequest = arrayOf(
    Manifest.permission.RECORD_AUDIO
)

@Composable
fun CallScreen(
    state: CallState,
    onIntent: (CallIntent) -> Unit
) {
    val dialogQueue = state.visiblePermissionDialogQueue

    val multiplePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                permissionsToRequestTiramisu
            }else{
                permissionsToRequest
            }.forEach { permission ->
                onIntent(
                    CallIntent.OnPermissionResult(
                        permission = permission.mapToPermission(),
                        isGranted = permissions[permission] == true
                    )
                )
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        FindCompanionButton(
            onClick = {
                multiplePermissionLauncher.launch(
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        permissionsToRequestTiramisu
                    }else{
                        permissionsToRequest
                    }
                )
            }
        )
    }

    PermissionsDialogQueue(
        permissionsDialogQueue = dialogQueue,
        onDismissDialog = {
            onIntent(CallIntent.OnDismissPermissionDialog)
        },
        requestPermission = { permission ->
            multiplePermissionLauncher.launch(
                arrayOf(permission.mapToString())
            )
        }
    )
}

@Composable
private fun FindCompanionButton(
    onClick: () -> Unit
){
    Button(
        content = {
            Text("Request Permission")
        },
        onClick = onClick,
    )
}

@Composable
private fun PermissionsDialogQueue(
    permissionsDialogQueue: List<Permission>,
    onDismissDialog: () -> Unit,
    requestPermission: (Permission) -> Unit
){
    val context = LocalContext.current as ComponentActivity

    permissionsDialogQueue
        .reversed()
        .forEach { permission ->
            PermissionDialog(
                permissionTextProvider = when (permission) {
                    Permission.RECORD_AUDIO -> RecordAudioPermissionTextProvider()
                    Permission.POST_NOTIFICATIONS -> NotificationPermissionTextProvider()
                },
                isPermanentlyDeclined = !context
                    .shouldShowRequestPermissionRationale(permission.mapToString()),
                onDismiss = onDismissDialog,
                onOkClick = {
                    onDismissDialog()
                    requestPermission(permission)
                },
                onGoToAppSettingsClick = context::openAppSettings
            )
        }
}

private fun Activity.openAppSettings(){
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts(
            "package",
            packageName,
            null
        )
    ).apply {
        startActivity(this)
    }
}