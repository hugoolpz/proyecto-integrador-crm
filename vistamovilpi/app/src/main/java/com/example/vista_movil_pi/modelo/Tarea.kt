package com.example.vista_movil_pi.modelo

data class Tarea (
    val nombre: String,
    val importante: Boolean,
    val estado: Boolean,
) {
    val _id: String = ""
}