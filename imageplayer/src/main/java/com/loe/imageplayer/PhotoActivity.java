package com.loe.imageplayer;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends FragmentActivity
{
    private PhotoViewPager photoViewPager;
    private List<String> urlList;
    private List<String> nameList;
    public View viewBottom;
    private TextView textName;
    private TextView textIndex;
    private View buttonDownload;

    private Animation inAnimation;
    private Animation outAnimation;

    private boolean isFast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(0xFF181818);
        }
        setContentView(R.layout.photo_activity);

        isFast = getIntent().getBooleanExtra("isFast", false);

        urlList = new ArrayList<>();
        nameList = new ArrayList<>();

        try
        {
            JSONArray js = new JSONArray(getIntent().getStringExtra("data"));
            for (int i = 0; i < js.length(); i++)
            {
                JSONObject json = js.getJSONObject(i);
                urlList.add(json.optString("url"));
                nameList.add(json.optString("name"));
            }
        } catch (Exception e)
        {
        }

        try
        {
            if (urlList.isEmpty())
            {
                urlList.add(getIntent().getStringExtra("url"));
                String name = getIntent().getStringExtra("name");
                if (name == null)
                {
                    name = "";
                }
                nameList.add(name);
            }
        } catch (Exception e)
        {
            finish();
        }

        initView();
    }

    private void initView()
    {
        viewBottom = findViewById(R.id.viewBottom);

        textName = findViewById(R.id.textName);
        textIndex = findViewById(R.id.textIndex);
        buttonDownload = findViewById(R.id.buttonDownload);

        textIndex.setVisibility(urlList.size() > 1 ? View.VISIBLE : View.GONE);
        buttonDownload.setVisibility(ImagePlayer.onDownloadListener != null ? View.VISIBLE : View.GONE);

        int i = getIntent().getIntExtra("index", 0);
        if(i >= urlList.size()) i = urlList.size() -1;
        textName.setText(nameList.get(i));
        textIndex.setText((i + 1) + "/" + urlList.size());
        photoViewPager = (PhotoViewPager) findViewById(R.id.viewPager);
        photoViewPager.init(urlList, i, getSupportFragmentManager());

        inAnimation = new TranslateAnimation(1, 0, 1, 0, 1, 1, 1, 0);
        inAnimation.setDuration(150);
        outAnimation = new TranslateAnimation(1, 0, 1, 0, 1, 0, 1, 1);
        outAnimation.setDuration(150);
        outAnimation.setInterpolator(new AccelerateInterpolator());
        outAnimation.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                viewBottom.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });

        photoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int i)
            {
                textName.setText(nameList.get(i));
                textIndex.setText((i + 1) + "/" + urlList.size());
            }

            @Override
            public void onPageScrolled(int i, float v, int i1)
            {
            }

            @Override
            public void onPageScrollStateChanged(int i)
            {
            }
        });

        // 判断isFast
        if(isFast)
        {
            viewBottom.setVisibility(View.GONE);
        }
    }

    public void displayBottom(boolean is)
    {
        if(isFast)
        {
            activityBack(null);
            return;
        }
        try
        {
            viewBottom.setVisibility(View.VISIBLE);
            viewBottom.clearAnimation();
            viewBottom.startAnimation(is ? inAnimation : outAnimation);
        } catch (Exception e)
        {
        }
    }

    public void activityBack(View view)
    {
        finish();
        overridePendingTransition(R.anim.on, R.anim.scale_out);
    }

    public void activityDownload(View view)
    {
        int i = photoViewPager.getCurrentItem();
        ImagePlayer.download(urlList.get(i));
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.on, R.anim.scale_out);
    }
}