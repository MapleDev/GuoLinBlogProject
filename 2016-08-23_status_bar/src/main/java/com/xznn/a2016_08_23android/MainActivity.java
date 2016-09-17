package com.xznn.a2016_08_23android;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Android状态栏微技巧，带你真正理解沉浸式模式 - 郭霖的专栏 - 博客频道 - CSDN.NET -
 * http://blog.csdn.net/guolin_blog/article/details/51763825
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 全屏
//        func01();

        // 状态栏设置成透明色 的效果是只有5.0及以上系统才支持
//        fun02();

        // 在状态栏设置成透明色 的基础上 制作一个透明导航栏
//        fun03();

        // 真正的沉浸式模式 onWindowFocusChanged()中的语句


        // 根据Android的设计建议，ActionBar是不应该独立于状态栏而单独显示的，
        // 因此状态栏如果隐藏了，我们同时也需要调用ActionBar的hide()方法将ActionBar也进行隐藏
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    /**
     * 在状态栏设置成透明色 的基础上 制作一个透明导航栏
     */
    private void fun03() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 状态栏设置成透明色 的效果是只有5.0及以上系统才支持
     */
    private void fun02() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            // 使用了SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN和SYSTEM_UI_FLAG_LAYOUT_STABLE，注意两个Flag必须要结合在一起使用，
            // 表示会让应用的主体内容占用系统状态栏的空间
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            // 将状态栏设置成透明色
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 全屏
     */
    private void func01() {
        // 获取到了当前界面的DecorView
        View decorView = getWindow().getDecorView();
        // SYSTEM_UI_FLAG_FULLSCREEN表示全屏的意思，也就是会将状态栏隐藏。
        int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
        // 设置系统UI元素的可见性
        decorView.setSystemUiVisibility(option);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}
