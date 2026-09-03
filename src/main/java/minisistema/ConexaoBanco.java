package minisistema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    public static Connection conectar() {

        String url = "jdbc:postgresql://localhost:5432/mini_sistema";
        String usuario = System.getenv("DB_USER");
        String senha = System.getenv("DB_PASSWORD");

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado ao banco com sucesso!");
            return conexao;

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}