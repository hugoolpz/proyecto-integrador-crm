package com.example.vista_movil_pi.repositorios

import com.example.vista_movil_pi.modelo.RespuestaApi
import com.example.vista_movil_pi.modelo.Factura
import com.example.vista_movil_pi.modelo.Proyecto
import com.example.vista_movil_pi.modelo.Tarea
import com.example.vista_movil_pi.modelo.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RepoApi {
    @POST("api/usuarios/auth")
    suspend fun getUsuario(@Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @POST("api/usuarios")
    suspend fun postUsuario(@Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @POST("api/facturas")
    suspend fun postFactura(@Body factura: Factura): Response<RespuestaApi<Factura>>

    @GET("api/facturas/tuyas/{id}")
    suspend fun getFacturas(@Path("id") id: String): Response<RespuestaApi<List<Factura>>>

    @GET("api/facturas/{id}")
    suspend fun getFactura(@Path("id") id: String): Response<RespuestaApi<Factura>>

    @GET("api/usuarios/{id}")
    suspend fun getClientes(@Path("id") id: String): Response<RespuestaApi<Usuario>>
    @PUT("api/usuarios/cliente/{id}")
    suspend fun removeCliente(@Path("id") id: String, @Body usuario: Usuario): Response<RespuestaApi<Usuario>>
    @PUT("api/usuarios/{id}")
    suspend fun putCliente(@Path("id") id: String, @Body usuario: Usuario): Response<RespuestaApi<Usuario>>

    @POST("api/proyectos")
    suspend fun postProyecto(@Body proyecto: Proyecto): Response<RespuestaApi<Proyecto>>

    @GET("api/proyectos")
    suspend fun getProyectos(): Response<RespuestaApi<List<Proyecto>>>

    @PUT("api/proyectos/agregarTarea/{id}")
    suspend fun agregarTarea(
        @Path("id") id: String,
        @Body tarea: Tarea
    ): Response<RespuestaApi<Proyecto>>

    @DELETE("api/proyectos/{id}")
    suspend fun deleteProyecto(@Path("id") id: String): Response<RespuestaApi<Proyecto>>
}