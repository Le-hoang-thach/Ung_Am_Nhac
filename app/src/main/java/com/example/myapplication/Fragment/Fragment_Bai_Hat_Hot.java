package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.BaihathotAdapter;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Bai_Hat_Hot  extends Fragment {
    View view;
    RecyclerView recyclerView;
    BaihathotAdapter baihatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_bai_hat_thich_nhat,container,false);
        recyclerView  = view.findViewById(R.id.recyclerviewbaihathot);

        GetData();
         return view;

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Baihat>> callback = dataService.GetBaihatduocthich();
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(Call<List<Baihat>> call, Response<List<Baihat>> response) {
                ArrayList<Baihat> baihatArrayList = (ArrayList<Baihat>) response.body();
                baihatAdapter = new BaihathotAdapter(getActivity(),baihatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(baihatAdapter);
            }

            @Override
            public void onFailure(Call<List<Baihat>> call, Throwable t) {

            }
        });
    }

}
