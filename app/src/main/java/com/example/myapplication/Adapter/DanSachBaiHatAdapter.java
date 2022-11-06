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

public class DanSachBaiHatAdapter extends  RecyclerView.Adapter<DanSachBaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<Baihat> mangbaihat;
    public DanSachBaiHatAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Baihat baihat = mangbaihat.get(position);
        holder.txtcasi.setText(baihat.getCaSi());
        holder.txttenbaihat.setText(baihat.getTenBaiHat());
        holder.txtindex.setText(position + 1 + "");


    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
          TextView txtindex;
          TextView txttenbaihat;
          TextView txtcasi;
                ImageView imgluotthich;
                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    txtcasi = itemView.findViewById(R.id.textviewtencasi);
                    setTxtindex(itemView.findViewById(R.id.textviewdanhsachindex));
                    txttenbaihat = itemView.findViewById(R.id.textviewtenbaihat);
                    imgluotthich = itemView.findViewById(R.id.imgviewchonluotthichdanhsachbaihat);
                    imgluotthich.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imgluotthich.setImageResource(R.drawable.love_tim);
                            DataService dataService = APIService.getService();
                            Call<String > callback = dataService.UpdateLuotThich("1",mangbaihat.get(getPosition()).getIdBaiHat());
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
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PlayNhacActivity.class);
                            intent.putExtra("cakhuc",mangbaihat.get(getPosition()));
                            context.startActivity(intent);
                        }
                    });
                }

        public TextView getTxtindex() {
            return txtindex;
        }

        public void setTxtindex(TextView txtindex) {
            this.txtindex = txtindex;
        }
    }
}
