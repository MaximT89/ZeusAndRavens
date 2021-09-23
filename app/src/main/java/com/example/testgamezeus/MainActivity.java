package com.example.testgamezeus;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.testgamezeus.databinding.ActivityMainBinding;
import com.example.testgamezeus.factotySprites.GeneratorSprites;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String TAG = "TAG";

    private AnimationDrawable mAnimationLightning1, mAnimationLightning2, mAnimationLightning3;
    private AnimationDrawable mAnimationRaven1, mAnimationRaven2, mAnimationRaven3;
    private MediaPlayer mp;
    private MediaPlayer mpBg;
    private int durationLightning = 30;
    private int durationRaven = 60;
    private boolean inGame = false;
    private int currentRaven = 0;

    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        initView();
        createAnimForLighting();
        createAnimForRaven();

        binding.btnStartAnim.setOnClickListener(v -> {
            startGame();
        });
    }

    private void createAnimForRaven() {
        createAnimForRaven1();
        createAnimForRaven2();
        createAnimForRaven3();
    }

    private void createAnimForLighting() {
        createAnimForLighting1();
        createAnimForLighting2();
        createAnimForLighting3();
    }

    private void createAnimForLighting1() {
        GeneratorSprites.fillAnimation(mAnimationLightning1, GeneratorSprites.getListBitmapLightning(MainActivity.this), durationLightning);
        binding.imageViewLightning.setBackground(mAnimationLightning1);
    }

    private void createAnimForLighting2() {
        GeneratorSprites.fillAnimation(mAnimationLightning2, GeneratorSprites.getListBitmapLightning(MainActivity.this), durationLightning);
        binding.imageViewLightning2.setBackground(mAnimationLightning2);
    }

    private void createAnimForLighting3() {
        GeneratorSprites.fillAnimation(mAnimationLightning3, GeneratorSprites.getListBitmapLightning(MainActivity.this), durationLightning);
        binding.imageViewLightning3.setBackground(mAnimationLightning3);
    }

    private void createAnimForRaven1() {
        GeneratorSprites.fillAnimation(mAnimationRaven1, GeneratorSprites.getListBitmapRaven(MainActivity.this), durationRaven);
        binding.imageViewRaven1.setBackground(mAnimationRaven1);
    }

    private void createAnimForRaven2() {
        GeneratorSprites.fillAnimation(mAnimationRaven2, GeneratorSprites.getListBitmapRaven(MainActivity.this), durationRaven);
        binding.imageViewRaven2.setBackground(mAnimationRaven2);
    }

    private void createAnimForRaven3() {
        GeneratorSprites.fillAnimation(mAnimationRaven3, GeneratorSprites.getListBitmapRaven(MainActivity.this), durationRaven);
        binding.imageViewRaven3.setBackground(mAnimationRaven3);
    }

    private void initView() {
        mAnimationLightning1 = new AnimationDrawable();
        mAnimationLightning1.setOneShot(true);
        mAnimationLightning1.setVisible(true, true);

        mAnimationLightning2 = new AnimationDrawable();
        mAnimationLightning2.setOneShot(true);
        mAnimationLightning2.setVisible(true, true);

        mAnimationLightning3 = new AnimationDrawable();
        mAnimationLightning3.setOneShot(true);
        mAnimationLightning3.setVisible(true, true);

        mAnimationRaven1 = new AnimationDrawable();
        mAnimationRaven1.setOneShot(false);
        mAnimationRaven1.setVisible(true, true);

        mAnimationRaven2 = new AnimationDrawable();
        mAnimationRaven2.setOneShot(false);
        mAnimationRaven2.setVisible(true, true);

        mAnimationRaven3 = new AnimationDrawable();
        mAnimationRaven3.setOneShot(false);
        mAnimationRaven3.setVisible(true, true);
    }

    private void stopAndStartMpLightning() {
        mp = MediaPlayer.create(this, R.raw.molnia);
        mp.start();
        mp.setOnCompletionListener(MediaPlayer::release);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mpBg = MediaPlayer.create(this, R.raw.bgmusic);
        mpBg.setLooping(true);
        mpBg.start();
        mpBg.setOnCompletionListener(MediaPlayer::release);
    }

    private void startGame() {
        if (!inGame) {
            inGame = true;

            startTimerWithRandomRaven();
        }
    }

    private void startTimerWithRandomRaven() {

        Random random = new Random();
        currentRaven = random.nextInt(3) + 1;

        stopAndStartAnimationRaven(currentRaven);
        Log.d(TAG, "currentRaven: " + currentRaven);

        timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startTimerWithRandomRaven();
            }
        }.start();
    }

    private void stopAndStartAnimationRaven(int currentRaven) {

        binding.imageViewRaven1.setVisibility(View.GONE);
        binding.imageViewRaven2.setVisibility(View.GONE);
        binding.imageViewRaven3.setVisibility(View.GONE);

        if (mAnimationRaven1.isRunning()) {
            mAnimationRaven1.stop();
        } else if (mAnimationRaven2.isRunning()) {
            mAnimationRaven2.stop();
        } else if (mAnimationRaven3.isRunning()) {
            mAnimationRaven3.stop();
        }

        if (currentRaven == 1) {
            mAnimationRaven1.start();
            binding.imageViewRaven1.setVisibility(View.VISIBLE);
        } else if (currentRaven == 2) {
            mAnimationRaven2.start();
            binding.imageViewRaven2.setVisibility(View.VISIBLE);
        } else if (currentRaven == 3) {
            mAnimationRaven3.start();
            binding.imageViewRaven3.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mpBg.isPlaying()) {
            mpBg.stop();
        }
    }

    private void stopAndStartAnimation(AnimationDrawable mAnimation) {
        if (mAnimation.isRunning()) {
            mAnimation.stop();
        }
        mAnimation.start();
    }
}