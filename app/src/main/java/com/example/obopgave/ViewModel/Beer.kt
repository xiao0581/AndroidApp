package com.example.obopgave.ViewModel


data class Beer(val id: Int,  val name: String,  val abv: Double) {
 constructor(name: String,  abv: Double) : this(-1, name,  abv)
    override fun toString(): String {
        return "Beer(id=$id,  name='$name', abv=$abv)"
    }

}
