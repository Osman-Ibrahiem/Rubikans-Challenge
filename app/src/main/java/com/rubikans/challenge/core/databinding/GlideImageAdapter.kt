package com.rubikans.challenge.core.databinding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rubikans.challenge.di.GlideApp


object GlideImageAdapter {

    fun interface OnImageLoad {
        fun onLoad(isLoad: Boolean)
    }

    @BindingAdapter("app:loadImage", "app:placeholder", "app:listener", requireAll = false)
    @JvmStatic
    fun ImageView.load(
        imageUrl: String?,
        placeholder: Drawable?,
        listener: OnImageLoad?,
    ) {
        GlideApp.with(context)
            .load(imageUrl?.replaceFirst("http://", "https://"))
            .placeholder(placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    listener?.onLoad(false)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    listener?.onLoad(true)
                    return false
                }
            })
            .thumbnail(0.1f)
            .into(this)
    }

}