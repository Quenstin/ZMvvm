package com.kotlin.zmvvm.ui.activitys

import android.os.Bundle
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleActivity
import com.kotlin.zmvvm.ui.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseLifeCycleActivity<LoginViewModel>() {
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

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onBackPressed() {
        finish()
    }

    override fun initDataObserver() {
        mViewModel.loginData.observe(this, Observer {
            it?.let { loginResponse ->
                tvContent.text="${it.userName}${it.id}"
//                finish()
            }
        })
    }
}