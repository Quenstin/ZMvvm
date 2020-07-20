package com.kotlin.zmvvm.ui.home.data

/**
 * Created by zhgq on 2020/7/16
 * Describe：
 */
data class ArticleBean(  var id: Int,
                    var author: String,
                    var shareUser : String,
                    var chapterName: String?,
                    var desc: String,
                    var link: String,
                    var originId: Int,
                    var title: String,
                    var collect: Boolean,
                    var superChapterName: String?,
                    var niceDate: String,
                    var fresh: Boolean,
                    var top: Boolean,
                    var envelopePic : String) {
}