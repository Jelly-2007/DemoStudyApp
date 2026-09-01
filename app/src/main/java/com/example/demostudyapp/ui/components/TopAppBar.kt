package com.example.demostudyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp


/**
 * 统一标题栏
 * @param modifier
 * @param content 标题栏内容
 */
@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    // 标题栏内容高度
    val appBarHeight = 56.dp

    Row(
        modifier = modifier
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary
                    )
                )
            )
            .statusBarsPadding() // 标题避开状态栏，背景铺到状态栏后面
            .fillMaxWidth()
            .height(appBarHeight),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
