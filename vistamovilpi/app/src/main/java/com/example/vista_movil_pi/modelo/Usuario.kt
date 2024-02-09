package com.example.vista_movil_pi.modelo

data class Usuario(
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val contra: String,
    val nif: String,
    val telefono: String,
    val direccion: String,
) {
}