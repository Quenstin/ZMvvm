package com.kotlin.zmvvm.ui.wechat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.common.view.ArticleListFragment
import com.kotlin.zmvvm.ui.wechat.viewmodel.WeChatViewModel

/**
 * Created by zhgq on 2020/8/25
 * Describe：
 */
class WeChatArticleFragment : ArticleListFragment<WeChatViewModel>() {

    private var page = 1
    private val cid: Int by lazy { arguments?.getInt("id") ?: 1 }

    companion object {
        fun newInstance(id: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("id", id)
            val fragment = WeChatArticleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.getWeChatArticleData(cid, page)
    }

    override fun initDataObserver() {
        super.initDataObserver()
        mViewModel.mWeChatData.observe(this, Observer { response ->
            addData(response.datas)
        })
    }

    override fun onRefreshData() {
        page=0
        mViewModel.getWeChatArticleData(cid, page)

    }

    override fun onLoadMoreData() {
        mViewModel.getWeChatArticleData(cid, ++page)

    }
}