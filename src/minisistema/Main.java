package minisistema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static void menu() {
    System.out.println("\n\n====== SISTEMA DE PRODUTOS ======");
    System.out.println("1 - Cadastrar produto");
    System.out.println("2 - Listar produtos");
    System.out.println("3 - Vender produto");
    System.out.println("4 - Ver estoque total");
    System.out.println("5 - Editar preco");
    System.out.println("6 - Adcionar estoque");
    System.out.println("7 - Remover produto");
    System.out.println("8 - Listar produtos vencidos");
    System.out.println("9- Produtos com estoque baixo");
    System.out.println("10 - Visualizar Produtos por determinados valores");
    System.out.println("11 - Mostrar Produto mais caro");
    System.out.println("12 - Busca por menor estoque");
    System.out.println("0 - Sair\n\n");
}

    static void subMenu(){
        System.out.println("\n\n====== CADASTRO ======");
        System.out.println("1 - Cadastrar Alimento");
        System.out.println("2 - Cadastrar Eletrônico");
        System.out.println("0 - Voltar");
    }

    public static void main(String[] args){
    SistemaProdutos sistema = new SistemaProdutos();
    Scanner scanner = new Scanner(System.in);


    int menuOp;

    do {
        menu();
        menuOp = scanner.nextInt();
        menuOp = sistema.verificaNumeroMenu(menuOp, scanner);
        String busca;
        int escolhasubmenu;

        if (menuOp == 1) {

                do{
                    String nome;
                    double valor;
                    int estoque;


                    subMenu();
                    escolhasubmenu = scanner.nextInt();

                    boolean verificaSubMenu = verificaSubMenu = sistema.verificaSubMenu(escolhasubmenu);

                    if(!verificaSubMenu){
                        System.out.print("\n\nNúmero inválido, entre com um número válido:\n\n");
                    }
                    else if(escolhasubmenu == 0){
                        System.out.println("\n\nVoltando ao menu\n\n");
                    }
                    else if(verificaSubMenu && escolhasubmenu == 1){
                            System.out.println("\n\n====== CADASTRO DE ALIMENTO ======");
                            scanner.nextLine();
                            System.out.println("Entre com o nome do alimento");
                            nome = scanner.nextLine();

                            System.out.println("Entre com o preco");
                            valor = scanner.nextDouble();

                            System.out.println("Entre com o estoque");
                            estoque = scanner.nextInt();
                            scanner.nextLine();
                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            System.out.println("Entre com a validade");
                            String validadeTexto = scanner.nextLine();

                            LocalDate validade = LocalDate.parse(validadeTexto, formato);

                            Alimento p = new Alimento(nome,valor,estoque,validade);
                            sistema.cadastraProduto(p);
                            System.out.println("\n\n====== Alimento cadastrado com sucesso ======\n\n");

                        }

                        else if(verificaSubMenu && escolhasubmenu == 2){
                            scanner.nextLine();
                            System.out.println("\n\n====== CADASTRO DE ELETRONICO ======");
                            System.out.println("Entre com o nome do Eletronico");
                            nome = scanner.nextLine();

                            System.out.println("Entre com o preco");
                            valor = scanner.nextDouble();

                            System.out.println("Entre com o estoque");
                            estoque = scanner.nextInt();

                            System.out.println("Entre com a garantia");
                            scanner.nextLine();
                            String garantia = scanner.nextLine();

                            Eletronico eletronico = new Eletronico(nome, valor, estoque,garantia);
                            sistema.cadastraProduto(eletronico);
                            System.out.println("\n\nCadastrado com sucesso\n\n");
                        }

                }while(escolhasubmenu != 0);


        } // Cadastrar Produtos

        else if (menuOp == 2) {
            if (sistema.listaVazia()) {
                System.out.println("Nenhum produto cadastrado, cadastre para visualizar");
            } else {
                System.out.println("\n\n====== LISTA DE PRODUTOS CADASTRADOS ======");
                System.out.println("Produtos cadastrados:");
                System.out.println(sistema.produtosCadastrados());
            }

        }//Listar Produtos

        else if (menuOp == 3) {
            //Vender Produto

            if (sistema.listaVazia()) {
                System.out.println("Lista Vazia");
            } else {
                System.out.println("\n\n====== VENDA DE PRODUTO ======");
                System.out.println("Digite o produto vendido");
                scanner.nextLine();
                busca = scanner.nextLine();

                System.out.println("Digite a quantidade da venda");
                int quantVendido = scanner.nextInt();

                boolean vendeu = sistema.venderProduto(busca,quantVendido);

                if(vendeu){
                    System.out.println("Produto vendido");
                }else{
                    System.out.println("Falha");
                }
                                                   }


        }//Vender Produtos

        else if (menuOp == 4) {
            //Ver Estoque
            if (sistema.listaVazia()) {
                System.out.println("Lista Vazia\nCadastre para visualizar os produtos");
            } else {
                System.out.println("===== ESTOQUE ======\n" + sistema.produtosCadastrados());
                System.out.println("Soma de estoque Geral: ");
                System.out.println(sistema.somaEstoque());
            }

        }//Soma de Todos Estoques

        else if (menuOp == 5) {
            System.out.println("\n\n====== EDITAR PRECO ======");
            System.out.println("Qual produto Deseja editar?");
            scanner.nextLine();
            String nome = scanner.nextLine();

            System.out.println("Qual o novo preco?");
            double novoPreco = scanner.nextDouble();

            boolean consegui = sistema.atualizaPreco(nome, novoPreco);

            if(consegui){
                System.out.println("Atualizado com sucesso");
            }else{
                System.out.println("Erro ao atualizar");
            }


        }//Editar Produtos

        else if(menuOp == 6){
            System.out.println("\n\n====== ATUALIZAR ESTOQUE ======");
            System.out.println("Qual produto deseja atualizar?");
            scanner.nextLine();
            busca = scanner.nextLine();

            System.out.println("Digite o valor do estoque para atualizar");
            int atualiza = scanner.nextInt();

            boolean alterou = sistema.atualizaEstoque(busca, atualiza);

            if(alterou){
                System.out.println("Atualizado com sucesso ");
            }else{
                System.out.println("Erro ao atualizar");
            }

        } //Adcionar Estoque

        else if(menuOp == 7){
            System.out.println("\n\n====== REMOVER PRODUTO ======");
            System.out.println("Entre com o nome do produto que deseja remover");
            scanner.nextLine();
            String removeProduto = scanner.nextLine();
            boolean remove = sistema.removerProdutos(removeProduto);

            if(remove){
                System.out.println("Excluido com sucesso");
            }else{
                System.out.println("Erro ao excluir");;
            }


        } //Remover

        else if(menuOp == 8){
            System.out.println("===== ALIMENTOS VENCIDOS =====");
            sistema.listarAlimentosVencidos();

        } //Listar Produtos vencidos
        else if (menuOp == 9) {
            System.out.println("====== LISTAR ESTOQUE BAIXO ======");
            sistema.listarEstoqueBaixo();
        }//Listar Estoque Baixo

        else if (menuOp == 10) {
            System.out.println("====== PRODUTOS ACIMA DO VALOR ======");
            System.out.println("Entre com o valor do produto que deseja ver");
            double valorProdutoAcima = scanner.nextDouble();
            sistema.produtosAcimaDePreco(valorProdutoAcima);

        } //Verifica Produtos Acima do Valor Informado

        else if (menuOp == 11) {

            Produto p = sistema.buscaMaisCaro();
            if (p != null){
                System.out.println("====== PRODUTO MAIS CARO ======");
                System.out.println(p.getNome() + " - R$"+p.getPreco());
            }else{
                System.out.println("Não Existe produto na lista");
            }
        } //Mostra Maior Valor

        else if (menuOp == 12) {
            System.out.println("====== PRODUTO COM MENOR ESTOQUE ======");
            Produto p = sistema.buscaMenorEstoque();

            if(p != null){
                System.out.println("Produto com menor estoque: " + p.getNome()+ " - Estoque: "+p.getEstoque());
            }else{
                System.out.println("Não existe produto na lista");
            }
        } //Produto com menor estoque

    }while(menuOp != 0);}}

