package com.kotlin.zmvvm.common.state

import android.app.Activity
import android.content.Context
import com.kotlin.zmvvm.common.callback.CollectListener
import com.kotlin.zmvvm.common.state.callback.LoginSuccessState
import com.kotlin.zmvvm.common.utils.Constant
import com.kotlin.zmvvm.common.utils.SPreference

/**
 * Created by zhgq on 2020/6/17
 * Describe：
 */
class UserInfo private constructor() {
    private var isLogin: Boolean by SPreference(Constant.LOGIN_KEY, false)

    var mState: UserState = if (isLogin) LoginState() else LogoutState()

    companion object {
        val instance = Holder.INSTANCE
    }

    private object Holder {
        val INSTANCE = UserInfo()
    }


    // 收藏
    fun collect(context: Context?, position: Int, listener: CollectListener) {
        mState.collect(context, position, listener)
    }

    fun startRankActivity(context: Context?) {
        mState.startRankActivity(context)
    }

    fun startCollectActivity(context: Context?) {
        mState.startCollectActivity(context)
    }

    fun startShareActivity(context: Context?) {
        mState.startShareActivity(context)
    }

    fun startAddShareActivity(context: Context?) {
        mState.startAddShareActivity(context)
    }

    fun startTodoActivity(context: Context?) {
        mState.startTodoActivity(context)
    }

    fun startEditTodoActivity(context: Context?) {
        mState.startEditTodoActivity(context)
    }

    // 跳转去登录
    fun login(context: Activity?) {
        mState.login(context)
    }

    fun loginSuccess(username: String, userId: String, collectIds: List<Int>?) {
        // 改变 sharedPreferences   isLogin值
        isLogin = true
        UserInfo.instance.mState = LoginState()

        // 登录成功 回调 -> DrawerLayout -> 个人信息更新状态
        LoginSuccessState.notifyLoginState(username, userId, collectIds)
    }

    fun logoutSuccess() {
        UserInfo.instance.mState = LogoutState()
        // 清除 cookie、登录缓存
        var mCookie by SPreference("cookie", "")
        mCookie = ""
        isLogin = false
        LoginSuccessState.notifyLoginState("未登录", "--", null)
    }


}