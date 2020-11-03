package com.loe.imageplayer;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ImagePlayer
{
    public static void open(Activity activity, String url, String name)
    {
        Intent intent = new Intent(activity, PhotoActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("name", name);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.scale_in, R.anim.on);
    }

    public static void open(Activity activity, List<Pair<String, String>> urlList, int index)
    {
        Intent intent = new Intent(activity, PhotoActivity.class);
        JSONArray js = new JSONArray();
        try
        {
            for (Pair<String, String> pair : urlList)
            {
                js.put(new JSONObject()
                        .put("name", pair.first)
                        .put("url", pair.second));
            }
            intent.putExtra("data", js.toString());
            intent.putExtra("index", index);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.scale_in, R.anim.on);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public static void openUrls(Activity activity, List<String> urlList, int index)
    {
        Intent intent = new Intent(activity, PhotoActivity.class);
        JSONArray js = new JSONArray();
        try
        {
            for (String url : urlList)
            {
                js.put(new JSONObject().put("url", url));
            }
            intent.putExtra("data", js.toString());
            intent.putExtra("index", index);
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.scale_in, R.anim.on);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    /**
     * 显示监听
     */
    private static OnDisplayListener onDisplayListener;

    public static void setOnDisplayListener(OnDisplayListener onDisplayListener)
    {
        ImagePlayer.onDisplayListener = onDisplayListener;
    }

    public interface OnDisplayListener
    {
        void onDisplay(ImageView imageView, String url);
    }

    public static void display(ImageView imageView, String url)
    {
        if(onDisplayListener != null)
        {
            onDisplayListener.onDisplay(imageView, url);
        }
    }

    /**
     * 下载监听
     */
    static OnDownloadListener onDownloadListener;

    public static void setOnDownloadListener(OnDownloadListener onDownloadListener)
    {
        ImagePlayer.onDownloadListener = onDownloadListener;
    }

    public interface OnDownloadListener
    {
        void onDownload(String imageUrl);
    }

    public static void download(String imageUrl)
    {
        if(onDownloadListener != null)
        {
            onDownloadListener.onDownload(imageUrl);
        }
    }
}