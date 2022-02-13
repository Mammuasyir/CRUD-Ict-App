package com.example.crudapplication.api

import com.example.crudapplication.model.ResponseGetKategori
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("kategori")
    fun getKategori(): Call<ResponseGetKategori>
}