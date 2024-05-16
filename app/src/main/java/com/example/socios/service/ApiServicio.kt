package com.example.socios.service

import com.example.socios.modelo.Respuesta
import com.example.socios.modelo.Usuario
import com.example.socios.modelo.UsuarioLogin
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServicio {
    @POST("route/usuario_duoc_almacenar")
    suspend fun usuarioAlmacenar(@Body usuario: Usuario): Response<List<Respuesta>>

    @POST("route/usuario_duoc_login")
    suspend fun usuarioLogin(@Body usuario: UsuarioLogin): Response<List<Respuesta>>
}