package com.kotlin.zmvvm.common.callback

import com.kingja.loadsir.callback.Callback
import com.kotlin.zmvvm.R

/**
 * Created by zhgq on 2020/6/16
 * Describe：加载中回调
 */
class LoadingCallBack : Callback(){
    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }
}