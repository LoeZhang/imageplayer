package com.loe.imageplayer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends FragmentStatePagerAdapter
{
    private List<PhotoFragment> fragments; // Fragment数组

    public PhotoAdapter(FragmentManager fragmentManager, List<String> urls)
    {
        super(fragmentManager);

        fragments = new ArrayList<>();
        for (String url : urls)
        {
            PhotoFragment fragment = new PhotoFragment();
            fragment.setUrl(url);
            fragments.add(fragment);
        }
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public int getItemPosition(Object object)
    {
        return PagerAdapter.POSITION_NONE;
    }
}