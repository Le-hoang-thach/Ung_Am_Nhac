package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.MainViewPagerAdapter;
import com.example.myapplication.Fragment.Fragment_Tim_Kiem;
import com.example.myapplication.Fragment.Fragment_Trang_Chu;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
           }
       },3000);

    }

}

