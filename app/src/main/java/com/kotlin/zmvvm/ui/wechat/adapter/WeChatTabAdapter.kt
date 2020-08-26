package com.kotlin.zmvvm.ui.wechat.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by zhgq on 2020/8/25
 * Describe：
 */
class WeChatTabAdapter(
    fragmentManager: FragmentManager,
    val tabs: List<String>,
    val fragments: List<Fragment>
) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount() = tabs.size

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int) = tabs[position]
}