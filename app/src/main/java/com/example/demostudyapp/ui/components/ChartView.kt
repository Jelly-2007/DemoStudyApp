package com.example.demostudyapp.ui.components

import android.graphics.Canvas
import android.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun ChartView(
    modifier: Modifier = Modifier,
    points: List<Double>
){

    //每一行的高度
    val heightForRow = 24

    //总行数
    val countForRow = 5

    //小圆圈半径
    val circleRadius = 2.5

    //每8dp代表1积分
    val perY = 8.0


    //画布宽度 = 屏幕宽度 - padding *2
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 8 *2

    //画布高度 = 行高 * 总行数 + 小圆圈直径
    val canvasHeight = heightForRow * countForRow + circleRadius * 2

    //在可组合作用域内读取主题颜色，供下方 DrawScope 使用
    val lineColor = MaterialTheme.colorScheme.primary

    val primaryColor = MaterialTheme.colorScheme.primary

    //7平分的宽度
    val averageOffWidth = canvasWidth / 7

    Canvas(
        modifier = modifier
            .size(
                width = canvasWidth.dp,
                height = canvasHeight.dp
            )
    ){
        //画背景横线
        for (index in 0..countForRow){
            //行高 * index + 圆圈半径
            val y = (heightForRow * index + circleRadius).dp.toPx()
            drawLine(
                color = lineColor,
                start = Offset(0f,y),
                end = Offset(size.width,y),
                strokeWidth = 1.5f
            )
        }


        //画圆圈、折现
        for(index in 0 until points.count()){
            val circleCenter = Offset(
                x = (averageOffWidth * index + averageOffWidth / 2).dp.toPx(),
                y =(heightForRow*countForRow - points[index] * perY + circleRadius).dp.toPx()
            )
            drawCircle(
                color = primaryColor,
                radius = circleRadius.dp.toPx(),
                center = circleCenter,
                style = Stroke(5f)
            )

            //画线
            if(index<points.count()-1){
                //下一个点的坐标   index+1的点的坐标
                val nextPointOffset = Offset(
                    x = (averageOffWidth * (index+1) + averageOffWidth / 2).dp.toPx(),
                    y =(heightForRow*countForRow - points[index+1] * perY + circleRadius).dp.toPx()
                )
                drawLine(
                    color = lineColor,
                    start = circleCenter,
                    end = nextPointOffset,
                    strokeWidth = 5f
                )
            }
        }


    }
}