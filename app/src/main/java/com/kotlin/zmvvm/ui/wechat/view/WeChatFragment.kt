package com.kotlin.zmvvm.ui.wechat.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleFragment
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.ui.wechat.adapter.WeChatTabAdapter
import com.kotlin.zmvvm.ui.wechat.data.WeChatTabNameResponse
import com.kotlin.zmvvm.ui.wechat.viewmodel.WeChatViewModel
import kotlinx.android.synthetic.main.fragment_wechat.*

/**
 * Created by zhgq on 2020/8/24
 * Describe：公众号
 */
class WeChatFragment : BaseLifeCycleFragment<WeChatViewModel>() {

    companion object {
        fun getInstance(): WeChatFragment {
            return WeChatFragment()
        }
    }

    override fun initView() {
        super.initView()
        initColor()
    }

    override fun initData() {
        super.initData()
        mViewModel.getWeChatTabNameStr()
    }

    private fun initColor() {
        wechat_tab.dividerColor = ColorUtil.getColor(activity!!)
        wechat_tab.indicatorColor = ColorUtil.getColor(activity!!)
    }


    override fun initDataObserver() {
        mViewModel.mWeChatTabData.observe(this, Observer { resources ->
            initTabName(resources)
        })


    }

    private fun initTabName(listData: List<WeChatTabNameResponse>) {
        val tabs = arrayListOf<String>()
        val fragments = arrayListOf<Fragment>()

        for (data in listData) {
            tabs.add(data.name)
            fragments.add(WeChatArticleFragment.newInstance(data.id))
        }

        wechat_viewpager.adapter = WeChatTabAdapter(childFragmentManager, tabs, fragments)
        wechat_tab.setViewPager(wechat_viewpager)


    }

    override fun getLayoutId() = R.layout.fragment_wechat
}