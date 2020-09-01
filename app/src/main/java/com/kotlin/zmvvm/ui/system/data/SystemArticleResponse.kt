package com.kotlin.zmvvm.ui.system.data

import com.kotlin.zmvvm.ui.common.data.ArticleBean

/**
 * Created by zhgq on 2020/8/28
 * Describe：
 */
data class SystemArticleResponse(var curPage: Int,
                            var datas: List<ArticleBean>,
                            var pageCount: Int,
                            var total: Int) {
}