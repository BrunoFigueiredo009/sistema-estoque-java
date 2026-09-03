package minisistema.dao;

import minisistema.ConexaoBanco;
import minisistema.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class ProdutoDAO {

    public void salvar(Produto produto) throws SQLException {

        String sql = """
                INSERT INTO produto (nome, preco, estoque, categoria_id)
                VALUES (?, ?, ?, ?)
                """;
        try (
                Connection conexao = ConexaoBanco.conectar();

                PreparedStatement stmt = conexao.prepareStatement(sql);
        ) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.setInt(4, produto.getCategoria().getId());

            stmt.executeUpdate();
        }

    }

    public void listar() throws SQLException {

        String sql = "SELECT * FROM produto";

        try (
                Connection conexao = ConexaoBanco.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet resultado = stmt.executeQuery()
        ) {

            while (resultado.next()) {

                String nome = resultado.getString("nome");
                double preco = resultado.getDouble("preco");
                int estoque = resultado.getInt("estoque");

                System.out.println(
                        nome + " - R$ " + preco + " - Estoque: " + estoque
                );
            }
        }
    }
}