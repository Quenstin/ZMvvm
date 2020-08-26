package com.kotlin.zmvvm.ui.wechat.data

import com.kotlin.zmvvm.ui.common.data.ArticleBean

/**
 * Created by zhgq on 2020/8/25
 * Describe：
 */
data class WeChatArticleResponse(var curPage: Int,
                                 var datas: List<ArticleBean>,
                                 var offset: Int,
                                 var over: Boolean,
                                 var pageCount: Int,
                                 var size: Int,
                                 var total: Int) {
}