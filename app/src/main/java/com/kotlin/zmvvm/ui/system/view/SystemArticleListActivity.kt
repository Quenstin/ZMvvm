package com.kotlin.zmvvm.ui.system.view

import android.view.View
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.common.state.UserInfo
import com.kotlin.zmvvm.common.utils.ChangeThemeEvent
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.common.view.ArticleListActivity
import com.kotlin.zmvvm.ui.system.viewmodel.SystemViewModel
import kotlinx.android.synthetic.main.custom_bar.view.*
import org.greenrobot.eventbus.Subscribe


/**
 * Created by zhgq on 2020/8/28
 * Describe：
 */
class SystemArticleListActivity : ArticleListActivity<SystemViewModel>() {

    private var pag: Int = 0
    private lateinit var headView: View

    private val mTitle: String? by lazy { intent?.getStringExtra("titile") }
    private val mCid: Int? by lazy { intent?.getIntExtra("id", 0) }


    override fun initView() {
        super.initView()
        initHeanView()
        mAdapter.setOnItemClickListener { adapter, view, position ->
            UserInfo.instance.collect(this, position, this)

        }

    }

    override fun initData() {
        pag = 0
        mViewModel.loadSystemList(pag, mCid)
    }

    private fun initHeanView() {
        headView = View.inflate(this, R.layout.custom_bar, null)
        headView.detailTitle.text = mTitle
        headView.detailBack.visibility = View.VISIBLE
        headView.detailBack.setOnClickListener { finish() }
        headView.detailSearch.visibility = View.GONE
        headView.setBackgroundColor(ColorUtil.getColor(this))
        mAdapter.addHeaderView(headView)
    }

    override fun initDataObserver() {
        super.initDataObserver()
        mViewModel.systemListData.observe(this, Observer { response ->
            response?.let {
                addData(it.datas)
            }
        })

    }

    override fun onRefreshData() {
        pag = 0
        mViewModel.loadSystemList(pag, mCid)
    }

    override fun onLoadMoreData() {
        mViewModel.loadSystemList(++pag, mCid)

    }

    override fun showCreateReveal()=true


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    @Subscribe
    override fun settingEvent(event: ChangeThemeEvent) {
        super.settingEvent(event)
        headView.setBackgroundColor(ColorUtil.getColor(this))
    }

}