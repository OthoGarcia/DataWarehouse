/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhofoly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ogarcia
 */
public class Conexao {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
          "jdbc:mysql://localhost/games","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getDW() {
        try {
            return DriverManager.getConnection(
          "jdbc:mysql://localhost/dw","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection(Connection c) throws SQLException{
        c.close();
    }
}
