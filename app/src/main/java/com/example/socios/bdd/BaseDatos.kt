package com.example.socios.bdd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.socios.modelo.ProductoCredito

var BD = "baseDatos"

class BaseDatos(contexto: Context): SQLiteOpenHelper(
    contexto, BD, null, 1 ) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = "CREATE TABLE ProductoCredito(idproducto INTEGER PRIMARY KEY AUTOINCREMENT, nombreproducto VARCHAR(200), precio INTEGER)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Manejar actualizaciones de la base de datos
    }
    private fun mostrarMensaje(mensaje: String, contexto: Context) {
        // Mostrar el mensaje toast
        Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show()
        // Imprimir la respuesta en la terminal
        println("MSV: --------CREAR CREDITO BDD--------")
        println("MSV: $mensaje")
        println("MSV: --------FIN CREAR CREDITO BDD--------")
    }
    private val contexto: Context = contexto

    fun insertarDatos(productoCredito: ProductoCredito): String {
        val db = this.writableDatabase
        val contenedor = ContentValues()

        contenedor.put("nombreproducto", productoCredito.nombreproducto)
        contenedor.put("precio", productoCredito.precio)

        val resultado = db.insert("ProductoCredito", null, contenedor)
        val mensaje = if (resultado == -1L) {
            "Existió un error al solicitar"
        } else {
            "Credito solicitado"
        }
        mostrarMensaje(mensaje, contexto)
        return mensaje
    }

    fun listarProducto(): MutableList<ProductoCredito> {
        val lista: MutableList<ProductoCredito> = ArrayList()
        val db = this.readableDatabase
        val sql = "SELECT * FROM ProductoCredito"
        val resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                val prod = ProductoCredito()
                prod.idproducto = resultado.getInt(resultado.getColumnIndexOrThrow("idproducto"))
                prod.nombreproducto = resultado.getString(resultado.getColumnIndexOrThrow("nombreproducto"))
                prod.precio = resultado.getInt(resultado.getColumnIndexOrThrow("precio"))
                lista.add(prod)
            } while (resultado.moveToNext())
        }
        resultado.close()
        db.close()
        return lista
    }
    fun actualizarDatos(idproducto:String,nombreproducto:String,precio:Int):String{
        val db= this.writableDatabase
        var contenedor=ContentValues();
        contenedor.put("nombreproducto",nombreproducto)
        contenedor.put("precio",precio)
        var resultado = db.update("ProductoCredito",contenedor,"idproducto=?",arrayOf(idproducto))
        val mensaje = if (resultado > 0){
            "Solicitud actualizada"
        }else{
            "No se pudo actualizar"
        }
        mostrarMensaje(mensaje, contexto)
        return mensaje
    }
    fun borrarDatos(idproducto: String){
        val db= this.writableDatabase
        if (idproducto.length > 0){
            db.delete("ProductoCredito","idproducto=?", arrayOf(idproducto))
            mostrarMensaje("Solicitud eliminada", contexto)
        } else {
            mostrarMensaje("Error al eliminar", contexto)
        }
    }

}
