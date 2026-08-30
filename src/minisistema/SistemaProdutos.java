package minisistema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

 public class SistemaProdutos {
     private ArrayList<Produto> produto = new ArrayList<>();

     public boolean numeroMaiorZero(double valor) {
         return valor > 0;


     }

     public boolean verificaSubMenu(int valor, int maximo){

         return valor >= 0 && valor <= maximo;
     }

     public boolean verificaNumeroMenu(int a, int maximo) {
         if(a < 0 || a > maximo) {
             return false;
         }else{
             return true;
         }
     }

     public void cadastraProduto(Produto alimento) {
         produto.add(alimento);
     }

     public boolean listaVazia() {
         return produto.isEmpty();
     }

     public Produto buscarProduto(String nome) {
         for (int i = 0; i < produto.size(); i++) {
             if (produto.get(i).getNome().equals(nome)) {
                 return produto.get(i);
             }
         }
         return null;
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
         int soma = 0;
         for (int i = 0; i < produto.size(); i++) {
             soma += produto.get(i).getEstoque();
         }
         return soma;
     }

     public boolean venderProduto(String nome, int quantidade) {
         Produto encontrado = buscarProduto(nome);

             if (encontrado instanceof Vendavel v){
                 //Ja confirmei que a encontrado é vendavel. Agora quero trata-la como vendavel

                 return v.vender(quantidade);
             }

         return false;

     }

     public boolean removerProdutos(String nome) {
         Produto encontrado = buscarProduto(nome);
         if (encontrado != null){
             return produto.remove(encontrado);

         }return false;
     }

     public void listarAlimentosVencidos() {
         boolean encontrouVencido = false;

         for(int i = 0; i < produto.size(); i++){
            Produto p = produto.get(i);

            if(p instanceof Alimento a){
                if (a.estaVencido()){
                    System.out.println(a.getNome());
                     encontrouVencido = true;

                    }
                }
            }
         if (!encontrouVencido){
             System.out.println("Nenhum alimento vencido");
         }
         }

     public void listarEstoqueBaixo(){
         boolean listaEstoque = false;
         for(int i = 0; i< produto.size(); i++){
             Produto p = produto.get(i);
             if(p.getEstoque()<10){
                 System.out.println(p.getNome());
                 listaEstoque = true;
             }
         }
         if(!listaEstoque){
             System.out.println("Nenhum produto com estoque baixo");
         }
     }

     public void produtosAcimaDePreco(double valor){
         boolean verificaAcimaPreco = false;
         for(int i =0; i < produto.size(); i++){
             Produto p = produto.get(i);
             if(p.getPreco() > valor){
                 System.out.println(p.getNome() + " - R$"+ p.getPreco());
                 verificaAcimaPreco = true;
             }
         }
         if(!verificaAcimaPreco){
             System.out.println("Nenhum produto informado está acima do valor");
         }
     }

     public Produto buscaMaisCaro(){
         if(produto.isEmpty()){
             return null;
         } else {
             Produto maisCaro = produto.get(0);
             for(int i = 0; i < produto.size(); i++){
                 Produto p = produto.get(i);
                 if(p.getPreco() > maisCaro.getPreco()){
                     maisCaro = p;
                 }
             }
             return maisCaro;
         }

     }

     public double calculaValorTotal(){
         double valorTotal = 0;

            for(int i = 0; i < produto.size(); i++){
                 Produto p = produto.get(i);
                 valorTotal += p.getPreco()*p.getEstoque();
             }

         return valorTotal;
         }

     public ArrayList<Produto> buscaProdutosSemEstoque(){
         ArrayList<Produto> semEstoque = new ArrayList<>() ;
         for(int i = 0; i<produto.size(); i++){
             Produto p = produto.get(i);

             if(p.getEstoque() == 0){
                 semEstoque.add(p);
             }

         }return semEstoque;
     }


 }
