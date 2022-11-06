package com.example.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Activity.DanhsachbaihatActivity;
import com.example.myapplication.Activity.DanhsachcacplaylistActivity;
import com.example.myapplication.Model.Playlist;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DanhsachcaplaylistAdapter extends  RecyclerView.Adapter<DanhsachcaplaylistAdapter.ViewHolder>{
    Context context;
    ArrayList<Playlist> mangplaylist;

    public DanhsachcaplaylistAdapter(Context context, ArrayList<Playlist> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_play_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                Playlist playlist = mangplaylist.get(position);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imagehinhnen);
        holder.txttenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView imagehinhnen;
        TextView txttenplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imagehinhnen = itemView.findViewById(R.id.imageviewdanhsachcaplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhsachbaihatActivity.class);
                    intent.putExtra("itemplaylist",mangplaylist.get(getLayoutPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
