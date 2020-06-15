package com.kotlin.zmvvm.common.state

import androidx.annotation.StringRes

/**
 * Created by zhgq on 2020/6/15
 * Describe：
 */
data class State(var code : StateType, var message : String = "", @StringRes var tip : Int = 0) {
}