package com.kotlin.zmvvm.ui.project.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleFragment
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.ui.project.data.ProjectTabBean
import com.kotlin.zmvvm.ui.project.viewmodel.ProjectViewModel
import com.kotlin.zmvvm.ui.wechat.adapter.WeChatTabAdapter
import kotlinx.android.synthetic.main.fragment_project.*

/**
 * Created by zhgq on 2020/9/9
 * Describe：项目
 * @author 13718
 */
class ProjectFragment : BaseLifeCycleFragment<ProjectViewModel>() {

    companion object{
        fun getInstance():ProjectFragment{
            return ProjectFragment()
        }
    }

    override fun initView() {
        super.initView()
        initColor()
    }

    private fun initColor() {
        projectTab.indicatorColor=ColorUtil.getColor(activity!!)
        projectTab.dividerColor=ColorUtil.getColor(activity!!)
    }

    override fun initData() {
        super.initData()
        mViewModel.getProjectTabData()
    }


    override fun initDataObserver() {
        mViewModel.mProjectTabData.observe(this, Observer { reponse ->
            reponse?.let {
                setProjectTabData(it)

            }
        })

    }

    private fun setProjectTabData(datas:List<ProjectTabBean>){
        val tabs = arrayListOf<String>()
        val fragments= arrayListOf<Fragment>()

        for (data in datas){
            tabs.add(data.name)
            fragments.add(ProjectArticleFragment.getInstance(data.id))
        }
        projectViewPager.adapter=WeChatTabAdapter(childFragmentManager,tabs,fragments)
        projectTab.setViewPager(projectViewPager)
    }

    override fun getLayoutId()= R.layout.fragment_project
}