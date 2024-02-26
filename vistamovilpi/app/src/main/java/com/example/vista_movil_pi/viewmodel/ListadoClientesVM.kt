package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.vista_movil_pi.modelo.Cliente
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Usuario
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api

class ListadoClientesVM {
    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _clientes = MutableLiveData<List<Cliente>>()
    val clientes: LiveData<List<Cliente>> = _clientes

    suspend fun getClientes(uid: String) {
        _cargando.value = true
        try {
            val result = InstanciaRetrofit.RetrofitInstance.api.getClientes(uid)
            Log.d("result de getClientes", result.toString())
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

    suspend fun eliminarCliente(uid: String, lista: List<Cliente>, idClienteElegido: String, navController: NavController) {
        _cargando.value = true
        try {
            // Crear una nueva lista de clientes sin el cliente elegido
            val nuevosClientes = lista.filter { it._id != idClienteElegido }

            // Asignar la nueva lista de clientes al usuario
            val usuarioModificado = Usuario(nuevosClientes)

            // Llamar a la API para actualizar el usuario en el servidor
            val result = api.putCliente(uid, usuarioModificado)

            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
            } else {
                // Manejar la respuesta del servidor si no es exitosa
                Log.e("ViewModel", "Error en la solicitud al servidor: ${result.message()}")
            }
        } catch (e: Exception) {
            // Manejar otros errores
            Log.e("ViewModel", "Error en eliminarCliente()", e)
        }
        navController.navigate(Vistas.ListadoClientes.ruta + "?uid=" + uid)
        _cargando.value = false
    }

}