package com.example.demostudyapp.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyTaskContent(){

    DailyTaskItem(
        "登录",
        "5积分/每日首次登陆",
        "已获得5积分/每日上限5积分",
        percent = 1f
    )

    DailyTaskItem(
        "文章学习",
        "10积分/每有效阅读一篇文章",
        "已获得50积分/每日上限100积分",
        percent = 0.7f
    )

    DailyTaskItem(
        "试听学习",
        "10积分/每有效观看1分钟",
        "已获得50积分/每日上限1000积分",
        percent = 0.5f
    )

}


@Composable
fun DailyTaskItem(
    title: String,
    secondaryTitle: String,
    desc: String,
    percent: Float
){
    val inlineContentId = "inlineContentId"

    val secondaryAnnotatedText = buildAnnotatedString {
        append(secondaryTitle)
        appendInlineContent(inlineContentId,"[icon]")
    }

    val inlineContent = mapOf(
        Pair(
            inlineContentId,
            InlineTextContent(Placeholder(width = 14.sp,
                height = 14.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
                )
            ) {
                Icon(
                    imageVector = Icons.Default.HelpOutline,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable{
                            Log.i("===","点击了问号")
                        }
                )
            }
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(7.5f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = secondaryAnnotatedText,
                inlineContent = inlineContent,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                LinearProgressIndicator(
                    progress = { percent },
                    modifier = Modifier.weight(3f),
                    gapSize = 0.dp,
                    drawStopIndicator = {}
                )
                Text(
                    text = desc,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(7f,false)
                        .padding(horizontal = 8.dp)
                )
            }
        }
        OutlinedButton(
            onClick = {},
            border = if (percent >= 1f) ButtonDefaults.outlinedButtonBorder else BorderStroke(
                1.dp,
                Color(0xFFFF5900)
            ),
            modifier = Modifier
                .weight(2.5f),
            enabled = (percent<1f)
        ) {
            Text(
                text = "去学习"
            )
        }

    }



}