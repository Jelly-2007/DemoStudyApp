package com.example.demostudyapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {

    var taskData by mutableStateOf("学习周期：2026.01.01 - 2022.12.31")
        private set

    //学年总积分
    var totalPointOfYear = 13500

    //学年积分
    var pointOfYear by mutableStateOf(13231)
        private set


    //计算学年积分进度 = 220f * pointOfYear / 学年总积分
    var pointOfYearPercent by mutableStateOf(0f)
        private set


    /**
     * 更新学年积分进度
     */
    fun updatePointPercent(){
        pointOfYearPercent = 220f * pointOfYear / totalPointOfYear
    }


}