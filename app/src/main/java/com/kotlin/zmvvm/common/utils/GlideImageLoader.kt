package com.kotlin.zmvvm.common.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * Created by zhgq on 2020/7/20
 * Describe：
 */
class GlideImageLoader : ImageLoader(){
    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)
    }
}