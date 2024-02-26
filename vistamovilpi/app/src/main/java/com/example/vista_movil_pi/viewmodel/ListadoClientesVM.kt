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

    private val _clientes = MutableLiveData<MutableList<Cliente>>()
    val clientes: LiveData<MutableList<Cliente>> = _clientes

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    private val _codCliente = MutableLiveData<String>()
    val codCliente: LiveData<String> = _codCliente

    fun CambiarInputs(cod: String){
        _codCliente.value = cod
        _botonActivo.value = cod.isNotEmpty()
    }

    suspend fun getClientes(uid: String) {
        _cargando.value = true
        try {
            val result = InstanciaRetrofit.RetrofitInstance.api.getClientes(uid)
            Log.d("result de getClientes", result.toString())
            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
                if (response.exito == true){
                    Log.d("Datos clientes", response.datos.toString())
                    _clientes.value = (response.datos?.clientes as MutableList<Cliente>?)!!
                }
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en getClientes()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }

    suspend fun anadirCliente(uid: String, idCliente: String){
        _cargando.value = true
        try {
            val result = api.getClientes(idCliente)
            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
                if (response.exito == true){
                    var yo: Usuario? = null
                    if (response.datos != null){
                        val nuevoCliente = listOf(Cliente(response.datos._id))
                        yo = Usuario(nuevoCliente)
                    }
                    try {
                        val result = yo?.let { api.putCliente(uid, it) }
                        val response: RespuestaApi<Usuario> = result?.body()!!
                        if (response.exito == true){
                            _cargando.value = false
                        }
                    } catch (e: Exception) {
                        Log.e("ViewModel", "Error en añadirCliente()", e)
                    }
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
            //Creamos una nueva lista de clientes sin el cliente elegido
            val nuevosClientes = lista.filter { it._id != idClienteElegido }

            //Asignamos la nueva lista de clientes al usuario
            val usuarioModificado = Usuario(nuevosClientes)

            val result = api.removeCliente(uid, usuarioModificado)

            if (result.isSuccessful) {
                val response: RespuestaApi<Usuario> = result.body()!!
            } else {
                Log.e("ViewModel", "Error en la solicitud al servidor: ${result.message()}")
            }
        } catch (e: Exception) {
            Log.e("ViewModel", "Error en eliminarCliente()", e)
        }
        navController.navigate(Vistas.ListadoClientes.ruta + "?uid=" + uid)
        _cargando.value = false
    }

}