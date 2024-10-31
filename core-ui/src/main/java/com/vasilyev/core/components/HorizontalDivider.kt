package com.vasilyev.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vasilyev.core.ui.theme.DividerColor

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    color: Color = DividerColor
){
    Box(
        modifier = modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(color)
            .clip(RoundedCornerShape(10.dp))
    )
}