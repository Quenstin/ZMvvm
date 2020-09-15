package com.kotlin.zmvvm.ui.navigation.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.ui.navigation.data.NavgationBean
import com.kotlin.zmvvm.ui.navigation.data.NavgationLableData
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.item_fragment_navigation.view.*

/**
 * Created by zhgq on 2020/9/8
 * Describe：
 * @author 13718
 */
public class NavigationAdapter(listData: MutableList<NavgationBean>?) :
    BaseQuickAdapter<NavgationBean, BaseViewHolder>(R.layout.item_fragment_navigation, listData) {


    override fun convert(helper: BaseViewHolder?, item: NavgationBean?) {

        helper?.let { helper ->
            item?.let {
                helper.itemView.navTiele.text=it.name
                helper.itemView.nav_label_layout.adapter =
                    object : TagAdapter<NavgationLableData>(it.articles) {
                        override fun getView(
                            parent: FlowLayout?,
                            position: Int,
                            t: NavgationLableData?
                        ): View {
                            val tagView: TextView =
                                LayoutInflater.from(mContext)
                                    .inflate(R.layout.flow_item, parent, false) as TextView
                            tagView.text = it.articles[position].title
                            tagView.setTextColor(ColorUtil.randomColor())
                            return tagView
                        }
                    }
            }
        }


    }
}