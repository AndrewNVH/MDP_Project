package com.example.mdp_project

import android.util.Log
import java.sql.Connection
import java.sql.Date
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

object Utils {
    fun getFromSql(tableName: String, columnName: String, filter: String? = null): ArrayList<String> {
        val sql = if (filter == null) "SELECT $columnName FROM $tableName" else "SELECT $columnName FROM $tableName $filter"
        Log.d("Database", sql)
        var result = ArrayList<String>()
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
                result.add(rs.getString(columnName))
            }
            Log.d("Database", "Result: $result")

            // Close the connection
            conn.close()
        } catch (e: Exception) {
            e.printStackTrace()
//            result = "Error: ${e.message}"
            Log.d("Database", "Error: ${e.message}")
        }
        return result
    }

    fun getFromSqlFloat(tableName: String, columnName: String, filter: String? = null): ArrayList<Float> {
        val sql = if (filter == null) "SELECT $columnName FROM $tableName" else "SELECT $columnName FROM $tableName $filter"
        Log.d("Database", sql)
        val result = ArrayList<Float>()

        var conn: Connection? = null
        var stmt: Statement? = null
        var rs: ResultSet? = null

        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver")

            // Establish a connection to the database
            conn = DriverManager.getConnection(
                "jdbc:mariadb://192.168.1.34:3306/exampledb",
                "trial2",
                "trial"
            )
            Log.d("Database", "Connected to MariaDB")

            // Create a statement
            stmt = conn.createStatement()

            // Execute a query
            rs = stmt.executeQuery(sql)

            // Process the result set
            while (rs.next()) {
                result.add(rs.getFloat(columnName))
            }
            Log.d("Database", "Result: $result")

        } catch (e: SQLException) {
            e.printStackTrace()
            Log.d("Database", "SQL Error: ${e.message}")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            Log.d("Database", "Class Not Found Error: ${e.message}")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("Database", "Error: ${e.message}")
        } finally {
            // Close resources
            try {
                rs?.close()
                stmt?.close()
                conn?.close()
            } catch (e: SQLException) {
                e.printStackTrace()
                Log.d("Database", "Error closing resources: ${e.message}")
            }
        }
        return result
    }

    fun getFromSqlDate(tableName: String, columnName: String, filter: String? = null): ArrayList<Date> {
        val sql = if (filter == null) "SELECT $columnName FROM $tableName" else "SELECT $columnName FROM $tableName $filter"
        Log.d("Database", sql)
        var result = ArrayList<Date>()
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
                result.add(rs.getDate(columnName))
            }
            Log.d("Database", "Result: $result")

            // Close the connection
            conn.close()
        } catch (e: Exception) {
            e.printStackTrace()
//            result = "Error: ${e.message}"
            Log.d("Database", "Error: ${e.message}")
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

    fun sendUserToSql(user: String, pass: String){
        val sql = "INSERT INTO userlist VALUES ('$user', '$pass')"
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver")

            // Establish a connection to the database
            val conn: Connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/exampledb",
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
            MockDB.user.add(UserList(user, pass))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}