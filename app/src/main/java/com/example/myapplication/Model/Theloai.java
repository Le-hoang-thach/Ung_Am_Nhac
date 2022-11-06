package com.example.myapplication.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Theloai implements Serializable {

    @SerializedName("IdTheloai")
    @Expose
    private String idTheloai;
    @SerializedName("IdKeyChuDe")
    @Expose
    private String idKeyChuDe;
    @SerializedName("TenTheLoai")
    @Expose
    private String tenTheLoai;
    @SerializedName("HinhTheLoai")
    @Expose
    private String hinhTheLoai;

    public String getIdTheloai() {
        return idTheloai;
    }

    public void setIdTheloai(String idTheloai) {
        this.idTheloai = idTheloai;
    }

    public String getIdKeyChuDe() {
        return idKeyChuDe;
    }

    public void setIdKeyChuDe(String idKeyChuDe) {
        this.idKeyChuDe = idKeyChuDe;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getHinhTheLoai() {
        return hinhTheLoai;
    }

    public void setHinhTheLoai(String hinhTheLoai) {
        this.hinhTheLoai = hinhTheLoai;
    }

}