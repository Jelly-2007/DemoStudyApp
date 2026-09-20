package com.example.demostudyapp.ui.screens

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.demostudyapp.R
import com.example.demostudyapp.ui.components.TopAppBar

@Composable
fun MineScreen() {
    val menus = listOf(
        MenuItem(R.drawable.database,"学习积分"),
        MenuItem(R.drawable.chart_line,"浏览记录"),
        MenuItem(R.drawable.clipboard_check,"学习档案"),
        MenuItem(R.drawable.help_circle,"常见问题"),
        MenuItem(R.drawable.info_circle,"版本信息"),
        MenuItem(R.drawable.settings,"个人设置"),
    )
    Column(
        modifier = Modifier
    ) {
        TopAppBar(

        ) {
            Text(
                text = "我的",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        LazyColumn(

        ) {
            //头像部分
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape)
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .height(62.dp)
                    ) {
                        Text(
                            text = "未登录",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "已坚持学习0天",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            //菜单部分
            itemsIndexed(menus){index,menu->
                if(index == 3){
                    Box(
                        modifier = Modifier
                            .height(16.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .fillMaxWidth()
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = menu.icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(17.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ){
                            Text(
                                text = menu.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier
                                    .weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos ,
                                contentDescription = null,
                                modifier = Modifier.width(13.dp)
                            )
                        }
                        HorizontalDivider(
                        )
                    }
                }
            }

        }

    }
}

data class MenuItem(
    @DrawableRes
    val icon: Int,
    val title: String
)
