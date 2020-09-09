package com.kotlin.zmvvm.ui.navigation.data

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * Created by zhgq on 2020/9/9
 * Describe：
 * @author 13718
 */
data class NavgationBeanHead(var isHead:Boolean,var title:String): SectionEntity<NavgationBean>(isHead,title) {
}