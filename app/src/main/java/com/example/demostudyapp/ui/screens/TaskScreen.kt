package com.example.demostudyapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                ){
                    //圆环

                    //进度数据
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            buildAnnotatedString {
                                append(taskVM.pointOfYear)
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
        }
    }
}
