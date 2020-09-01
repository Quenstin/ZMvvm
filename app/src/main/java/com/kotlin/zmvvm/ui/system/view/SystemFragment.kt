package com.kotlin.zmvvm.ui.system.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleFragment
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.ui.system.adapter.SystemListAdapter
import com.kotlin.zmvvm.ui.system.data.SystemTabNameResponse
import com.kotlin.zmvvm.ui.system.viewmodel.SystemViewModel
import kotlinx.android.synthetic.main.fragment_system.*
import org.greenrobot.eventbus.Subscribe

/**
 * Created by zhgq on 2020/8/28
 * Describe：
 */
class SystemFragment : BaseLifeCycleFragment<SystemViewModel>() {
    private lateinit var mAdapter: SystemListAdapter


    companion object {
        fun getInstance(): SystemFragment {
            return SystemFragment()
        }
    }

    override fun initView() {
        super.initView()
        refresh()

    }

    override fun initData() {
        super.initData()
        mViewModel.loadSystemTab()
    }

    override fun initDataObserver() {
        mViewModel.systemTabData.observe(this, Observer { data -> setTabData(data) })
        sysList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = SystemListAdapter(R.layout.system_tab, null)
        sysList.adapter = mAdapter


    }

    override fun getLayoutId(): Int = R.layout.fragment_system

    private fun refresh() {
        sysSwipe.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(activity!!))
        sysSwipe.setColorSchemeColors(ColorUtil.getColor(activity!!))
        sysSwipe.setOnRefreshListener { mViewModel.loadSystemTab() }
    }


    private fun setTabData(data: List<SystemTabNameResponse>) {
        if (data.isEmpty()) {
            mAdapter.loadMoreEnd()
            return
        }
        if (sysSwipe.isRefreshing) {
            sysSwipe.isRefreshing = false
            mAdapter.setNewData(data)
            mAdapter.loadMoreComplete()
            return
        }

        mAdapter.addData(data)
        mAdapter.loadMoreComplete()

    }

    @Subscribe
    fun settingEvent() {
        sysSwipe.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(activity!!))
        mAdapter.notifyDataSetChanged()

    }
}