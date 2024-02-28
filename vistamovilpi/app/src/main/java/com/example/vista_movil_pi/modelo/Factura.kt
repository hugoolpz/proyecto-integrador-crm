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

    // Constructor vacío
    constructor() : this("", false, "", Cliente(""), "", "", 0.0, Cliente(""))

    constructor(completada: Boolean) : this("", completada, "", Cliente(""), "", "", 0.0, Cliente(""))
}
