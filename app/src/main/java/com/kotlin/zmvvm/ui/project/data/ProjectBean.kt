package com.kotlin.zmvvm.ui.project.data

import com.kotlin.zmvvm.ui.common.data.ArticleBean

/**
 * Created by zhgq on 2020/9/9
 * Describe：
 * @author 13718
 */
data class ProjectBean(
    var datas: List<ArticleBean>,
    var curPage: Int,
    var size: Int,
    var total: Int,
    var pageCount: Int
) {
}