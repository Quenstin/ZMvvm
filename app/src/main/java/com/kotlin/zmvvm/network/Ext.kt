package com.kotlin.zmvvm.network

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.common.state.StateType
import com.kotlin.zmvvm.common.state.UserInfo
import com.kotlin.zmvvm.common.utils.Constant
import com.kotlin.zmvvm.network.response.BaseResponse

/**
 * Created by zhgq on 2020/6/16
 * Describe：拓展函数
 */

fun <T> BaseResponse<T>.dataConvert(
    loadState: MutableLiveData<State>
) : T{
    when (errorCode) {
        Constant.SUCCESS -> {
            if (data is List<*>) {
                if ((data as List<*>).isEmpty()) {
                    loadState.postValue(State(StateType.EMPTY))
                }
            }
            loadState.postValue(State(StateType.SUCCESS))
            return data
        }
        Constant.NOT_LOGIN -> {
            UserInfo.instance.logoutSuccess()
            loadState.postValue(State(StateType.ERROR, message = "请重新登录"))
            return data
        }
        else -> {
            loadState.postValue(State(StateType.ERROR, message = errorManager))
            throw Exception(errorManager)
        }
    }
}

