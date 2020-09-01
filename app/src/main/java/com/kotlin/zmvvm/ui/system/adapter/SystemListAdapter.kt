package com.kotlin.zmvvm.ui.system.adapter

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kotlin.zmvvm.R
import com.kotlin.zmvvm.common.utils.ColorUtil
import com.kotlin.zmvvm.ui.system.data.SystemLabelResponse
import com.kotlin.zmvvm.ui.system.data.SystemTabNameResponse
import com.kotlin.zmvvm.ui.system.view.SystemArticleListActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.system_tab.view.*

/**
 * Created by zhgq on 2020/8/28
 * Describe：
 */
class SystemListAdapter(layout: Int, data: List<SystemTabNameResponse>?) :
    BaseQuickAdapter<SystemTabNameResponse, BaseViewHolder>(layout, data) {


    override fun convert(helper: BaseViewHolder?, item: SystemTabNameResponse?) {
        helper?.let {
            item?.let {
                helper.itemView.sysFlow.adapter =
                    object : TagAdapter<SystemLabelResponse>(item.children) {
                        override fun getView(
                            parent: FlowLayout?,
                            position: Int,
                            t: SystemLabelResponse?
                        ): View {
                            val tagView: TextView = LayoutInflater.from(mContext)
                                .inflate(R.layout.flow_item, parent, false) as TextView
                            tagView.text=item.children[position].name
                            tagView.setTextColor(ColorUtil.randomColor())
                            return tagView

                        }

                    }

                val gradientDrawable = GradientDrawable(
                    GradientDrawable.Orientation.BR_TL,
                    intArrayOf(
                        ColorUtil.calculateGradient(0.5f, ColorUtil.randomColor(), Color.WHITE),
                        Color.WHITE
                    )
                )

                helper.itemView.sysFlow.setOnTagClickListener{_, position,_ ->
                    val intent = Intent(mContext, SystemArticleListActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    intent.putExtra("id", it.children[position].id)
                    intent.putExtra("title", it.children[position].name)
                    mContext.startActivity(intent)
                    return@setOnTagClickListener true
                }
            }
        }

    }
}