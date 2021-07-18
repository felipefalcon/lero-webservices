package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {

    public static void main(String[] args) {
        System.out.println("Inicio PostgreSQLMySQL");
        try {
            Class.forName("org.postgresql.Driver");
            Connection conexao = DriverManager.getConnection(
                    "jdbc:postgresql://ec2-3-212-75-25.compute-1.amazonaws.com:5432/dadn1cddkhm83j?sslmode=require",
                    "plqutghdamllgm", "91c33fd5b27a61a5d01e872f66813058026eb512289d391605cb387057855093");
            PreparedStatement pstm = conexao
                    .prepareStatement("SELECT * FROM cesta");

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                System.out.println("Nome tabela: " + rs.getString("codigo"));
            }
            rs.close();
            pstm.close();
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fim PostgreSQLMySQL");
    }
}
