package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api

class FacturaVM: ViewModel() {
    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _factura = MutableLiveData<Factura>()
    val factura: LiveData<Factura> = _factura

    suspend fun getFactura(id: String) {
        _cargando.value = true
        try {
            val result = api.getFactura(id)
            if (result.isSuccessful) {
                val response: RespuestaApi<Factura> = result.body()!!
                if (response.exito == true){
                    Log.d("Datos facturas", response.datos.toString())
                    _factura.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getFactura()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }
}