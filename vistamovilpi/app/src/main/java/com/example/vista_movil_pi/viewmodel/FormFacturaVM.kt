package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.vista_movil_pi.modelo.Cliente
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.modelo.Proyecto
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Usuario
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class FormFacturaVM: ViewModel() {
    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _clientes = MutableLiveData<MutableList<Cliente>>()
    val clientes: LiveData<MutableList<Cliente>> = _clientes

    private val _remitente = MutableLiveData<String>()
    val remitente: LiveData<String> = _remitente

    private val _remitenteElegido = MutableLiveData<Cliente>()
    val remitenteElegido: MutableLiveData<Cliente> = _remitenteElegido

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    private val _concepto = MutableLiveData<String>()
    val concepto : LiveData<String> = _concepto

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    private val _fecha = MutableLiveData<String>()
    val fecha: LiveData<String> = _fecha

    private val _baseImponible = MutableLiveData<String>()
    val baseImponible: LiveData<String> = _baseImponible

    fun CambiarInputs(concepto: String, descripcion: String, fecha: String, baseImponible: String, remitenteElegido: Cliente) {
        _concepto.value = concepto
        _descripcion.value = descripcion
        _fecha.value = fecha
        _baseImponible.value = baseImponible
        _remitenteElegido.value = remitenteElegido

        _botonActivo.value = concepto.isNotEmpty() &&
                descripcion.isNotEmpty() &&
                fecha.isNotEmpty() &&
                baseImponible.isNotEmpty() &&
                remitenteElegido != null
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
            Log.e("ViewModel", "Error en postFactura()", e)
            // Maneja los errores aquí
        }
        _cargando.value = false
    }

    suspend fun postFactura(uid: String, factura: Factura, navController: NavController) {
        viewModelScope.launch {
            try {
                val result = api.postFactura(factura)
                Log.d("Resultado proyecto: ", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Factura> = result.body()!!
                    if (response.exito == true) {
                        navController.navigate(Vistas.ListadoFacturas.ruta + "?uid=" + uid)
                    }
                }
            } catch (ex: Exception) {
                Log.d("Fallo al crear nuevo factura", ex.message.toString())
            }
            navController.navigate(Vistas.ListadoFacturas.ruta + "?uid=" + uid)
        }
    }
}