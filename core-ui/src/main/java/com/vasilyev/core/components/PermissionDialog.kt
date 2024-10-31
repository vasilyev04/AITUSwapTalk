package com.vasilyev.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.vasilyev.core.permissions.PermissionTextProvider
import com.vasilyev.core.ui.R

@Composable
fun PermissionDialog(
    permissionTextProvider: PermissionTextProvider,
    isPermanentlyDeclined: Boolean,
    onDismiss: () -> Unit,
    onOkClick: () -> Unit,
    onGoToAppSettingsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Column(
                modifier = modifier
                    .padding(top = 20.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    text = stringResource(R.string.permission_required),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    text = permissionTextProvider.getDescription(isPermanentlyDeclined),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                Column{
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    )
                    Text(
                        text = if(isPermanentlyDeclined) {
                            stringResource(R.string.grant_permission)
                        } else {
                            stringResource(R.string.ok)
                        },
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                if(isPermanentlyDeclined)  {
                                    onGoToAppSettingsClick()
                                } else {
                                    onOkClick()
                                }
                            }
                            .padding(vertical = 15.dp)
                    )
                }
            }
        }
    )
}
