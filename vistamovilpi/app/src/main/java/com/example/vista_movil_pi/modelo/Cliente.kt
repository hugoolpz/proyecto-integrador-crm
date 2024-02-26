package com.example.vista_movil_pi.modelo

data class Cliente(
    val _id: String = "",
    val nombre: String,
    val apellidos: String,
    val correo: String,
    val contra: String,
    val nif: String,
    val telefono: String,
    val direccion: String,
    val clientes: List<String>
) {
    // Constructor primario con todos los parámetros excepto _id
    constructor(
        nombre: String,
        apellidos: String,
        correo: String,
        contra: String,
        nif: String,
        telefono: String,
        direccion: String,
        clientes: List<String>
    ) : this("", nombre, apellidos, correo, contra, nif, telefono, direccion, clientes)

    // Constructor secundario solo con _id
    constructor(_id: String) : this(_id, "", "", "", "", "", "", "", emptyList())
}
