package minisistema;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {


    private String nomeProduto;
    private double precoProduto;
    private int quantidade;
    private int estoqueAposMovimentacao;
    private LocalDateTime data;
    private TipoMovimentacao tipo;

    public Movimentacao(Produto produto, int quantidade, LocalDateTime data, TipoMovimentacao tipo) {

        this.nomeProduto = produto.getNome();
        this.precoProduto = produto.getPreco();
        this.quantidade = quantidade;
        this.estoqueAposMovimentacao = produto.getEstoque();
        this.data = data;
        this.tipo = tipo;
    }
    public String getNomeProduto(){
        return nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }


    public LocalDateTime getData() {
        return data;
    }


    public TipoMovimentacao getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        return "Produto: " + nomeProduto +
                " - Quantidade: " + quantidade +
                " - Preço: " + precoProduto +
                " - Estoque após movimentação: " + estoqueAposMovimentacao +
                " - Data: " + data.format(formato) +
                " - Tipo: " + tipo;
    }
}
