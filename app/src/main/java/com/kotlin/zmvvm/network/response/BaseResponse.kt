package com.kotlin.zmvvm.network.response


/**
 * Created by zhgq on 2020/6/15
 * Describe：数据基类
 */
open class BaseResponse<T>(var data:T,var errorCode:Int=-1,var errorManager: String="") {
}