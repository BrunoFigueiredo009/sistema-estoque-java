package minisistema.dao;

import minisistema.Categoria;
import minisistema.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public Categoria buscarPorId(int id) throws SQLException {

        String sql = """
                SELECT id, nome
                FROM categoria
                WHERE id = ?
                """;

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {
                    int idCategoria = resultado.getInt("id");
                    String nome = resultado.getString("nome");

                    return new Categoria(idCategoria, nome);
                }
            }
        }

        return null;
    }

    public List<Categoria> listarTodas() throws SQLException {

        String sql = """
            SELECT id, nome
            FROM categoria
            ORDER BY nome
            """;

        List<Categoria> categorias = new ArrayList<>();

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()
        ) {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");

                Categoria categoria = new Categoria(id, nome);

                categorias.add(categoria);
            }
        }

        return categorias;
    }


}