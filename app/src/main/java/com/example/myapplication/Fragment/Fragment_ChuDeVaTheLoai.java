package com.example.myapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activity.DanhsachbaihatActivity;
import com.example.myapplication.Activity.DanhsachtatcachudeActivity;
import com.example.myapplication.Model.ChuDe;

import com.example.myapplication.Model.Theloai;
import com.example.myapplication.Model.Theloaitrongngay;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ChuDeVaTheLoai extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtxemthemchudevatheloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai_today,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtxemthemchudevatheloai = view.findViewById(R.id.textviewxemthem);
        txtxemthemchudevatheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }
    private void GetData() {
        DataService dataservice = APIService.getService();
        Call<Theloaitrongngay>  callback =dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<Theloaitrongngay>() {
            @Override
            public void onResponse(Call<Theloaitrongngay> call, Response<Theloaitrongngay> response) {
               Theloaitrongngay theloaitrongngay = response.body();
               final  ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
               chuDeArrayList.addAll(theloaitrongngay.getChuDe());
                final  ArrayList<Theloai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theloaitrongngay.getTheloai());
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580 , 250);
                layout.setMargins(10,20,10,30);
                for (int i = 0 ; i<  (chuDeArrayList.size()) ; i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final  int finalJ = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });

                }

                for (int j = 0 ; j<  (theLoaiArrayList.size()) ; j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);

                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final  int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), DanhsachbaihatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                    horizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<Theloaitrongngay> call, Throwable t) {

            }
        });


    }

}
