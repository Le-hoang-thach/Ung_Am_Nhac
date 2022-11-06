package com.example.myapplication.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.myapplication.Model.Album;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



import android.content.Intent;

import com.example.myapplication.Activity.DanhsachbaihatActivity;



public class AlbumAddapter extends RecyclerView.Adapter<AlbumAddapter.ViewHolder>  {
        Context context;
        ArrayList<Album> mangalbumbaihat;

    public AlbumAddapter(Context context, ArrayList<Album> mangalbumbaihat) {
        this.context = context;
        this.mangalbumbaihat = mangalbumbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = mangalbumbaihat.get(position);
        holder.txtcasialbum.setText(album.getTenCaSi());
        holder.txtenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imghinhanhalbum);

    }

    @Override
    public int getItemCount() {
        return mangalbumbaihat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhanhalbum;
        TextView txtenalbum, txtcasialbum;
        public  ViewHolder(View itemView){
            super(itemView);
            imghinhanhalbum = itemView.findViewById(R.id.imageviewalbum);
            txtenalbum = itemView.findViewById(R.id.textviewtenalbum);
            txtcasialbum = itemView.findViewById(R.id.textviewcasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("album", mangalbumbaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
