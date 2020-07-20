package com.kotlin.zmvvm.common.state

import android.content.Context
import com.kotlin.zmvvm.common.callback.CollectListener

/**
 * Created by zhgq on 2020/7/7
 * Describe：页面跳转控制器
 */
class LoginState : UserState {
    override fun collect(context: Context?, position: Int, listener: CollectListener) {
        listener.collect(position)
    }

    override fun login(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun startRankActivity(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun startTodoActivity(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun startCollectActivity(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun startShareActivity(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun startAddShareActivity(context: Context?) {
        TODO("Not yet implemented")
    }

    override fun startEditTodoActivity(context: Context?) {
        TODO("Not yet implemented")
    }
}