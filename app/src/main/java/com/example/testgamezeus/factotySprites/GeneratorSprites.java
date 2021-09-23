package com.example.testgamezeus.factotySprites;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GeneratorSprites {

    public static List<BitmapDrawable> generateCreateEnemy(Context context) {
        List<BitmapDrawable> list = new ArrayList<>();
        return list;
    }

    // Наполняет аниматор картинками и задает скорость анимации
    public static void fillAnimation(AnimationDrawable mAnimation, List<BitmapDrawable> listBitmap, int duration) {
        for (int i = 0; i < listBitmap.size(); i++) {
            mAnimation.addFrame(listBitmap.get(i), duration);
        }
    }

    // Метод для получения картинки из assets
    private static Drawable getDrawable(Context context, String pathPicture) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(pathPicture);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Drawable drawable = Drawable.createFromStream(inputStream, null);
        return drawable;
    }

    // Данным методом мы берем картинку по ссылке из assets
    private static Bitmap getBitmapFromAssets(Context context, String filepath) {
        AssetManager assetManager = context.getAssets();
        InputStream istr = null;
        Bitmap bitmap = null;

        try {
            istr = assetManager.open(filepath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException ioe) {
            // manage exception
        } finally {
            if (istr != null) {
                try {
                    istr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bitmap;
    }

    // Получаем набор картинок для анимации молнии
    public static List<BitmapDrawable> getListBitmapLightning(Context context) {
        List<BitmapDrawable> frames = new ArrayList<>();
        List<Bitmap> listBitmap = new ArrayList<>();

        listBitmap.add(getBitmapFromAssets(context, "lightning/tile0011.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile000.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile001.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile002.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile003.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile004.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile005.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile006.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile007.png"));
        listBitmap.add(getBitmapFromAssets(context, "lightning/tile0011.png"));

        for (int i = 0; i < listBitmap.size(); i++) {
            frames.add(new BitmapDrawable(context.getResources(), listBitmap.get(i)));
        }

        return frames;
    }

    // Получаем набор картинок для анимации зевса
    public static List<BitmapDrawable> getListBitmapZeus(Context context) {
        List<BitmapDrawable> frames = new ArrayList<>();
        List<Bitmap> listBitmap = new ArrayList<>();

        listBitmap.add(getBitmapFromAssets(context, "zeus/zeus1.png"));
        listBitmap.add(getBitmapFromAssets(context, "zeus/zeus2.png"));
        listBitmap.add(getBitmapFromAssets(context, "zeus/zeus3.png"));
        listBitmap.add(getBitmapFromAssets(context, "zeus/zeus4.png"));


        for (int i = 0; i < listBitmap.size(); i++) {
            frames.add(new BitmapDrawable(context.getResources(), listBitmap.get(i)));
        }

        return frames;
    }

    // Получаем набор картинок для анимации ворона
    public static List<BitmapDrawable> getListBitmapRaven(Context context) {
        List<BitmapDrawable> frames = new ArrayList<>();
        List<Bitmap> listBitmap = new ArrayList<>();

        listBitmap.add(getBitmapFromAssets(context, "raven/raven1.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven2.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven3.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven4.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven5.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven6.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven7.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven8.png"));
        listBitmap.add(getBitmapFromAssets(context, "raven/raven9.png"));

        for (int i = 0; i < listBitmap.size(); i++) {
            frames.add(new BitmapDrawable(context.getResources(), listBitmap.get(i)));
        }

        return frames;
    }

}