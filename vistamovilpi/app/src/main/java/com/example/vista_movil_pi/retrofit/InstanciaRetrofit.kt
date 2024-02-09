package com.example.vista_movil_pi.retrofit

import com.example.vista_movil_pi.repositorios.RepoApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstanciaRetrofit {
    object RetrofitInstance {
        private
        const val BASE_URL = "http://10.0.2.2:3000/"
        val api: RepoApi by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RepoApi::class.java)
        }
    }
}