package com.example.myapplication.Activity;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.DanSachBaiHatAdapter;
import com.example.myapplication.Model.Album;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.Model.Quangcao;

import com.example.myapplication.Model.Playlist;
import com.example.myapplication.Model.Theloai;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatActivity extends AppCompatActivity {
    Playlist playlist;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    Quangcao quangcao;
    ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangbaihat;
    DanSachBaiHatAdapter danSachBaiHatAdapter;
    Theloai theLoai;
    Album album;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DateIntent();
        anhxa();
        init();
        if(quangcao != null && !quangcao.getTenBaiHat().equals("") ){
                setValueInView(quangcao.getTenBaiHat(),quangcao.getHinhBaiHat());
                GetDataQuangCao(quangcao.getIdQuangCao());
        }
        if (playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataPlaylist(playlist.getIdPlaylist());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")){
                setValueInView(theLoai.getTenTheLoai(),theLoai.getHinhTheLoai());
                GetDataTheLoai(theLoai.getIdTheloai());
        }
        if(album != null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danSachBaiHatAdapter = new DanSachBaiHatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai){
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                mangbaihat = (ArrayList<Baihat>) response.body();
                danSachBaiHatAdapter = new DanSachBaiHatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
            DataService dataService = APIService.getService();
            Call<List<Baihat>> callback = dataService.Getdanhsachbaihattheoplaylist(idplaylist);
            callback.enqueue(new Callback<List<Baihat>>() {
                @Override
                public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                    mangbaihat = (ArrayList<Baihat>) response.body();
                    danSachBaiHatAdapter = new DanSachBaiHatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                    recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                    recyclerViewDanhSachBaiHat.setAdapter(danSachBaiHatAdapter);
                    eventClick();
                }

                @Override
                public void onFailure(Call<List<Baihat>> call, Throwable t) {

                }
            });
    }

    private void setValueInView(String ten, String hinh)  {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }


    private void GetDataQuangCao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.Getdanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                 mangbaihat = (ArrayList<Baihat>) response.body();
                danSachBaiHatAdapter = new DanSachBaiHatAdapter(DanhsachbaihatActivity.this,mangbaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(DanhsachbaihatActivity.this));
                recyclerViewDanhSachBaiHat.setAdapter(danSachBaiHatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }




    private  void init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout =findViewById(R.id.collapsingtoolbar);
        toolbar = findViewById(R.id.toolbardanhsach);
        recyclerViewDanhSachBaiHat =findViewById(R.id.recyclerviewdanhsachbaihat);
        floatingActionButton =findViewById(R.id.flaotingactiobutton);
        imgdanhsachcakhuc = findViewById(R.id.danhsachhinhanhchon);

    }

    private void DateIntent() {
        Intent intent = getIntent();
        if (intent != null){
            if (intent.hasExtra("banner")){
               quangcao = (Quangcao) intent.getSerializableExtra("banner");

            }
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");

            }
            if (intent.hasExtra("idtheloai")){
                    theLoai = (Theloai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                    album = (Album) intent.getSerializableExtra("album");
            }
        }
    }
    private  void eventClick(){
            floatingActionButton.setEnabled(true);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DanhsachbaihatActivity.this,PlayNhacActivity.class);
                    intent.putExtra("cacbaihat",mangbaihat);
                    startActivity(intent);
                }
            });
    }
}