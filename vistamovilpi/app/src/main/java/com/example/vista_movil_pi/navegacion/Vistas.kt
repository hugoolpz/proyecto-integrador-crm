package com.example.vista_movil_pi.navegacion

sealed class Vistas(val ruta: String) {
    object ListadoFacturas: Vistas("ListadoFacturas")
    object FormFactura: Vistas("FormFactura")
        object Factura: Vistas("Factura")

}