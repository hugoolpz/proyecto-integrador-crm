package com.example.vista_movil_pi.modelo

data class Factura(
    val concepto: String,
    val completada: Boolean,
    val descripcion: String,
    val datosEmisor: Cliente,
    val numero: String,
    val fecha: String,
    val baseImp: Double,
    val datosReceptor: Cliente
) {
    val _id: String = ""
}