package com.kotlin.zmvvm.ui.home.view

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.common.utils.GlideImageLoader
import com.kotlin.zmvvm.common.view.ArticleListFragment
import com.kotlin.zmvvm.ui.activity.ArticleDetailActivity
import com.kotlin.zmvvm.ui.home.data.ArticleBean
import com.kotlin.zmvvm.ui.home.data.BannerResponseBean
import com.kotlin.zmvvm.ui.home.viewmodel.HomeViewModel
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.layout_home_headview.view.*

/**
 * Created by zhgq on 2020/7/16
 * Describe：首页
 */
class HomeFragment : ArticleListFragment<HomeViewModel>() {
    private lateinit var mBanner: Banner

    private val urls by lazy {
        arrayListOf<String>()
    }

    private val titles by lazy {
        arrayListOf<String>()
    }

    private var mCurrentPage = 0

    private var mTopArticlesLoadTimes = 0

    companion object {
        fun getIntance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun initView() {
        super.initView()
        val headView = View.inflate(activity, R.layout.layout_home_headview, null)
        mBanner = headView.mBanner
        mBanner.setOnBannerListener { position ->
            val intent = Intent(activity, ArticleDetailActivity::class.java)
            intent.putExtra("url", urls[position])
            intent.putExtra("title", titles[position])
            startActivity(intent)
        }
        mBanner.setImageLoader(GlideImageLoader())
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        mBanner.setDelayTime(5000)
        mBanner.setBannerAnimation(Transformer.DepthPage)
        mAdapter.addHeaderView(headView)
    }



    override fun initData() {
        mCurrentPage = 0
        mTopArticlesLoadTimes = 0
        mViewModel.loadBannerData()
        mViewModel.loadHomeArticleData(mCurrentPage)
    }

    override fun initDataObserver() {
        super.initDataObserver()
        mViewModel.mBannerData.observe(this, Observer { t ->
            t?.let {
                setBannerData(t)
            }
        })

        mViewModel.mHomeArticleData.observe(this, Observer { t ->
            t?.let {
                mViewModel.mTopArticleData.observe(this, Observer { topArticle ->
                    topArticle?.let {
                        if (mCurrentPage == 0 && mTopArticlesLoadTimes == 0) {
                            handleTopArticle(it)
                            addData(topArticle + t.datas)
                            mTopArticlesLoadTimes++
                        }
                    }
                })
                if (mCurrentPage != 0) {
                    addData(t.datas)
                }
            }
        })
    }


    override fun onRefreshData() {
        mCurrentPage = 0
        mTopArticlesLoadTimes = 0
        mViewModel.loadBannerData()
        mViewModel.loadHomeArticleData(mCurrentPage)
    }

    override fun onLoadMoreData() {
        mViewModel.loadHomeArticleData(++mCurrentPage)
    }

    private fun setBannerData(bannerData: List<BannerResponseBean>) {
        val images = ArrayList<String>()
        urls.clear()
        titles.clear()
        for (item in bannerData) {
            images.add(item.imagePath)
            urls.add(item.url)
            titles.add(item.title)
        }
        mBanner.setImages(images)
        mBanner.setBannerTitles(titles)
        mBanner.start()

    }

    private fun handleTopArticle(it: List<ArticleBean>) {
        for (item in it) {
            item.top = true
        }
    }

}