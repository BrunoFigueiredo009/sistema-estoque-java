package minisistema;

// Contrato para classes que podem realizar uma venda.
// A classe não precisa ser Produto para implementar Vendavel.

public interface Vendavel {
     boolean vender(int quantidade);

}
