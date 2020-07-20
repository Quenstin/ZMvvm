package com.kotlin.zmvvm.interface_

/**
 * Created by zhgq on 2020/7/7
 * Describe：
 */
interface LoginSuccessListener {
    fun loginSuccess(userName: String, userId: String, collectArticleIds: List<Int>?)
}