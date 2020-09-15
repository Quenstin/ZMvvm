package com.kotlin.zmvvm.ui.navigation.view

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleFragment
import com.kotlin.zmvvm.ui.navigation.adapter.NavigationAdapter
import com.kotlin.zmvvm.ui.navigation.viewmodel.NavigationViewModel
import kotlinx.android.synthetic.main.fragment_navigation.*

/**
 * Created by zhgq on 2020/9/8
 * Describe：导航
 * @author 13718
 */
class NavigationFragment : BaseLifeCycleFragment<NavigationViewModel>() {

    private lateinit var mAdapter: NavigationAdapter

    companion object {
        fun getInstance(): NavigationFragment {
            return NavigationFragment()

        }
    }

    override fun initView() {
        super.initView()
        navRecycle.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mAdapter= NavigationAdapter(null)
        navRecycle.adapter=mAdapter
    }

    override fun initData() {
        super.initData()
        mViewModel.getNavigationData()
    }

    override fun initDataObserver() {
        mViewModel.navigationTab.observe(this, Observer { response ->
            response?.let {
                mAdapter.setNewData(it)
            }
        })

    }


    override fun getLayoutId() = R.layout.fragment_navigation
}