package com.kotlin.zmvvm.ui.home.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kotlin.zmvvm.common.viewmodel.ArticleViewModel
import com.kotlin.zmvvm.ui.common.data.ArticleBean
import com.kotlin.zmvvm.ui.home.data.BannerResponseBean
import com.kotlin.zmvvm.ui.home.data.HomeArticleResponseBean
import com.kotlin.zmvvm.ui.home.repository.HomeRepository
import kotlinx.coroutines.launch

/**
 * Created by zhgq on 2020/7/20
 * Describe：
 */
class HomeViewModel(application: Application) : ArticleViewModel<HomeRepository>(application) {

    val mBannerData: MutableLiveData<List<BannerResponseBean>> = MutableLiveData()
    val mTopArticleData: MutableLiveData<List<ArticleBean>> = MutableLiveData()
    val mHomeArticleData: MutableLiveData<HomeArticleResponseBean> = MutableLiveData()


    fun loadBannerData() {
        viewModelScope.launch {
            try {
                mBannerData.value = mRepository.loadBannerCo()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun loadHomeArticleData(pageNum: Int) {
        viewModelScope.launch {
            try {
                if (pageNum == 0) {
                    mTopArticleData.value = mRepository.loadTopArticleCo()
                }
                mHomeArticleData.value = mRepository.loadHomeArticleCo(pageNum)

            } catch (e: Exception) {

            }
        }
    }
}