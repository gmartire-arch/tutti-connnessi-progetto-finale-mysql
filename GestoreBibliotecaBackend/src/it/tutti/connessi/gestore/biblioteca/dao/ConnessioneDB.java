package it.tutti.connessi.gestore.biblioteca.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {
    private static final String URL = "jdbc:mysql://nomedb:18981/nomeschema?ssl-mode=REQUIRED";
    private static final String USER = "admin";       // Cambia con il tuo utente MySQL
    private static final String PASSWORD = "password";   // Cambia con la tua password MySQL

    public static Connection getConnessione() {
        try {
            // Carica il driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Errore di connessione al Database: " + e.getMessage());
            return null;
        }
    }
}