package com.example.demostudyapp.entity.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 *导航栏对象
 *
 */
data class NavigationItem(
    val title: String,      //底部导航栏的标题
    val icon: ImageVector   //底部导航栏图标
)