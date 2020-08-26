package com.kotlin.zmvvm.ui.wechat.data

/**
 * Created by zhgq on 2020/8/25
 * Describe：
 */
data class WeChatTabNameResponse(var courseId : Int,
                                 var name : String,
                                 var id : Int,
                                 var order : Int,
                                 var parentChapterId : Int,
                                 var userControlSetTop : Boolean) {
}