package com.kotlin.zmvvm.common.callback

import com.kingja.loadsir.callback.Callback
import com.kotlin.zmvvm.R

/**
 * Created by zhgq on 2020/6/16
 * Describe：数据错误回调
 */
class ErrorCallBack : Callback(){
    override fun onCreateView(): Int {
        return R.layout.layout_error
    }
}