package com.example.socios.service

import com.example.socios.modelo.Producto
import com.example.socios.modelo.Respuesta
import com.example.socios.modelo.Usuario
import com.example.socios.modelo.UsuarioLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServicio {
    @POST("route/usuario_duoc_almacenar")
    suspend fun usuarioAlmacenar(@Body usuario: Usuario): Response<List<Respuesta>>

    @POST("route/usuario_duoc_login")
    suspend fun usuarioLogin(@Body usuario: UsuarioLogin): Response<List<Respuesta>>

    @POST("route/producto_duoc_almacenar")
    suspend fun crearProducto(@Body producto: Producto): Response<List<Respuesta>>

}