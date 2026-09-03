package com.example.demostudyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.demostudyapp.model.entity.NavigationItem

@Composable
fun MainFrame(){

    val navigationItems = listOf(
        NavigationItem("学习", Icons.Filled.Home),
        NavigationItem("任务", Icons.Filled.DateRange),
        NavigationItem("我的", Icons.Filled.Person)
    )

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }

    val bottomBarHeight = 96.dp

    Scaffold(

        contentWindowInsets = WindowInsets(0, 0, 0, 0),

        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .background(
                    MaterialTheme.colorScheme.surface
                )
                    .height(bottomBarHeight)
            ){
                navigationItems.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        selected = currentNavigationIndex == index,
                        onClick = {
                            currentNavigationIndex = index
                        },
                        icon = {
                            Icon(
                                imageVector = navigationItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                text = navigationItem.title
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            when(currentNavigationIndex){
                0->StudyScreen()
                1->TaskScreen()
                2->MineScreen()
            }
        }
    }
}

