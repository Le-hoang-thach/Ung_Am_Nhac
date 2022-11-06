package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Activity.PlayNhacActivity;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.R;
import com.example.myapplication.Service.APIService;
import com.example.myapplication.Service.DataService;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder> {
    Context context;
    ArrayList<Baihat> baihatArrayList;
    public BaihathotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_bai_hat_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Baihat baihat = baihatArrayList.get(position);
                holder.txtcasi.setText(baihat.getCaSi());
                holder.txtten.setText(baihat.getTenBaiHat());
                Picasso.with(context).load(baihat.getHinhAnhBaiHat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
            TextView txtten,txtcasi;
            ImageView imghinh, imgluotthich;
            public ViewHolder(View itemView){
                super(itemView);
                txtten = itemView.findViewById(R.id.textviewtenbaihathot);
                txtcasi = itemView.findViewById(R.id.textviewcasibaihathot);
                imghinh = itemView.findViewById(R.id.imageviewbaihathot);
                imgluotthich = itemView.findViewById(R.id.imageviewluotthich);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PlayNhacActivity.class);
                        intent.putExtra("cakhuc",baihatArrayList.get(getPosition()));
                        context.startActivity(intent);
                    }
                });
                imgluotthich.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            imgluotthich.setImageResource(R.drawable.love_tim);
                            DataService dataService = APIService.getService();
                            Call<String > callback = dataService.UpdateLuotThich("1",baihatArrayList.get(getPosition()).getIdBaiHat());
                            callback.enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {
                                    String ketqua = response.body();
                                    if (ketqua.equals("ok")){
                                        Toast.makeText(context, "Đã tim", Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(context, "Errol", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {

                                }
                            });
                            imgluotthich.setEnabled(false);
                    }
                });


            }
        }
}
