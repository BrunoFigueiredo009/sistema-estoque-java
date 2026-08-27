package minisistema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static void menu() {
    System.out.println("===== SISTEMA DE PRODUTOS =====");
    System.out.println("1 - Cadastrar Produto");
    System.out.println("2 - Listar produtos");
    System.out.println("3 - Vender produto");
    System.out.println("4 - Ver estoque total");
    System.out.println("5 - Editar Preco");
    System.out.println("6 - Adcionar Estoque");
    System.out.println("7 - Remover Produto");
    System.out.println("0 - Sair");
}

    static void subMenu(){
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
                            System.out.println("\n\nAlimento cadastrado com sucesso\n\n");

                        }

                        else if(verificaSubMenu && escolhasubmenu == 2){
                            scanner.nextLine();
                            System.out.println("Entre com o nome do Eletronico");
                            nome = scanner.nextLine();

                            System.out.println("Entre com o preco");
                            valor = scanner.nextInt();

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


        } // Cadastrar Produto

        else if (menuOp == 2) {
            if (sistema.listaVazia()) {
                System.out.println("Nemhum produto cadastrado, cadastre para visualizar");
            } else {
                System.out.println("Produtos cadastrados:");
                System.out.println(sistema.produtosCadastrados());
            }

        }//Listar Produtos

        else if (menuOp == 3) {
            //Vender Produto

            if (sistema.listaVazia()) {
                System.out.println("Lista Vazia");
            } else {
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
                System.out.println("------ ESTOQUE DE PRODUTOS TOTAL ------\n" + sistema.produtosCadastrados());
                System.out.println("Soma de estoque Geral: ");
                System.out.println(sistema.somaEstoque());
            }

        }//Soma de todos estoques

        else if (menuOp == 5) {

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


        }//Editar Produto

        else if(menuOp == 6){
            System.out.println("Qual produto deseja atualizar?");
            scanner.nextLine();
            busca = scanner.nextLine();

            System.out.println("Digite o valor do estoque para atualizar");
            int atualiza = scanner.nextInt();

            boolean alterou = sistema.atualizaEstoque(busca, atualiza);

            if(alterou){
                System.out.println("Produto Atualizado com sucesso");
            }else{
                System.out.println("Erro ao atualizar");
            }

        } //Adcionar Estoque

        else if(menuOp == 7){
            System.out.println("Entre com o nome do produto que deseja remover");
            scanner.nextLine();
            String removeProduto = scanner.nextLine();
            boolean remove = sistema.removerProdutos(removeProduto);

            if(remove){
                System.out.println("Produto Excluido com Sucesso");
            }else{
                System.out.println("Erro ao Excluir");
            }


        } //Remover

    }while(menuOp != 0);}}

