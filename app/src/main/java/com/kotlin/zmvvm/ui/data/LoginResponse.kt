package com.kotlin.zmvvm.ui.data

/**
 * Created by zhgq on 2020/6/17
 * Describe：
 */
data class LoginResponse(
    var icon: String,
    var type: String,
    var id: Int,
    var collectId: List<Int>,
    var userName: String
) {
}