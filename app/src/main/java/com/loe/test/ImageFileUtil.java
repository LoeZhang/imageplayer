package com.loe.test;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ImageFileUtil
{
    public static void downloadImage(Context context, String imageUrl)
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    File file = Glide.with(context)
                            .asFile()
                            .load(imageUrl)
                            .submit()
                            .get();
                    new Handler(Looper.getMainLooper()).post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            String newPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                                    .getPath() + "/LoeImagePlayer/" + System.currentTimeMillis() + ".jpg";
                            if(ImageFileUtil.copyFile(file.getAbsolutePath(), newPath))
                            {
                                Toast.makeText(context, "下载成功：" + newPath, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static boolean copyFile(String oldPath, String newPath)
    {
        try
        {
            File oldFile = new File(oldPath);

            if (!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead())
            {
                return false;
            }

            File parent = new File(newPath).getParentFile();
            if(!parent.exists())
            {
                parent.mkdirs();
            }

            FileInputStream fileInputStream = new FileInputStream(oldPath);
            FileOutputStream fileOutputStream = new FileOutputStream(newPath);
            byte[] buffer = new byte[1024];
            int byteRead;
            while (-1 != (byteRead = fileInputStream.read(buffer)))
            {
                fileOutputStream.write(buffer, 0, byteRead);
            }
            fileInputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
