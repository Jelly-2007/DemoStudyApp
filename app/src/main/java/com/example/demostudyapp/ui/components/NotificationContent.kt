package com.example.demostudyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.demostudyapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@Composable
fun NotificationContent(vm: MainViewModel){

    val notificationHeight = 45.dp

    //虚拟页数
    val virtualCount = Int.MAX_VALUE

    //实际页数
    val actualCount = vm.notifications.size

    //初始图片下标
    val initialIndex = virtualCount / 2

    val pagerState = rememberPagerState(
        initialPage = initialIndex,
        pageCount = { virtualCount }
    )

    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        val timer = Timer()

        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            },
            3000,
            3000
        )

        onDispose {
            timer.cancel()
        }

    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
            MaterialTheme.colorScheme.surfaceContainerHigh
        )
            .height(notificationHeight)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "最新活动",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )



        VerticalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start

        ) { index ->
            val actualIndex =
                (index - initialIndex).floorMod(actualCount)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = vm.notifications[actualIndex],
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(
            modifier = Modifier.width(8.dp)
        )


        Text(
            text = "更多",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1
        )
    }

}

private fun Int.floorMod(other: Int) = when(other){
    0 -> this
    else -> this - floorDiv(other) * other
}