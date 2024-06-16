package com.example.mdp_project

import android.util.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

object Utils {
    fun getFromSql(tableName: String, columnName: String, filter: String? = null): String {
        val sql = "SELECT $columnName FROM $tableName $filter"
        var result = ""
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver")

            // Establish a connection to the database
            val conn: Connection = DriverManager.getConnection(
                "jdbc:mariadb://192.168.1.34:3306/exampledb",
                "trial2",
                "trial"
            )
            Log.d("Database", "Connected to MariaDB")
            // Create a statement
            val stmt: Statement = conn.createStatement()

            // Execute a query
            val rs: ResultSet = stmt.executeQuery(sql)

            // Process the result set
            while (rs.next()) {
                result += rs.getString(columnName)
            }

            // Close the connection
            conn.close()
        } catch (e: Exception) {
            e.printStackTrace()
            result = "Error: ${e.message}"
        }
        return result
    }

    fun sendToSql(tableName: String, columnName: String, value: String){
        val sql = "INSERT INTO $tableName ($columnName) VALUES ('$value')"
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver")

            // Establish a connection to the database
            val conn: Connection = DriverManager.getConnection(
                "jdbc:mariadb://192.168.1.34:3306/exampledb",
                "trial2",
                "trial"
            )
            Log.d("Database", "Connected to MariaDB")
            // Create a statement
            val stmt: Statement = conn.createStatement()

            // Execute a query
            stmt.executeUpdate(sql)

            // Close the connection
            conn.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}