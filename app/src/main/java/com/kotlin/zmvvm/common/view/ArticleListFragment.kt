package com.kotlin.zmvvm.common.view

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleFragment
import com.kotlin.zmvvm.common.callback.CollectListener
import com.kotlin.zmvvm.common.state.UserInfo
import com.kotlin.zmvvm.common.state.callback.LoginSuccessState
import com.kotlin.zmvvm.common.utils.ChangeThemeEvent
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.common.utils.SpeedLayoutManager
import com.kotlin.zmvvm.common.viewmodel.ArticleViewModel
import com.kotlin.zmvvm.interface_.LoginSuccessListener
import com.kotlin.zmvvm.ui.activitys.ArticleDetailActivity
import com.kotlin.zmvvm.ui.home.adapter.ArticleListAdapter
import com.kotlin.zmvvm.ui.common.data.ArticleBean
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.greenrobot.eventbus.Subscribe

/**
 * Created by zhgq on 2020/7/16
 * Describe：列表使用的fragment
 */
abstract class ArticleListFragment<VM : ArticleViewModel<*>> : BaseLifeCycleFragment<VM>(),
    LoginSuccessListener, CollectListener {
    private var mCollectState: Boolean = false
    private var mCurrentItem: Int = 0
    protected lateinit var mAdapter: ArticleListAdapter


    override fun initView() {
        super.initView()
        refreshData()
        mRvArticle.layoutManager = SpeedLayoutManager(activity)
        mAdapter = ArticleListAdapter(R.layout.item_article_list, null)
        mRvArticle.adapter = mAdapter
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val item = mAdapter.getItem(position)
            item?.let {
                val intent = Intent(activity, ArticleDetailActivity::class.java)
                intent.putExtra("url", it.link)
                intent.putExtra("title", it.title)
                startActivity(intent)

            }
        }
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            UserInfo.instance.collect(activity, position, this)
        }
        mAdapter.setEnableLoadMore(true)
        mAdapter.setOnLoadMoreListener({ onLoadMoreData() }, mRvArticle)
        LoginSuccessState.addListeners(this)
    }


    override fun getLayoutId() = R.layout.fragment_article_list

    private fun refreshData() {
        mSrlRefresh.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(activity!!))
        mSrlRefresh.setColorSchemeColors(Color.WHITE)
        mSrlRefresh.setOnRefreshListener { onRefreshData() }

    }

    abstract fun onRefreshData()

    abstract fun onLoadMoreData()

    fun addData(datas: List<ArticleBean>) {
        if (datas.isEmpty()) {
            mAdapter.loadMoreEnd()
            return
        }

        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
            mAdapter.setNewData(datas)
            mAdapter.loadMoreComplete()
            return
        }

        mAdapter.addData(datas)
        mAdapter.loadMoreComplete()
    }

    override fun initDataObserver() {
        mViewModel.mCollectData.observe(this, Observer {
            var article = mAdapter.getItem(mCurrentItem)
            article?.let {
                it.collect = !mCollectState
                mAdapter.notifyDataSetChanged()
            }
        })
    }

    override fun collect(position: Int) {
        val item = mAdapter.getItem(position)
        item?.let {
            mCurrentItem = position
            mCollectState = it.collect
            if (mCollectState) mViewModel.unCollectCo(it.id) else mViewModel.collectCo(it.id)
        }
    }

    override fun loginSuccess(userName: String, userId: String, collectArticleIds: List<Int>?) {
        collectArticleIds?.let {
            it.forEach { id ->
                mAdapter.data.forEach { item ->
                    if (item.id == id) {
                        item.collect = true
                    }
                }

            }
        } ?: let {
            mAdapter.data.forEach { item ->
                item.collect = false
            }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        LoginSuccessState.removeListener(this)
    }

    @Subscribe
    fun settingEvent(event: ChangeThemeEvent){
        mSrlRefresh.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(activity!!))
        mAdapter.notifyDataSetChanged()
    }
}