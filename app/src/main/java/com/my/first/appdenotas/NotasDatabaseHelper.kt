package com.my.first.appdenotas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NotasDatabaseHelper (context: Context) : SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "notas.do"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "notas"
        private const val COLUM_ID = "id"
        private const val COLUMN_TITLE = "titulo"
        private const val COLUMN_DESCRIPTION = "descripcion"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (${COLUM_ID} INTEGER PRIMARY KEY," +
                "$COLUMN_TITLE TEXT, $COLUMN_DESCRIPTION TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       val dropTableQUery = "Drop TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQUery)
        onCreate(db)
    }

    fun inserNota(nota : Nota){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, nota.titulo)
            put(COLUMN_DESCRIPTION, nota.descripcion)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllNotas() : List<Nota> {
        val listaNotas = mutableListOf<Nota>()
        val db = readableDatabase
        val querry = "SELECT + FROM $TABLE_NAME"
        val cursor = db.rawQuery(querry, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUM_ID))
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val descripcion = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION))

            val nota = Nota(id, titulo, descripcion)
            listaNotas.add(nota)
        }
        cursor.close()
        db.close()
        return listaNotas
    }



}