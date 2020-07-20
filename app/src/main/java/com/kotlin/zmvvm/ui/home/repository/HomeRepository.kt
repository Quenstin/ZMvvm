package com.kotlin.zmvvm.ui.home.repository

import androidx.lifecycle.MutableLiveData
import com.kotlin.zmvvm.common.repository.ArticleRepository
import com.kotlin.zmvvm.common.state.State
import com.kotlin.zmvvm.network.dataConvert
import com.kotlin.zmvvm.ui.home.data.ArticleBean
import com.kotlin.zmvvm.ui.home.data.BannerResponseBean
import com.kotlin.zmvvm.ui.home.data.HomeArticleResponseBean

/**
 * Created by zhgq on 2020/7/20
 * Describe：
 */
class HomeRepository(loadState:MutableLiveData<State>):ArticleRepository(loadState) {


    suspend fun loadBannerCo():List<BannerResponseBean>{
    return apiService.loadBannerCo().dataConvert(loadState)
    }

    suspend fun loadTopArticleCo():List<ArticleBean>{
        return apiService.loadTopArticleCo().dataConvert(loadState)
    }

    suspend fun loadHomeArticleCo(pageNum:Int):HomeArticleResponseBean{
        return apiService.loadHomeArticleCo(pageNum).dataConvert(loadState)
    }
}