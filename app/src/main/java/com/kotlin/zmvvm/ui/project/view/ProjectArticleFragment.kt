package com.kotlin.zmvvm.ui.project.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.common.view.ArticleListFragment
import com.kotlin.zmvvm.ui.project.viewmodel.ProjectViewModel

/**
 * Created by zhgq on 2020/9/9
 * Describe：
 * @author 13718
 */
class ProjectArticleFragment : ArticleListFragment<ProjectViewModel>() {

    private var mPage: Int = 1
    private val mId: Int by lazy { arguments?.getInt("id") ?: 1 }


    companion object {
        fun getInstance(id: Int): ProjectArticleFragment {
            val bundel = Bundle()
            bundel.putInt("id", id)
            val fragment = ProjectArticleFragment()
            fragment.arguments = bundel
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        onRefreshData()

    }

    override fun initDataObserver() {
        super.initDataObserver()
        mViewModel.mProjectData.observe(this, Observer { reponse ->
            reponse?.let {
                addData(reponse.datas)
            }
        })
    }


    override fun onRefreshData() {
        mPage = 1
        mViewModel.getProjectData(mPage, mId)
    }

    override fun onLoadMoreData() {
        mViewModel.getProjectData(++mPage, mId)
    }
}