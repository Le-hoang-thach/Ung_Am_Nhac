package com.example.myapplication.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dia_Nhac extends Fragment {
    View view;
    CircleImageView circleImageView;
    ObjectAnimator objectAnimator;
    @SuppressLint("ObjectAnimatorBinding")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageView = view.findViewById(R.id.imageviewcircl);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotatall",0f,360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return view;
    }
    public void Playnhac(String hinhanh) {
        Picasso.with(getContext()).load(hinhanh).into(circleImageView);
    }

}
