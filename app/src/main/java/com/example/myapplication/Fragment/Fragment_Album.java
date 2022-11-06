package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Adapter.AlbumAddapter;
import com.example.myapplication.Model.Album;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album extends Fragment {
    View view;
    RecyclerView recyclerViewAblum;
    TextView textViewAlbum;
    AlbumAddapter albumAddapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album,container,false);
        recyclerViewAblum = view.findViewById(R.id.recyclerviewalbum);
        textViewAlbum = view.findViewById(R.id.txtAlbumXemThem);

        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetAlbumHot();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
              albumAddapter = new AlbumAddapter(getActivity(),albumArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAblum.setLayoutManager(linearLayoutManager);
                recyclerViewAblum.setAdapter(albumAddapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });

    }
}
