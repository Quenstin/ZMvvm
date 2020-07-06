package com.kotlin.zmvvm.ui.activitys

import android.os.Bundle
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleActivity
import com.kotlin.zmvvm.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseLifeCycleActivity<LoginViewModel>() {


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        super.initView()
        showSuccess()

    }


    override fun showCreateReveal(): Boolean {
        return true
    }

    override fun initData() {
        clickBt.setOnClickListener {
            mViewModel.getLoginData("13718132104@163.com", "Zgq768120757")
        }
    }


    /**
     * 返回键处理
     */
    override fun onBackPressed() {
//        finish()
    }

    /**
     * 订阅livedata 获得返回的数据
     */
    override fun initDataObserver() {
        mViewModel.loginData.observe(this, Observer {
            it?.let { loginResponse ->
                toast("登陆成功")
                tvContent.text = it.id.toString()
//                finish()
            }
        })
    }
}