package com.example.demostudyapp.ui.screens

import android.R.attr.category
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.compose.backgroundDark
import com.example.demostudyapp.ui.components.SwiperContent
import com.example.demostudyapp.ui.components.TopAppBar
import com.example.demostudyapp.viewmodel.MainViewModel

@Composable
fun StudyScreen(
    vm: MainViewModel= viewModel()
) {
    Column(
        modifier = Modifier
    ) {
        //标题栏
        TopAppBar(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {

            Surface(
                modifier = Modifier.weight(1f),
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surfaceContainerHigh
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "搜索",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "搜索感兴趣的咨询或课程",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "学习\n进度",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = "26%",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            // 通知
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "通知",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        //分类标签
        PrimaryTabRow(
            selectedTabIndex = vm.categoryIndex
        ) {
            vm.categories.forEachIndexed { index, category ->
                Tab(
                    selected = vm.categoryIndex == index,
                    onClick = {
                        vm.updateCategoryIndex(index)
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant

                ) {
                    Text(
                        text = category.title,
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }

        SecondaryTabRow(
            selectedTabIndex = vm.currentTypeIndex,
            indicator = {},
            divider = {}
        ) {
            vm.types.forEachIndexed { index, dataType ->
                LeadingIconTab(
                    selected = vm.currentTypeIndex == index,
                    onClick = {
                        vm.updateTypeIndex(index)
                    },
                    text = {
                        Text(
                            text = dataType.title,
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    icon = {
                        Icon(
                            imageVector = dataType.icon,
                            contentDescription = null
                        )
                    }

                )


            }
        }
        //轮播图
        SwiperContent(vm)

    }
}
