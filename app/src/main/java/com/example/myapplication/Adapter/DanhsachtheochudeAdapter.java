package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activity.DanhsachtheloaiActivity;
import com.example.myapplication.Model.Theloai;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DanhsachtheochudeAdapter extends RecyclerView.Adapter<DanhsachtheochudeAdapter.ViewHolder> {
    Context context;
    ArrayList<Theloai> mangtheloaitheochude;

    public DanhsachtheochudeAdapter(Context context, ArrayList<Theloai> mangtheloaitheochude) {
        this.context = context;
        this.mangtheloaitheochude = mangtheloaitheochude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theo_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Theloai theloai = mangtheloaitheochude.get(position);
        Picasso.with(context).load(theloai.getHinhTheLoai()).into(holder.imghinhnen);
        holder.txttentheloai.setText(theloai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return mangtheloaitheochude.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
    ImageView imghinhnen;
    TextView txttentheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewthelaoitheochude);
            txttentheloai = itemView.findViewById(R.id.textviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =  new Intent(context, DanhsachtheloaiActivity.class);
                    intent.putExtra("idtheloai", mangtheloaitheochude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
