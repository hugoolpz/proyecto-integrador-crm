package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.modelo.Proyecto
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Tarea
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListadoProyectosVM: ViewModel() {
    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    private val _proyectos = MutableLiveData<List<Proyecto>>()
    val proyectos: LiveData<List<Proyecto>> = _proyectos

    private val _nombreTarea = MutableLiveData<String>()
    val nombreTarea: LiveData<String> = _nombreTarea

    fun cambiarInputs(nombreTarea: String) {
        _nombreTarea.value = nombreTarea
    }

    suspend fun getProyectos() {
        _cargando.value = true
        try {
            val result = api.getProyectos()
            if (result.isSuccessful) {
                val response: RespuestaApi<List<Proyecto>> = result.body()!!
                if (response.exito == true) {
                    _proyectos.value = response.datos!!
                }
            }
        } catch (e: Exception) {
            Log.e("ListadoProyectosVM", "Error en getProyectos()", e)
        }
        _cargando.value = false
    }

    fun agregarTarea(pid: String, nombre: String) {
        val tarea = Tarea(nombre, true, false)
        viewModelScope.launch {
            try {
                val result = api.agregarTarea(pid, tarea)
            } catch (e: Exception) {
                Log.e("ListadoProyectosVM", "Error en agregarTarea()", e)
            }
        }
    }

    fun deleteProyecto(uid: String) {
        viewModelScope.launch {
            try {
                val result = api.deleteProyecto(uid)
            } catch (e: Exception) {
                Log.e("ListadoProyectosVM", "Error en deleteProyecto()", e)
            }
        }
    }
}