package com.kotlin.zmvvm.common.view

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.base.view.BaseLifeCycleActivity
import com.kotlin.zmvvm.common.callback.CollectListener
import com.kotlin.zmvvm.common.state.UserInfo
import com.kotlin.zmvvm.common.utils.ChangeThemeEvent
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.common.utils.SpeedLayoutManager
import com.kotlin.zmvvm.common.viewmodel.ArticleViewModel
import com.kotlin.zmvvm.ui.activitys.ArticleDetailActivity
import com.kotlin.zmvvm.ui.common.data.ArticleBean
import com.kotlin.zmvvm.ui.home.adapter.ArticleListAdapter
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.greenrobot.eventbus.Subscribe

/**
 * Created by zhgq on 2020/8/28
 * Describe：
 */
abstract class ArticleListActivity <VM : ArticleViewModel<*>> : BaseLifeCycleActivity<VM>(), CollectListener {
    private var mCollectState : Boolean = false

    private var mCurrentItem : Int = 0

    protected lateinit var mAdapter : ArticleListAdapter

    protected var isLoadMore: Boolean = true

    override fun getLayout(): Int = R.layout.fragment_article_list

    override fun initView() {
        super.initView()
        initRefresh()
        mRvArticle?.layoutManager = SpeedLayoutManager(this, 10f)
        mAdapter =
            ArticleListAdapter(
                R.layout.item_article_list,
                null
            )
        mRvArticle?.adapter  = mAdapter

        mAdapter.setOnItemClickListener { _, _, position ->
            val article = mAdapter.getItem(position)

            article?.let {
                val intent : Intent = Intent(this, ArticleDetailActivity::class.java)
                intent.putExtra("url", it.link)
                intent.putExtra("title", it.title)
                startActivity(intent)
            }
        }

        mAdapter.setOnItemChildClickListener{_, _, position ->
            UserInfo.instance.collect(this, position, this)
        }
        mAdapter.setEnableLoadMore(isLoadMore)
        mAdapter.setOnLoadMoreListener({ onLoadMoreData() }, mRvArticle)
    }

    private fun initRefresh() {
        // 设置下拉刷新的loading颜色
        mSrlRefresh.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(this))
        mSrlRefresh.setColorSchemeColors(Color.WHITE)
        mSrlRefresh.setOnRefreshListener { onRefreshData() }
    }

    /**
     * 下拉刷新
     */
    abstract fun onRefreshData()

    /**
     * 加载更多数据
     */
    abstract fun onLoadMoreData()

    fun addData(articleList : List<ArticleBean>) {

        // 返回列表为空显示加载完毕
        if (articleList.isEmpty()) {
            mAdapter.loadMoreEnd()
            return
        }

        // 如果是下拉刷新状态，直接设置数据
        if (mSrlRefresh.isRefreshing) {
            mSrlRefresh.isRefreshing = false
            mAdapter.setNewData(articleList)
            mAdapter.loadMoreComplete()
            return
        }

        // 初始化状态直接加载数据
        mAdapter.addData(articleList)
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
        var article = mAdapter.getItem(position)

        article?.let {
            mCurrentItem = position
            mCollectState = it.collect
            if (mCollectState) mViewModel.unCollectCo(it.id) else mViewModel.collectCo(it.id)
        }
    }

    override fun reLoad() {
        showLoading()
        onRefreshData()
        super.reLoad()
    }

    @Subscribe
    open fun settingEvent(event: ChangeThemeEvent) {
        mSrlRefresh.setProgressBackgroundColorSchemeColor(ColorUtil.getColor(this))
        mAdapter.notifyDataSetChanged()
    }
}