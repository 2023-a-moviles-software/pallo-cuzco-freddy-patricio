package com.freddywin.artistamusic_crud_sqlite

import android.annotation.SuppressLint
import android.content.ContentValues
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import android.database.sqlite.SQLiteDatabase
import java.sql.PreparedStatement

data class Genero(
    var idGeneoro: Int,
    var nombreGenero: String,
    var puntuacionGenero: Double,
    var fechaGenero: String,
    var esPopular: Boolean

)

data class Artista(
    var idArtista: Int,
    var nombreArtista: String,
    var puntuacionArtista: Double,
    var fechaArtista: String,
    var esActivo: Boolean,
    var id_genero: Int
)

class SQLiteHelper(private val SQLiteOpenHelperConexion: Connection) {

    init {
        crearTablas()
    }

    fun crearTablas() {
        //tabla Genero
        val scripSQLTablaGenero = """
          CREATE TABLE GENERO(
          idGenero INTEGER PRIMARY KEY,
          nombreGenero VARCHAR(50),
          puntuacionGenero DECIMAL(5.2),
          fechaGenero DATE,
          esPopular BOOLEAN        
          )
      """.trimIndent()

        //tabla artista
        val scripSQLTablaArtista = """
           CREATE TABLE Artista(
           idArtista INTEGER PRIMARY KEY,
           nombreArtista VARCHAR(50),
           puntuacionArtista DECIMAL(5.2),
           fechaArtista DATE,
           esActivo BOOLEAN,
           id_Genero integer,
           FOREIGN KEY(id_Genero) REFERENCES GENERO(idGenero)
           )
        """.trimIndent()

        val estado = SQLiteOpenHelperConexion.createStatement()
        estado.executeUpdate(scripSQLTablaGenero)
        estado.executeUpdate(scripSQLTablaArtista)
    }


    fun insertarGenero(genero: Genero): Boolean {
        val scripSqlInsert =
            "INSERT INTO GENERO (idGenero, nombreGenero, puntuacionGenero, fechaGenero, esPopular)" +
                    "VALUES (?, ?, ?, ?, ?)"
        val valoresAGuardar: PreparedStatement =
            SQLiteOpenHelperConexion.prepareStatement(scripSqlInsert)
        valoresAGuardar.setInt(1, genero.idGeneoro)
        valoresAGuardar.setString(2, genero.nombreGenero)
        valoresAGuardar.setDouble(3, genero.puntuacionGenero)
        valoresAGuardar.setString(4, genero.fechaGenero)
        valoresAGuardar.setBoolean(5, genero.esPopular)

        val resulAGuadar = valoresAGuardar.executeUpdate()
        valoresAGuardar.close()
        return resulAGuadar > 0
    }


    fun insertarArtista(artista: Artista): Boolean {
        val scripSqlInsert =
            "INSERT INTO ARTISTA (idArtista, nombreArtista, puntuacionArtista,fechaArtista, esActivo, id_genero)" +
                    "VALUES(?,?,?,?,?)"
        val valoresAGuardar: PreparedStatement =
            SQLiteOpenHelperConexion.prepareStatement(scripSqlInsert)
        valoresAGuardar.setInt(1, artista.idArtista)
        valoresAGuardar.setString(2, artista.nombreArtista)
        valoresAGuardar.setDouble(3, artista.puntuacionArtista)
        valoresAGuardar.setString(4, artista.fechaArtista)
        valoresAGuardar.setBoolean(5, artista.esActivo)
        valoresAGuardar.setInt(6, artista.id_genero)

        val resulAGuadar = valoresAGuardar.executeUpdate()
        valoresAGuardar.close()
        return resulAGuadar > 0
    }


    //funcion para mostrar Genero
    fun mostarGenero(id: Int): Genero? {
        val sqlMostar = "SELECT * FROM GENERO WHERE idGenero = ?"

        val preparedStatement: PreparedStatement =
            SQLiteOpenHelperConexion.prepareStatement(sqlMostar)
        preparedStatement.setInt(1, id)

        val resultSet = preparedStatement.executeQuery()

        if (resultSet.next()) {
            val genero = Genero(
                resultSet.getInt("idGenero"),
                resultSet.getString("nombreGenero"),
                resultSet.getDouble("puntuacionGenero"),
                resultSet.getString("fechaGenero"),
                resultSet.getBoolean("esPopular")
            )
            preparedStatement.close()
            resultSet.close()
            return genero
        } else {
            preparedStatement.close()
            resultSet.close()
            return null
        }
    }

    //Funciones eliminar
    fun eliminarGenero(id: Int): Boolean {
        val scripSqlBorrar = "DELETE FROM GENERO WHERE idGenero = ?"
        val preparedStatement: PreparedStatement =
            SQLiteOpenHelperConexion.prepareStatement(scripSqlBorrar)
        preparedStatement.setInt(1, id)

        val resultado = preparedStatement.executeUpdate()
        preparedStatement.close()
        return resultado > 0
    }
}
