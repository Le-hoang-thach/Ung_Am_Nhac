package com.example.myapplication.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.Adapter.ViewPagerPlaylistnhac;
import com.example.myapplication.Fragment.Fragment_Dia_Nhac;
import com.example.myapplication.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.example.myapplication.Model.Baihat;

import com.example.myapplication.R;


import android.content.Intent;


import android.graphics.Color;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.Handler;
import android.os.StrictMode;
import android.view.View;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

import androidx.viewpager.widget.ViewPager;




import android.widget.ImageButton;

import android.widget.SeekBar;
import android.widget.TextView;




public class PlayNhacActivity extends AppCompatActivity {
    Toolbar toolbarplaynhac;
    TextView txtTimesong, txtTotaltimsong;
    SeekBar sktime;
    ImageButton imgplay, imgrepeat, imgnext , imgpre, imgrandom;
    ViewPager viewPagerplaynhac;
    public  static  ArrayList<Baihat> mangbaihat = new ArrayList<>();
    public  static ViewPagerPlaylistnhac adapternhac;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean checkrandom = false;
    boolean repeat = false;
    boolean next =  false;

    private void init(){
        toolbarplaynhac = findViewById(R.id.toobbarcuaplaynhac);
        txtTimesong = findViewById(R.id.textviewtimesong);
        txtTotaltimsong = findViewById(R.id.textviewtotaltimesong);
        sktime = findViewById(R.id.seebarsong);
        imgplay = findViewById(R.id.imgbuttomplay);
        imgnext = findViewById(R.id.imgbuttomnext);
        imgpre = findViewById(R.id.imgbuttompre);
        imgrepeat = findViewById(R.id.imgbuttomreplay);
        imgrandom = findViewById(R.id.imgbuttonsuffle);
        viewPagerplaynhac = findViewById(R.id.viewplaygerplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       toolbarplaynhac.setNavigationOnClickListener((v) -> {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
       });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        fragment_play_danh_sach_cac_bai_hat =  new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        adapternhac = new ViewPagerPlaylistnhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_dia_nhac);
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
        viewPagerplaynhac.setAdapter(adapternhac);

        if(mangbaihat.size() > 0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getLinkBaiHat());
            imgplay.setImageResource(R.drawable.pause);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();


    }

    private void eventClick() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                        if (mangbaihat.size() > 0){
                            fragment_dia_nhac.Playnhac(mangbaihat.get(0).getHinhAnhBaiHat());
                            handler.removeCallbacks(this);

                        }else{
                            handler.postDelayed(this,300);
                        }
                }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.play);
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.pause);
                }
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.sync);
                        imgrandom.setImageResource(R.drawable.shuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.sync);
                    repeat = true;
                } else{
                        imgrepeat.setImageResource(R.drawable.replay);
                        repeat = false;
                    }

                }

        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.shuffle);
                        imgrandom.setImageResource(R.drawable.replay);
                    }
                    imgrepeat.setImageResource(R.drawable.shuffle);
                    checkrandom = true;
                }  else{
                        imgrandom.setImageResource(R.drawable.shuffle);
                        checkrandom = false;
                    }

                }

        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                            mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() > 0){
                    if(mediaPlayer.isPlaying()  || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.pause);
                        position++;
                        if(repeat == true){
                            if(position == 0){
                                position = mangbaihat.size();
                            }
                            position -= 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index= random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index -1 ;
                            }
                            position = index;
                        }
                        if(position > (mangbaihat.size() -1) ){
                            position = 0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhAnhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });


        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() > 0){
                    if(mediaPlayer.isPlaying()  || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.pause);
                        position--;
                        if(position < 0){
                            position = mangbaihat.size();
                        }
                        if(repeat == true){
                            position += 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index= random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index -1 ;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhAnhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });


        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size() > 0){
                    if(mediaPlayer.isPlaying()  || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < (mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.pause);
                        position--;
                        if(position < 0){
                            position = mangbaihat.size();
                        }
                        if(repeat == true){
                            position += 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index= random.nextInt(mangbaihat.size());
                            if(index == position){
                                position = index -1 ;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getLinkBaiHat());
                        fragment_dia_nhac.Playnhac(mangbaihat.get(position).getHinhAnhBaiHat());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getTenBaiHat());
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent !=null){
            if (intent.hasExtra("cakhuc")){
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangbaihat.add(baihat);
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                mangbaihat = baihatArrayList;
            }
        }

    }



    class PlayMp3 extends AsyncTask<String,Void,String> {


                    @Override
                    protected String doInBackground(String... strings) {
                        return strings[0];

                    }

                    @Override
                    protected void onPostExecute(String baihat) {
                                super.onPostExecute(baihat);
                                    try {
                                    mediaPlayer = new MediaPlayer();
                                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                        @Override
                                        public void onCompletion(MediaPlayer mp) {
                                                    mediaPlayer.stop();
                                                    mediaPlayer.reset();

                                        }
                                    });

                                        mediaPlayer.setDataSource(baihat);
                                        mediaPlayer.prepare();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    mediaPlayer.start();
                    TimeSong();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimsong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
}