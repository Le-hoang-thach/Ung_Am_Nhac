package com.example.myapplication.Fragment;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;



import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.Adapter.BannerAdapter;
import com.example.myapplication.Model.Quangcao;

import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
   View view;
   ViewPager viewPager;
   CircleIndicator circleIndicator;
   BannerAdapter bannerAdapter;
   Runnable runnable;
   Handler handler;
   int curentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        anhxa();
        GetData();
        return view;
    }
    private  void anhxa(){
        viewPager =  view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.indicatordefault);
    }
    public  void  GetData(){
        DataService dataservice = APIService.getService();
        Call<List<Quangcao>> callback = dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<Quangcao>>() {
            @Override
            public void onResponse(Call<List<Quangcao>> call, Response<List<Quangcao>> response) {
                ArrayList<Quangcao> banners  = (ArrayList<Quangcao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banners);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                            curentItem = viewPager.getCurrentItem();
                            curentItem ++;
                            if(curentItem >= viewPager.getAdapter().getCount()){
                                curentItem = 0;
                            }
                            viewPager.setCurrentItem(curentItem,true);
                            handler.postDelayed(runnable,4500);
                    }
                };
                handler.postDelayed(runnable,4500);
            }

            @Override
            public void onFailure(Call<List<Quangcao>> call, Throwable t) {

            }
        });
    }
}