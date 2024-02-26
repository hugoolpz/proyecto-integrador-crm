package com.example.vista_movil_pi.modelo

data class Proyecto (
    val nombre: String,
    val subtitulo: String,
    val descripcion: String,
    val estado: Boolean,
    val tareas: List<Tarea>,
    val integrantes: List<Cliente>
) {
    val _id: String = ""
}