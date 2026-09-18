package com.example.demostudyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.demostudyapp.ui.components.ChartView
import com.example.demostudyapp.ui.components.CircleRing
import com.example.demostudyapp.ui.components.DailyTaskContent
import com.example.demostudyapp.viewmodel.TaskViewModel

@Composable
fun TaskScreen(
    taskVM: TaskViewModel = viewModel()
) {

    // 标题栏内容高度
    val appBarHeight = 64.dp

    //圆环高度
    var boxWidthDp: Int
    with(LocalConfiguration.current){
        boxWidthDp = screenWidthDp / 2
    }

    //当学年积分改变时，更新学年积分百分比
    LaunchedEffect(taskVM.pointOfYear) {
        taskVM.updatePointPercent()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.surface
                    )
                )
            ),

    ) {

        //标题栏
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .height(appBarHeight)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "学习任务",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //学习周期
            item {
                Text(
                    text = taskVM.taskData,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }

            //学习进度
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(top = 8.dp)
                ){
                    //圆环
                    CircleRing(
                        boxWidthDp,
                        taskVM
                    )
                    //进度数据
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            buildAnnotatedString {
                                append(taskVM.pointOfYear.toString())
                                withStyle(
                                    SpanStyle(
                                        fontSize = MaterialTheme.typography.labelSmall.fontSize
                                    )
                                ){
                                    append("分")
                                }

                            },
                            fontSize = 36.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Text(
                            text = "学年积分",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-30).dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${taskVM.totalPointOfYear}分",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "学年规定积分",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${taskVM.totalPointOfYear - taskVM.pointOfYear}分",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "还差",
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }

            //学习明细
            item{
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .padding(horizontal = 8.dp, vertical = 8.dp)

                ) {
                    Text(
                        text = "学习明细",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "最近一周获得积分情况",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    //积分情况的折线图
                    ChartView(
                        points = taskVM.pointOfWeek,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    //日期
                    Row() {
                        taskVM.weeks.forEach {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .weight(1f)
                            )
                        }
                    }

                    //今日任务提醒
                    Text(
                        text = taskVM.tips,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(
                            color = MaterialTheme.colorScheme.surfaceContainerHigh
                        )
                            .padding(8.dp)
                            .fillMaxWidth()
                    )

                    DailyTaskContent()
                }
            }





        }
    }
}
