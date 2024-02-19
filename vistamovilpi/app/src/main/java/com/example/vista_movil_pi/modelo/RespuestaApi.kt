package com.example.vista_movil_pi.modelo


class RespuestaApi<T> {
    val exito: Boolean? = null

    val datos: T? = null

    override fun toString(): String {
        return "DataResponse(code=$exito, data=$datos)"
    }
}