package com.example.vista_movil_pi.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.vista_movil_pi.modelo.Proyecto
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch

class ProyectoVM: ViewModel() {

    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _subtitulo = MutableLiveData<String>()
    val subtitulo: LiveData<String> = _subtitulo

    private val _descripcion = MutableLiveData<String>()
    val descripcion: LiveData<String> = _descripcion

    fun cambiarInputs(nombre: String, subtitulo: String, descripcion: String) {
        _nombre.value = nombre
        _subtitulo.value = subtitulo
        _descripcion.value = descripcion
    }

    fun crearProyecto(nombre: String, subtitulo: String, descripcion: String) {
        val proyecto = Proyecto(nombre, subtitulo, descripcion, false, emptyList(), emptyList())
        viewModelScope.launch {
            try {
                val result = api.postProyecto(proyecto)
                Log.d("Resultado proyecto: ", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Proyecto> = result.body()!!
                    if (response.exito == true) {

                    }
                }
            } catch (ex: Exception) {
                Log.d("Fallo al crear nuevo proyecto", ex.message.toString())
            }
        }
    }

}