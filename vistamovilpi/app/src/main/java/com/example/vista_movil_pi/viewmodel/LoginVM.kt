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

class LoginVM: ViewModel() {
    private val _correo = MutableLiveData<String>()
    val correo : LiveData<String> = _correo

    private val _contra = MutableLiveData<String>()
    val contra : LiveData<String> = _contra

    private val _botonActivo = MutableLiveData<Boolean>()
    val botonActivo : LiveData<Boolean> = _botonActivo

    private val _cargando = MutableLiveData<Boolean>()
    val cargando : LiveData<Boolean> = _cargando

    fun CambiarInputs(correo: String, contra: String){
        _correo.value = correo
        _contra.value = contra
        _botonActivo.value = EsCorreoValido(correo) && EsContraValida(contra)
    }

    private fun EsCorreoValido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    private fun EsContraValida(contra: String): Boolean = contra.length > 3

    fun IntentarInicioSesion(correo: String, contra: String, navController: NavController){
        _cargando.value = true
        viewModelScope.launch {
            try {
                val result = api.getUsuario(Usuario("","", correo, contra, "", "", "", emptyList()))
                Log.d("Resultado de inicio", result.toString())
                if (result.isSuccessful) {
                    val response: RespuestaApi<Usuario> = result.body()!!
                    if (response.exito == true){
                        _cargando.value = false
                        navController.navigate(Vistas.ListadoFacturas.ruta + "?uid=" + (response.datos?._id
                            ?: null))
                    }
                }
            } catch (ex: Exception) {
                Log.d("falloInicio", ex.message.toString())
            }
        }
        _cargando.value = false
    }
}