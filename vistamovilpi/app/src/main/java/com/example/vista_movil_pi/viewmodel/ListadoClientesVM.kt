package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Usuario
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit

class ListadoClientesVM {
    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _clientes = MutableLiveData<List<Usuario>>()
    val clientes: LiveData<List<Usuario>> = _clientes

    suspend fun getClientes(uid: String) {
        _cargando.value = true
        try {
            val result = InstanciaRetrofit.RetrofitInstance.api.getClientes(uid)
            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
                if (response.exito == true){
                    Log.d("Datos clientes", response.datos.toString())
                    _clientes.value = response.datos?.clientes!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getClientes()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }
}