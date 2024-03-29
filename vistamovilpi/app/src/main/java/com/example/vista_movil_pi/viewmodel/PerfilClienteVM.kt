package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vista_movil_pi.modelo.Cliente
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Usuario
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api

class PerfilClienteVM: ViewModel() {
    private val _cliente = MutableLiveData<Cliente>()
    val cliente : LiveData<Cliente> = _cliente

    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    suspend fun ObtenerDatosClienteElegido(uid: String, indice: Int) {
        _cargando.value = true
        try {
            val result = api.getClientes(uid)
            Log.d("result de getClientes", result.toString())
            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
                if (response.exito == true){
                    Log.d("Datos clientes", response.datos.toString())
                    _cliente.value = response.datos?.clientes?.get(indice)!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getClientes()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }
}