package com.example.demostudyapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.demostudyapp.model.entity.VideoEntity

class VideoViewModel: ViewModel(){

    var list = listOf(
        VideoEntity(
            title = "行测老师告诉你如何指定适合自己的学习方案",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/id/1013/800/300"
        ),
        VideoEntity(
            title = "行测老师告诉你如何指定适合自己的学习方案",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/id/1032/800/300"
        ),
        VideoEntity(
            title = "行测老师告诉你如何指定适合自己的学习方案",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/id/1041/800/300"
        ),
        VideoEntity(
            title = "行测老师告诉你如何指定适合自己的学习方案",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/id/1053/800/300"
        ),
        VideoEntity(
            title = "行测老师告诉你如何指定适合自己的学习方案",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/id/1092/800/300"
        ),
        VideoEntity(
            title = "行测老师告诉你如何指定适合自己的学习方案",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/id/1049/800/300"
        ),
        VideoEntity(
            title = "行测老师告诉你如何指定适合自己的学习方案",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/id/1032/800/300"
        )
    )
        private set
}