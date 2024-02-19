package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api

class ListadoFacturasVM: ViewModel() {
    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _facturas = MutableLiveData<List<Factura>>()
    val facturas: LiveData<List<Factura>> = _facturas

    suspend fun getFacturas(uid: String) {
        _cargando.value = true
        try {
            val result = api.getFacturas(uid)
            if (result.isSuccessful) {
                val response: RespuestaApi<List<Factura>> = result.body()!!
                if (response.exito == true){
                    Log.d("Datos facturas", response.datos.toString())
                    _facturas.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getFacturas()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }
}