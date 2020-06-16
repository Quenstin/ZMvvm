package com.kotlin.zmvvm.common.utils

import org.greenrobot.eventbus.EventBus

/**
 * Created by zhgq on 2020/6/15
 * Describe：改变主题的event
 */
class ChangeThemeEvent {
    fun post() {
        EventBus.getDefault().post(this)
    }
}