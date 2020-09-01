package com.kotlin.zmvvm.ui.activitys

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.common.permission.PermissionResult
import com.kotlin.zmvvm.common.permission.Permissions
import com.kotlin.zmvvm.common.utils.Constant
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog

/**
 * Created by zhgq on 2020/7/6
 * Describe：启动页
 */
class SplashActivity : AppCompatActivity() {

    private var mContext: Context? = null

    private val mPermissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.INTERNET,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = applicationContext
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        setContentView(R.layout.activity_splash)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        initView()
    }

    /**
     * 初始化进场动画
     */
    private fun initView() {
       startIntent()
    }

    private fun startIntent() {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    @AfterPermissionGranted(Constant.RC_STORAGE_PERM)
    private fun initPermission() {
        Permissions(this).request(*mPermissions).observe(
            this, Observer {
                when (it) {
                    is PermissionResult.Grant -> {
                        startIntent()
                    }
                    // 进入设置界面申请权限
                    is PermissionResult.Rationale -> {
                        AppSettingsDialog.Builder(this)
                            .setTitle("申请权限")
                            .setRationale("没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改")
                            .build()
                            .show()
                    }
                    // 进入设置界面申请权限
                    is PermissionResult.Deny -> {
                        AppSettingsDialog.Builder(this)
                            .setTitle("申请权限")
                            .setRationale("没有相关权限应用将无法正常运行，点击确定进入权限设置界面来进行更改")
                            .build()
                            .show()
                    }
                }
            }
        )
    }
}