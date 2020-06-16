package com.kotlin.zmvvm.base.view

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kingja.loadsir.core.LoadSir
import com.kotlin.zmvvm.common.callback.EmptyCallBack
import com.kotlin.zmvvm.common.callback.ErrorCallBack
import com.kotlin.zmvvm.common.callback.LoadingCallBack
import com.kotlin.zmvvm.common.utils.Constant
import com.kotlin.zmvvm.common.utils.SPreference
import org.litepal.LitePal

/**
 * Created by zhgq on 2020/6/16
 * Describe：app
 */
open class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        LitePal.initialize(this)
        SPreference.setContext(applicationContext)
        initMode()
        LoadSir.beginBuilder()
            .addCallback(ErrorCallBack())
            .addCallback(LoadingCallBack())
            .addCallback(EmptyCallBack())
            .commit()
    }

    private fun initMode() {
        var isNightMode: Boolean by SPreference(Constant.NIGHT_MODE, false)
        AppCompatDelegate.setDefaultNightMode(if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO)
    }
}