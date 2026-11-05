package com.example.the_knife.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe di utilita per la gestione delle connessioni JDBC a PostgreSQL
 * per la piattaforma TheKnife.
 */
public class DBConnection {

    private static String host = "localhost";
    private static int port = 5432;
    private static String DBName = "TheKnife";
    private static String user = "postgres";
    private static String password = "12345";

    // Caricamento statico del driver postgres JDBC
    static {
        try{
            Class.forName("org.postgres.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Errore driver postgresSQL : JDBC non trovato");
            e.printStackTrace();
        }
    }

    /** Inizializza i parametri del DB con le credenziali inserite all'avvio di serverTK.
     *  @param host Indirizzo IP o hostname del DB (es. "localhost")
     *  @param port Porta del servizio PostgreSQL (es. 5432)
     *  @param DBName Nome del database
     *  @param user Username dell'utente PostgreSQL
     *  @param password Password dell'utente PostgreSQL
     */
    public static synchronized void init(String host, int port, String user, String password, String DBName) {
        DBConnection.host = host;
        DBConnection.port = port;
        DBConnection.user = user;
        DBConnection.password = password;
        DBConnection.DBName = DBName;
    }

    /** Crea e restituisce una nuova connessione attiva verso il database PostgreSQL.
     * @return Connection istanza di java.sql.Connection
     * @throws SQLException in caso di errore di autenticazione o rete
     */
    public static synchronized Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + DBName;
        return DriverManager.getConnection(url, user, password);
    }

}
