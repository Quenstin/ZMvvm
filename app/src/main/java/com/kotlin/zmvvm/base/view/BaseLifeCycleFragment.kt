package com.kotlin.zmvvm.base.view

import android.text.TextUtils
import android.widget.Toast
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

/**
 * Created by zhgq on 2020/6/16
 * Describe：生命周期管理
 */
abstract class BaseLifeCycleFragment<VM : BaseViewModel<*>> : BaseFragment() {

    protected lateinit var mViewModel: VM

    override fun initView() {
        showLoading()
        mViewModel = ViewModelProvider(this).get(CommonUtil.getClass(this))
        mViewModel.loadState.observe(this,observer)
        initDataObserver()

    }

    abstract fun initDataObserver()

    private fun showLoading() {
        loadService.showCallback(LoadingCallBack::class.java)
    }

    private fun showSuccess() {
        loadService.showCallback(SuccessCallback::class.java)
    }

    private fun showError(msg: String) {
        if (!TextUtils.isEmpty(msg)) {

            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
        loadService.showCallback(ErrorCallBack::class.java)
    }

    open fun showTip(msg: String) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
        loadService.showCallback(SuccessCallback::class.java)
    }

    open fun showEmpty() {
        loadService.showCallback(EmptyCallBack::class.java)
    }

    private val observer by lazy {
        Observer<State>{
            it?.let {
                when(it.code){
                    StateType.SUCCESS -> showSuccess()
                    StateType.LOADING -> showLoading()
                    StateType.ERROR -> showTip(it.message)
                    StateType.NETWORK_ERROR -> showError("网络异常")
                    StateType.TIP -> showTip(it.message)
                    StateType.EMPTY -> showEmpty()
                }
            }
        }
    }
}