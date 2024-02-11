package com.example.vista_movil_pi.modelo

data class Factura(
    val concepto: String,
    val completada: Boolean,
    val descripcion: String,
    val datosEmisor: Usuario,
    val numero: String,
    val fecha: String,
    val baseImp: Double,
    val datosReceptor: Usuario
) {
    val _id: String = ""
}