package com.example.myapplication.Service;

import com.example.myapplication.Model.Album;
import com.example.myapplication.Model.Baihat;
import com.example.myapplication.Model.ChuDe;
import com.example.myapplication.Model.Quangcao;

import com.example.myapplication.Model.Theloai;
import com.example.myapplication.Model.Theloaitrongngay;
import com.example.myapplication.Model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {
        @GET("songbanenr.php")
        Call<List<Quangcao>> GetDataBanner ();

        @GET("playlistcurent.php")
        Call<List<Playlist>> GetPlaylistCurrentDay ();


        @GET("ChudeandTheloai.php")
        Call<Theloaitrongngay>  GetCategoryMusic();

        @GET("Album.php")
        Call<List<Album>>  GetAlbumHot();

        @GET("baihatduocthich.php")
        Call<List<Baihat>> GetBaihatduocthich();

        @FormUrlEncoded
        @POST("danhsachbaihat.php")
        Call<List<Baihat>> Getdanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

        @FormUrlEncoded
        @POST("baihatduocthich.php")
        Call<List<Baihat>> Getdanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);
        // con loi chua fix kip
        @GET("danhsachcacplaylist.php")
        Call<List<Playlist>>  GetDanhSachcacPlayList();

        @FormUrlEncoded
        @POST("danhsachbaihat.php")
        Call<List<Baihat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);

        @GET("tatcachude.php")
        Call<List<ChuDe>> GetAllChuDe();

        @FormUrlEncoded
        @POST("theloaitheochu.php")
        Call<List<Theloai>> GetTheloaitheochude(@Field("idtheloai") String idtheloai);


        @FormUrlEncoded
        @POST("danhsachbaihat.php")
        Call<List<Baihat>> GetDanhsachbaihattheoAlbum(@Field("idalbum") String idalbum);

        @FormUrlEncoded
        @POST("updateluotthich.php")
        Call<String> UpdateLuotThich(@Field("luotthich") String luotthich ,@Field("idbaihat") String idbaihat );



}
