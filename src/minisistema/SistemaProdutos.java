package minisistema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaProdutos {
    private ArrayList<Produto> produto = new ArrayList<>();

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



    public ArrayList<Produto> produtosCadastrados() {
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

    public boolean venderProduto(String nome, int quantidade) {
        Produto encontrado = buscarProduto(nome);

        if (encontrado instanceof Vendavel v) {
            //Ja confirmei que a encontrado é vendavel. Agora quero trata-la como vendavel

            return v.vender(quantidade);
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
        if(produto.isEmpty()){
            return null;
        }

        Produto menorEstoque = produto.get(0);
        if(menorEstoque.getEstoque() == 0){
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

    public ArrayList<Produto> buscaProdutosSemEstoque() {
        ArrayList<Produto> semEstoque = new ArrayList<>();
        for(Produto p : produto){
            if(p.getEstoque() == 0){
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
}