package com.example.rmfxaplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    context: Context?,
    ) : SQLiteOpenHelper(
    context,
    "moviles", //nombre BDD
    null,
    1
)   {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
                CREATE TABLE ENTRENADOR(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre VARCHAR (50),
                descripcion VARCHAR(50)
                )
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearEntrenador(
            nombre: String,
            descripcion: String
        ): Boolean{
            val basedatosEscritura = writableDatabase
            val valorAGuardar = ContentValues()
            valorAGuardar.put("nombre", nombre)
            valorAGuardar.put("descripcion", descripcion)
            val resultadoGuardar = basedatosEscritura
                .insert(
                    "ENTRENADOR", //Nombre tabla
                null,
                    valorAGuardar // valores
                )
            basedatosEscritura.close()
            return if (resultadoGuardar.toInt()== -1) false else true
        }

    fun eliminarEntrenadorFormulario(id: Int): Boolean{
        // val conexionEscrita = this.writableDatabase
        val conexionEscritura = writableDatabase
        //"SELECT * FROM ENTRENADOR WHERE ID = ?"
        // arrayOf(
        //      id.toString()
        //          )
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR", // Nombre tabla
                "id=?", // Consulta Where
                arrayOf(
                    id.toString()
                ) // Parametros
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descripcion", descripcion)
        val resultadoActualizado = conexionEscritura
            .update(
                "ENTRENADOR", // Nombre tabla
                valoresAActualizar, //Valores a actualizar
                "id=?", // Clausura Where
            arrayOf(
                idActualizar.toString()
            )//Parametros clausura where
            )
        conexionEscritura.close()
        return if (resultadoActualizado == -1) false else true
    }

    fun consultarEntrenadorPorId(id: Int): BEntrenador {
        //val baseDatosLectura = this.readableDatabase
        val baseDatosLectura = readableDatabase
        val scriptConsultaUsuario = "SELECT * FROM ENTRENADOR WHERE ID = ?"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultaUsuario, // Consulta
        arrayOf(
            id.toString()
        )// Parametros consulta
        )
        // Logica busqueda
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val usuarioEncontrado = BEntrenador(0, "","")
        val arreglo = arrayListOf<BEntrenador>()
        if (existeUsuario) {
            do {
                val id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) // columna indice 1 -> Nombre
                val descripcion =
                    resultadoConsultaLectura.getString(2) // Columna indice 2 -> DESCRIPCION
                if (id != null) { // Aqui crear el usuario
                    usuarioEncontrado.id = id
                    usuarioEncontrado.nombre = nombre
                    usuarioEncontrado.descripcion = descripcion
                    arreglo.add(usuarioEncontrado)
                }
            } while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        return usuarioEncontrado
    }

}


