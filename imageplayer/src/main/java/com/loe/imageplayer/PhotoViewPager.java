package com.loe.imageplayer;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.List;

public class PhotoViewPager extends ViewPager
{
    public PhotoViewPager(Context context)
    {
        super(context);
    }

    public PhotoViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void init(List<String> itemUrls, FragmentManager fragmentManager)
    {
        this.init(itemUrls, 0, fragmentManager);
    }

    public void init(List<String> itemUrls, int now, FragmentManager fragmentManager)
    {
        setAdapter(new PhotoAdapter(fragmentManager, itemUrls));
        // 开始选中
        setCurrentItem(now);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        try
        {
            return super.onInterceptTouchEvent(ev);
        } catch (Exception e)
        {
        }
        return false;
    }
}
