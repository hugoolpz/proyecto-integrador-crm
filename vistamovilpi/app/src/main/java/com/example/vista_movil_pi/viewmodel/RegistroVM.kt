package com.example.vista_movil_pi.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Usuario
import com.example.vista_movil_pi.navegacion.Vistas
import com.example.vista_movil_pi.retrofit.InstanciaRetrofit.RetrofitInstance.api
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistroVM: ViewModel() {
    private val _nombre = MutableLiveData<String>()
    val nombre: LiveData<String> = _nombre

    private val _apellidos = MutableLiveData<String>()
    val apellidos: LiveData<String> = _apellidos

    private val _nif = MutableLiveData<String>()
    val nif: LiveData<String> = _nif

    private val _telefono = MutableLiveData<String>()
    val telefono: LiveData<String> = _telefono

    private val _contrasena = MutableLiveData<String>()
    val contrasena: LiveData<String> = _contrasena

    private val _correo = MutableLiveData<String>()
    val correo: LiveData<String> = _correo

    private val _direccion = MutableLiveData<String>()
    val direccion: LiveData<String> = _direccion

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    fun CambiarInputs(nombre: String, apellidos: String, nif: String, telefono: String, contra: String, correo: String, direccion: String) {
        _nombre.value = nombre
        _apellidos.value = apellidos
        _nif.value = nif
        _telefono.value = telefono
        _contrasena.value = contra
        _correo.value = correo
        _direccion.value = direccion

        _botonActivo.value = EsNombreValido(nombre) &&
                EsApellidosValidos(apellidos) &&
                EsNifValido(nif) &&
                EsTelefonoValido(telefono) &&
                EsCorreoValido(correo) &&
                EsContraValida(contra) &&
                EsDireccionValida(direccion)
    }

    private fun EsNombreValido(nombre: String?): Boolean = !nombre.isNullOrEmpty()

    private fun EsApellidosValidos(apellidos: String?): Boolean = !apellidos.isNullOrEmpty()

    private fun EsNifValido(nif: String?): Boolean = !nif.isNullOrEmpty()

    private fun EsTelefonoValido(telefono: String?): Boolean = !telefono.isNullOrEmpty()

    private fun EsCorreoValido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    private fun EsContraValida(contra: String): Boolean = contra.length > 6

    private fun EsDireccionValida(direccion: String?): Boolean = !direccion.isNullOrEmpty()

    fun IntentarRegistrarse(nombre: String, apellidos: String, nif: String, telefono: String, contra: String, correo: String, direccion: String, navController: NavController){
        _cargando.value = true
        val usuario = Usuario(nombre, apellidos, correo, contra, nif, telefono, direccion, emptyList())
        viewModelScope.launch {
            try {
                val result = api.postUsuario(usuario)
                Log.d("Resultado del registro", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){
                        navController.navigate(Vistas.Login.ruta)
                    }
                }
            } catch (ex: Exception) {
                Log.d("fallo del registro", ex.message.toString())
            }
        }
    }
}