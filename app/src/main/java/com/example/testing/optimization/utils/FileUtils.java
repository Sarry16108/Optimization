package com.example.testing.optimization.utils;

import android.os.Environment;

import com.example.testing.optimization.base.BaseApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yanghj on 2017/6/4.
 */

public class FileUtils {
    private static String mImgPath;

    public static String readFileFromAssets(String name) {
        try {
            InputStream fileInputStream = BaseApplication.mContext.getAssets().open(name);
            int lenth = fileInputStream.available();
            byte [] value = new byte[lenth +1];
            fileInputStream.read(value);
            return value.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String readFileFromAssets2(String name) {
        try {
            InputStreamReader inputReader = new InputStreamReader(BaseApplication.mContext.getAssets().open(name) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";
            String Result="";
            while((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void initAppDir() {
        mImgPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/optimization/image/";
        File file = new File(mImgPath);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    public static String getImagePath() {
        return mImgPath;
    }
}
