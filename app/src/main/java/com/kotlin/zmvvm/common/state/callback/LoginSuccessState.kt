package com.kotlin.zmvvm.common.state.callback

import com.kotlin.zmvvm.interface_.LoginSuccessListener

/**
 * Created by zhgq on 2020/7/7
 * Describe：
 */
object LoginSuccessState {

    var listeners=ArrayList<LoginSuccessListener>()

    fun addListeners(listener: LoginSuccessListener){
        listeners.add(listener)
    }

    fun removeListener(listener: LoginSuccessListener){
        listeners.remove(listener)
    }

    fun notifyLoginState(name : String, userId : String, collectIds : List<Int>?){
        for (listener in listeners) {
            listener.loginSuccess(name, userId, collectIds)
        }
    }

}