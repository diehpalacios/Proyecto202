package com.proyectu1;

import com.proyectu1.utils.DatabaseConnection;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("Conexion realizada con exito a MariaDB en Docker");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
