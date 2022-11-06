package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.Adapter.DanhsachtheochudeAdapter;
import com.example.myapplication.Model.ChuDe;
import com.example.myapplication.Model.Theloai;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;


import android.os.Bundle;

import android.util.Log;
import android.view.View;


import android.content.Intent;


import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhsachtheloaiActivity extends AppCompatActivity {
    ChuDe chuDe;
    RecyclerView recyclerView;
    Toolbar toolbartheotheloai;
    DanhsachtheochudeAdapter danhsachtheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtheloai);
        GetIntent();
        init();
        GetData();

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Theloai>> calback = dataService.GetTheloaitheochude(chuDe.getIdChuDe());
        calback.enqueue(new Callback<List<Theloai>>() {
            @Override
            public void onResponse(Call<List<Theloai>> call, Response<List<Theloai>> response) {
                ArrayList<Theloai> mangtheloai = (ArrayList<Theloai>) response.body();
                danhsachtheochudeAdapter = new DanhsachtheochudeAdapter(DanhsachtheloaiActivity.this,mangtheloai);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhsachtheloaiActivity.this,2));
                recyclerView.setAdapter(danhsachtheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<Theloai>> call, Throwable t) {

            }
        });
    }

    private  void init(){
        recyclerView = findViewById(R.id.recylerviewtheloaitheochude);
        toolbartheotheloai = findViewById(R.id.toolbartheotheloai);
        setSupportActionBar(toolbartheotheloai);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheotheloai.setNavigationOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });
    }


    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");

        }
    }
}