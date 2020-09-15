package com.kotlin.zmvvm.base.view

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.common.utils.ChangeThemeEvent
import com.kotlin.zmvvm.common.utils.ColorUtil
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Logger
import org.greenrobot.eventbus.Subscribe
import java.util.*

/**
 * Created by zhgq on 2020/6/16
 * Describe：fragment基类
 */
abstract class BaseFragment : Fragment() {
    protected lateinit var loadService: LoadService<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(getLayoutId(), null)
        loadService = LoadSir.getDefault().register(rootView) { reload() }
        EventBus.getDefault().register(this)
        return loadService.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (Date().month + 1 == 4 && Date().date == 4) {
            initStatusColor(ContextCompat.getColor(activity!!, R.color.colorGray666))
        } else {
            initStatusColor(0)
        }
        initView()
        initData()
        Log.e("initvd-->","-----")
    }



    abstract fun initView()

    open fun initData() {}

    open fun reload() {
        initData()
    }

    abstract fun getLayoutId(): Int

    private fun initStatusColor(color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity!!.window.statusBarColor =
                if (color == 0) ColorUtil.getColor(activity!!) else color
        }
        if (ColorUtils.calculateLuminance(Color.TRANSPARENT) >= 0.5) {
            // 设置状态栏中字体的颜色为黑色
            activity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            // 跟随系统
            activity!!.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun changeThemeEvent(event: ChangeThemeEvent) {
        initStatusColor(0)
    }
}