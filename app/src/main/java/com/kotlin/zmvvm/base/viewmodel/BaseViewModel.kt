package com.kotlin.zmvvm.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.base.repository.BaseRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.common.utils.CommonUtil

/**
 * Created by zhgq on 2020/6/16
 * Describe：viewModel 基类
 */
open class BaseViewModel<T : BaseRepository>(application: Application) : AndroidViewModel(application) {

    val loadState by lazy {
        MutableLiveData<State>()
    }

    val mRepository : T by lazy {
        // 获取对应Repository 实例 (有参构造函数)
        (CommonUtil.getClass<T>(this))
            // 获取构造函数的构造器，传入参数class
            .getDeclaredConstructor(MutableLiveData::class.java)
            // 传入加载状态
            .newInstance(loadState)
    }
}