package com.kotlin.zmvvm.base.view

import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.kingja.loadsir.callback.SuccessCallback
import com.kotlin.zmvvm.base.viewmodel.BaseViewModel
import com.kotlin.zmvvm.common.callback.EmptyCallBack
import com.kotlin.zmvvm.common.callback.ErrorCallBack
import com.kotlin.zmvvm.common.callback.LoadingCallBack
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.common.state.StateType
import com.kotlin.zmvvm.common.utils.CommonUtil
import org.jetbrains.anko.toast

/**
 * Created by zhgq on 2020/6/16
 * Describe：自动管理生命周期
 */
abstract class BaseLifeCycleActivity<VM : BaseViewModel<*>> : BaseActivity() {
    protected lateinit var mViewModel: VM

    override fun initView() {
        showLoading()
        mViewModel = ViewModelProvider(this).get(CommonUtil.getClass(this))
        mViewModel.loadState.observe(this, observer)

        initDataObserver()

    }

    abstract fun initDataObserver()

    /**
     * 加载中
     */
    open fun showLoading() {
        loadService.showCallback(LoadingCallBack::class.java)
    }

    /**
     * 成功没有提示信息
     */
    open fun showSuccess() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    /**
     * 空数据
     */
    open fun showEmpty() {
        loadService.showCallback(EmptyCallBack::class.java)
    }

    /**
     * 失败
     */
    open fun showError(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            toast(msg)
        }
        loadService.showCallback(ErrorCallBack::class.java)
    }

    /**
     * 成功有提示信息
     */
    open fun showTip(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            toast(msg)
        }
        loadService.showCallback(SuccessCallback::class.java)

    }

    /**
     * 分发应用状态
     */
    private val observer by lazy {
        Observer<State> {
            it?.let {
                when (it.code) {
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showTip(it.message)
                    StateType.NETWORK_ERROR -> showError("网络出现问题啦")
                    StateType.TIP -> showTip(it.message)
                    StateType.EMPTY -> showEmpty()
                }
            }
        }
    }
}