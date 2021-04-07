package com.loe.imageplayer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoFragment extends Fragment
{
    private PhotoView photoView;
    private String url;

    public PhotoFragment()
    {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        try
        {
            url = savedInstanceState.getString("url");
        } catch (Exception e)
        {
        }
        photoView = new PhotoView(getContext());
        displayPhotoView();
//        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener()
//        {
//            @Override
//            public void onPhotoTap(View view, float x, float y)
//            {
//                PhotoActivity photoActivity = (PhotoActivity)getActivity();
//                photoActivity.displayBottom(photoActivity.viewBottom.getVisibility() == View.GONE);
//            }
//        });
        photoView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                PhotoActivity photoActivity = (PhotoActivity)getActivity();
                photoActivity.displayBottom(true);
                return true;
            }
        });
        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener()
        {
            @Override
            public void onViewTap(View view, float x, float y)
            {
                PhotoActivity photoActivity = (PhotoActivity)getActivity();
                photoActivity.displayBottom(photoActivity.viewBottom.getVisibility() == View.GONE);
            }
        });
        return photoView;
    }

    public void displayPhotoView()
    {
        if (url != null)
        {
            ImagePlayer.display(photoView, url);
        }
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * 存储url
     */
    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putString("url", url);
        super.onSaveInstanceState(outState);
    }
}
