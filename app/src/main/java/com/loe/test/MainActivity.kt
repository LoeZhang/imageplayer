package com.loe.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Pair
import com.loe.imageplayer.ImagePlayer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val urlList = ArrayList<Pair<String, String>>()

        urlList.add(Pair("hhjj颠三倒四", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604382712785&di=6722f7a1a7e2c08c225feb66d6df587f&imgtype=0&src=http%3A%2F%2Fattachments.gfan.com%2Fforum%2F201703%2F23%2F184812km434d0rrjr0j2u0.jpg"))

        urlList.add(Pair("滚滚滚", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604381171740&di=02a61fd0cb8d4586ae804f0db0757e88&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201211%2F19%2F164155ecfm7nskcm7mkfhw.jpg"))

        urlList.add(Pair("对方过后就很快会花费更多", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604382781826&di=714b69188b667ab8dbf252c9bdf81cf4&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201305%2F11%2F212813ql8v5igl9cjt8vmb.jpg"))

        buttonJump.setOnClickListener()
        {
//            ImagePlayer.open(this, urlList, 1)
            ImagePlayer.openFast(this, "")
        }
    }
}