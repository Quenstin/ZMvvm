package com.kotlin.zmvvm.common.state

import com.kotlin.zmvvm.common.utils.Constant
import com.kotlin.zmvvm.common.utils.SPreference

/**
 * Created by zhgq on 2020/6/17
 * Describe：
 */
class UserInfo private constructor(){
    private var isLogin: Boolean by SPreference(Constant.LOGIN_KEY,false)

//    var mState:UserState=if (isLogin) loginsta

}