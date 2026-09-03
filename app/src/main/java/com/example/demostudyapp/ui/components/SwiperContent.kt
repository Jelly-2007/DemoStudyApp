package com.example.demostudyapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.demostudyapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@Composable
fun SwiperContent(
    vm: MainViewModel
) {
    //虚拟页数
    val virtualCount = Int.MAX_VALUE

    //实际页数
    val actualCount = vm.swiperData.size

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

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp))
    ) { index ->
        val actualIndex =
            (index - initialIndex).floorMod(actualCount)
        AsyncImage(
            model = vm.swiperData[actualIndex].imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 3f)
        )
    }
}


private fun Int.floorMod(other: Int) = when(other){
    0 -> this
    else -> this - floorDiv(other) * other
}