package com.example.demostudyapp.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.demostudyapp.entity.model.Category
import com.example.demostudyapp.entity.model.DataType
import kotlin.collections.listOf

class MainViewModel: ViewModel() {

    //分类数据
    val categories by mutableStateOf(
        listOf(
            Category("思想政治"),
            Category("法律法规"),
            Category("职业道德"),
            Category("诚信自律")
        )
    )

    //当前分类下标
    var categoryIndex by mutableStateOf(0)
        private set

    /**
     * 更新分类下标
     */
    fun updateCategoryIndex(index: Int){
        categoryIndex = index
    }

    val types by mutableStateOf(
        listOf(
            DataType(
                title = "相关咨询",
                icon = Icons.Default.Description
            ),
            DataType(
                title = "视频课程",
                icon = Icons.Default.SmartDisplay
            )
        )
    )

    //当前类型下标
    var currentTypeIndex by mutableStateOf(0)
        private set

    /**
     * 更新类型下标
     */
    fun updateTypeIndex(index: Int){
        currentTypeIndex = index
    }
}