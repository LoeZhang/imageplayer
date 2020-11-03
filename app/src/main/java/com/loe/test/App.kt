package com.loe.test

import android.app.Application
import android.widget.Toast
import com.bumptech.glide.Glide
import com.loe.imageplayer.ImagePlayer

class App : Application()
{
    override fun onCreate()
    {
        super.onCreate()

        ImagePlayer.setOnDisplayListener()
        { imageView, url ->
            Glide.with(this)
                .load(url)
                .placeholder(R.mipmap.imageplayer_default_load)
                .error(R.mipmap.imageplayer_default_error)
                .into(imageView)
        }

        ImagePlayer.setOnDownloadListener()
        {imageUrl ->
            Toast.makeText(this, "下载成功", Toast.LENGTH_SHORT).show()
        }
    }
}