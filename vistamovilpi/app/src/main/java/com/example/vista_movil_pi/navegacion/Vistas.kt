package com.example.vista_movil_pi.navegacion

sealed class Vistas(val ruta: String) {
    object ListadoFacturas: Vistas("ListadoFacturas")
    object FormFactura: Vistas("FormFactura")
    object Factura: Vistas("Factura")
    object Login: Vistas("Login")
    object Registro: Vistas("Registro")
    object ListadoProyectos: Vistas("ListadoProyectos")
    object ListadoClientes: Vistas("ListadoClientes")
    object Opciones: Vistas("Opciones")
    object PerfilCliente: Vistas("PerfilCliente")

    object FormProyecto: Vistas("FormProyecto")

    object FormTarea: Vistas("FormTarea")

}