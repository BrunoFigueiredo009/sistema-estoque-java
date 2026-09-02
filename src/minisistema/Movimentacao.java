package minisistema;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {

    private int quantidade;
    private LocalDateTime data;
    private Produto produto;
    private TipoMovimentacao tipo;

    public Movimentacao( Produto produto, int quantidade, LocalDateTime data, TipoMovimentacao tipo){

        this.quantidade = quantidade;
        this.data = data;
        this.produto = produto;
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }
    java.time.format.DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public String toString() {
        return ("Produto: "+produto+" - Quantidade: "+ quantidade + " - Data: "+data.format(formato)+" - Tipo: "+tipo);
    }
}
