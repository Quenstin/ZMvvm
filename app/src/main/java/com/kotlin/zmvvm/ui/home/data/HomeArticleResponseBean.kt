package com.kotlin.zmvvm.ui.home.data

import com.kotlin.zmvvm.ui.common.data.ArticleBean

/**
 * Created by zhgq on 2020/7/20
 * Describe：
 */
data class HomeArticleResponseBean(var curPage: Int,
                                   var datas: List<ArticleBean>,
                                   var offset: Int,
                                   var over: Boolean,
                                   var pageCount: Int,
                                   var size: Int,
                                   var total: Int)