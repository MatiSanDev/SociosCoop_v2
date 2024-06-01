package com.example.socios.modelo

class ProductoCredito {
    var idproducto: Int=0
    var nombreproducto: String=""
    var precio: Int=0
    constructor(nombreproducto:String,precio:Int){
        this.nombreproducto=nombreproducto
        this.precio=precio
    }
    constructor(){}
}