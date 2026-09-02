package minisistema;

import java.text.DateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class SistemaProdutos {
    private List<Produto> produto = new ArrayList<>();

    public boolean numeroMaiorZero(double valor) {
        return valor > 0;
    }

    public boolean verificaSubMenu(int valor, int maximo) {

        return valor >= 0 && valor <= maximo;
    }

    public boolean verificaNumeroMenu(int a, int maximo) {
        if (a < 0 || a > maximo) {
            return false;
        } else {
            return true;
        }
    }

    public void cadastraProduto(Produto alimento) {
        produto.add(alimento);
    }

    public boolean listaVazia() {
        return produto.isEmpty();
    }

    public List<Produto> produtosCadastrados() {
        return produto;
    }

    public boolean atualizaPreco(String nome, double a) {

        Produto encontrado = buscarProduto(nome);

        if (encontrado != null) {
            return encontrado.setPreco(a);
        }

        return false;
    }

    public boolean atualizaEstoque(String nome, int a) {
        Produto encontrado = buscarProduto(nome);

        if (encontrado != null) {
            return encontrado.aumentaEstoque(a);
        }
        return false;
    }

    public int somaEstoque() {
        int contEstoque = 0;
        for (Produto p : produto) {
            contEstoque += p.getEstoque();
        }
        return contEstoque;
    }//for-each

    public boolean aumentaEstoque(String nome, int quantidade) {

        Produto encontrado = buscarProduto(nome);

        boolean resultadoAumentaEstoque;

        if (encontrado == null) {
            return false;
        }

        resultadoAumentaEstoque = encontrado.aumentaEstoque(quantidade);

        if (resultadoAumentaEstoque) {
            Movimentacao m = new Movimentacao(encontrado, quantidade, LocalDateTime.now(), TipoMovimentacao.ENTRADA);
            movimentacoes.add(m);
        }

        return resultadoAumentaEstoque;
    }

    public boolean venderProduto(String nome, int quantidade) {
        Produto encontrado = buscarProduto(nome);
        boolean resultadoVenda;
        if (encontrado instanceof Vendavel v) {
            //Ja confirmei que a encontrado é vendavel. Agora quero trata-la como vendavel
            resultadoVenda = v.vender(quantidade);
            if (resultadoVenda) {
                Movimentacao m = new Movimentacao(encontrado, quantidade, LocalDateTime.now(), TipoMovimentacao.VENDA);
                movimentacoes.add(m);

            }
            return resultadoVenda;
        }

        return false;
    }

    public boolean removerProdutos(String nome) {
        Produto encontrado = buscarProduto(nome);
        if (encontrado != null) {
            return produto.remove(encontrado);

        }
        return false;
    }

    public void listarAlimentosVencidos() {
        boolean encontrouVencido = false;

        for (Produto p : produto) {

            if (p instanceof Alimento a) {
                if (a.estaVencido()) {
                    System.out.println(a.getNome());
                    encontrouVencido = true;

                }
            }
        }
        if (!encontrouVencido) {
            System.out.println("Nenhum alimento vencido");
        }
    }//for-each

    public void produtosAcimaDePreco(double valor) {
        boolean verificaAcimaPreco = false;
        for (Produto p : produto) {
            if (p.getPreco() > valor) {
                System.out.println(p.getNome() + " - R$" + p.getPreco());
                verificaAcimaPreco = true;
            }
        }
        if (!verificaAcimaPreco) {
            System.out.println("Nenhum produto informado está acima do valor");
        }
    }//for-each

    public double calculaValorTotal() {
        double valorTotal = 0;

        for (Produto p : produto) {
            valorTotal += p.getEstoque() * p.getPreco();
        }

        return valorTotal;

    } //for-each

    public int quantidadeEstoqueBaixa() {
        int i = 0;

        for (Produto p : produto) {
            if (p.getEstoque() < 10) {
                i += 1;
            }
        }
        return i;

    }//for-each

    public Produto buscaMenorEstoque() {
        if (produto.isEmpty()) {
            return null;
        }

        Produto menorEstoque = produto.get(0);
        if (menorEstoque.getEstoque() == 0) {
            return menorEstoque;
        }

        for (Produto p : produto) {
            if (menorEstoque.getEstoque() > p.getEstoque()) {
                menorEstoque = p;
                if (menorEstoque.getEstoque() == 0) {
                    return menorEstoque;
                }
            }
        }
        return menorEstoque;
    } //for-each

    public List<Produto> buscaProdutosSemEstoque() {
        ArrayList<Produto> semEstoque = new ArrayList<>();
        for (Produto p : produto) {
            if (p.getEstoque() == 0) {
                semEstoque.add(p);
            }
        }
        return semEstoque;
    }//for-each

    public Produto buscaMaisCaro() {
        if (produto.isEmpty()) {
            return null;
        }
        Produto maisCaro = produto.get(0);
        for (Produto p : produto) {
            if (p.getPreco() > maisCaro.getPreco()) {
                maisCaro = p;
            }
        }
        return maisCaro;

    }//for-each

    public Produto buscarProduto(String nome) {
        for (Produto p : produto) {
            if (p.getNome().equals(nome)) {
                return p;
            }
        }
        return null;
    }//for-each

    public int lerInteiroValido(Scanner scanner) {
        boolean verificaNumero = false;
        int a = 0;
        do {
            try {
                a = scanner.nextInt();
                verificaNumero = true;
            } catch (InputMismatchException e) {
                System.out.println("Entre com um número válido");
                scanner.nextLine();
            }
        } while (!verificaNumero);
        return a;
    }

    public LocalDate dataProdutoValida(Scanner scanner) {
        boolean dataValida = false;
        LocalDate data = null;
        String dataString;
        do {
            try {
                java.time.format.DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                dataString = scanner.nextLine();
                data = LocalDate.parse(dataString, formato);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Entre com uma data valida: dd/mm/yyyy");

            }
        } while (!dataValida);
        return data;
    }

    public double lerDoubleValido(Scanner scanner) {
        double a = 0;
        boolean valida = false;
        do {
            try {
                a = scanner.nextDouble();
                valida = true;
            } catch (InputMismatchException e) {
                System.out.println("Número invalido");
                scanner.nextLine();
            }
        } while (!valida);
        return a;
    }

    private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();

    public List<Movimentacao> buscarHistoricoMovimentacoes() {

        return movimentacoes;
    }

    public boolean movimetacoesVazia() {
        return movimentacoes.isEmpty();
    }

    public List<Movimentacao> buscarHistoricoVendas() {

        List<Movimentacao> vendas = new ArrayList<>();

        for (Movimentacao m : movimentacoes) {
            if (m.getTipo() == TipoMovimentacao.VENDA) {
                vendas.add(m);
            }
        }

        return vendas;
    }

    public List<Movimentacao> buscarHistoricoEntradas() {

        List<Movimentacao> entradas = new ArrayList<>();

        for (Movimentacao m : movimentacoes) {
            if (m.getTipo() == TipoMovimentacao.ENTRADA) {
                entradas.add(m);
            }
        }

        return entradas;
    }

}