package com.kotlin.zmvvm.common.state

import android.content.Context
import com.kotlin.zmvvm.common.callback.CollectListener

/**
 * Created by zhgq on 2020/6/17
 * Describe：
 */
interface UserState {
    fun collect(context: Context?, position: Int, listener: CollectListener)

    fun login(context: Context?)

    fun startRankActivity(context: Context?)

    fun startTodoActivity(context: Context?)

    fun startCollectActivity(context: Context?)

    fun startShareActivity(context: Context?)

    fun startAddShareActivity(context: Context?)

    fun startEditTodoActivity(context: Context?)
}