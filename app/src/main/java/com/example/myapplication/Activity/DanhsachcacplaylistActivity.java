package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.DanhsachcaplaylistAdapter;
import com.example.myapplication.Model.Playlist;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachcacplaylist;
    DanhsachcaplaylistAdapter danhsachcaplaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        anhxa();
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetDanhSachcacPlayList();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                danhsachcaplaylistAdapter = new DanhsachcaplaylistAdapter(DanhsachcacplaylistActivity.this,mangplaylist);
                recyclerViewdanhsachcacplaylist.setLayoutManager(new GridLayoutManager(DanhsachcacplaylistActivity.this,2));
                recyclerViewdanhsachcacplaylist.setAdapter(danhsachcaplaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });

    }

    private void  init(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Play List");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void anhxa() {
        toolbar = findViewById(R.id.tooldanhsachcacplaylist);
        recyclerViewdanhsachcacplaylist = findViewById(R.id.recyclerviewdanhsachcacplaylist);
    }
}