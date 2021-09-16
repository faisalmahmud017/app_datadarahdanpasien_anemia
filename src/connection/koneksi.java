/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Faisal <faismud017@gmail.com>
 */
public class koneksi {

    public Connection con;
    public Statement stm;

    public void konek() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String DB = "jdbc:mysql://localhost/treatment";
            String user = "root";
            String pass = "";
            con = DriverManager.getConnection(DB, user, pass);
            stm = con.createStatement();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null,"Tidak ada koneksi","Error",JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "koneksi gagal " + e.getMessage());
            System.err.println("Koneksi gagal - " + e.getMessage());
        }
    }
}
