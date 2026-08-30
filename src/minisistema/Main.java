package minisistema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static void menu() {
        System.out.println("\n\n====== SISTEMA DE PRODUTOS ======");
        System.out.println("1 - CADASTRAR PRODUTO");
        System.out.println("2 - CONSULTAR PRODUTOS");
        System.out.println("3 - VENDAS");
        System.out.println("4 - EDITAR PRODUTOS");
        System.out.println("5 - ESTOQUE");
        System.out.println("0 - Sair\n\n");
    }

    static void subMenu() {
        System.out.println("\n\n====== CADASTRO ======");
        System.out.println("1 - Cadastrar Alimento");
        System.out.println("2 - Cadastrar Eletrônico");
        System.out.println("0 - Voltar");
    }

    public static void main(String[] args) {
        SistemaProdutos sistema = new SistemaProdutos();
        Scanner scanner = new Scanner(System.in);
        boolean verificaSubMenu;
        int menuOp;

        do {
            menu();
            menuOp = scanner.nextInt();

            boolean verificaMenu = sistema.verificaNumeroMenu(menuOp, 5);

            if(!verificaMenu){
                System.out.println("Entre com uma opçao válida");
                continue;
            }else{

            String busca;
            int op;

            if (menuOp == 1) {

                do {
                    boolean verificaPreco;
                    String nome;
                    double valor;
                    int estoque;
                    subMenu();
                    op = scanner.nextInt();
                    verificaSubMenu = sistema.verificaSubMenu(op, 2);

                    if (!verificaSubMenu) {
                        System.out.print("\n\nNúmero inválido, entre com um número válido:\n\n");
                        continue;
                    }

                    else if (op == 0) {
                        System.out.println("\n\nVoltando ao menu\n\n");
                        break;
                    }

                    else if (op == 1) {
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

                        Alimento p = new Alimento(nome, valor, estoque, validade);
                        sistema.cadastraProduto(p);
                        System.out.println("\n\n====== Alimento cadastrado com sucesso ======\n\n");

                    } //Cadastro de Alimento

                    else if (op == 2) {
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

                        Eletronico eletronico = new Eletronico(nome, valor, estoque, garantia);
                        sistema.cadastraProduto(eletronico);
                        System.out.println("\n\nCadastrado com sucesso\n\n");
                    }//Cadastro de Eletronico

                } while (op != 0);


            } // Cadastrar Produtos

            else if (menuOp == 2) {

                do {
                    System.out.println("""
                            ====== CONSULTAR PRODUTOS ======
                            1 - Consultar por Preço
                            2 - Consultar o mais caro
                            3 - Consultar produtos vencidos
                            4 - Consultar todos produtos
                            0 - Sair""");
                    op = scanner.nextInt();
                    verificaSubMenu = sistema.verificaSubMenu(op, 4);

                    if (!verificaSubMenu) {
                        System.out.println("Numero invalido");
                        continue;
                    }

                    else if (op == 0) {
                        break;
                    }

                    else if (sistema.listaVazia()) {
                        System.out.println("Nenhum produto cadastrado, cadastre para visualizar");
                        break;
                    }

                    else if (op == 1) {
                        System.out.println("====== PRODUTOS ACIMA DO VALOR ======");
                        System.out.println("Entre com o valor do produto que deseja ver");
                        double valorProdutoAcima = scanner.nextDouble();
                        sistema.produtosAcimaDePreco(valorProdutoAcima);

                    } //Consultar por preco

                    else if (op == 2) {

                        Produto p = sistema.buscaMaisCaro();
                        if (p != null) {
                            System.out.println("====== PRODUTO MAIS CARO ======");
                            System.out.println(p.getNome() + " - R$" + p.getPreco());
                        } else {
                            System.out.println("Não Existe produto na lista");
                        }

                    } //Produto mais caro

                    else if (op == 3) {
                        System.out.println("===== ALIMENTOS VENCIDOS =====");
                        sistema.listarAlimentosVencidos();

                    } //Alimentos Vencidos

                    else if (op == 4) {

                        System.out.println("\n\n====== LISTA DE PRODUTOS CADASTRADOS ======");
                        System.out.println("Produtos cadastrados:");
                        System.out.println(sistema.produtosCadastrados());
                    } //Produtos Cadastrados

                } while (op != 0);

            }//Consultar Produtos

            else if (menuOp == 3) {

                do {
                    System.out.println("\n\n====== VENDAS ======");
                    System.out.println("1 - Vender Produto");
                    System.out.println("0 - Voltar");
                    op = scanner.nextInt();

                    verificaSubMenu = sistema.verificaSubMenu(op, 1);

                    if (!verificaSubMenu) {
                        System.out.print("\n\nNúmero inválido, entre com um número válido:\n\n");
                        continue;
                    }

                    else if (op == 0) {
                        System.out.println("\n\nVoltando ao menu\n\n");
                        break;
                    }

                    else if (op == 1) {
                        if (sistema.listaVazia()) {
                            System.out.println("Lista Vazia");
                            break;
                        }
                        System.out.println("Digite o produto vendido");
                        scanner.nextLine();
                        busca = scanner.nextLine();
                        System.out.println("Digite a quantidade da venda");
                        int quantVendido = scanner.nextInt();
                        boolean vendeu = sistema.venderProduto(busca, quantVendido);

                        if (vendeu) {
                            System.out.println("Produto vendido");
                        } else {
                            System.out.println("Falha");
                        }
                    }//Vender produto

                } while (op != 0);

            }//Vender Produtos

            else if (menuOp == 4) {
                do {
                    System.out.println("""
                            ====== EDITAR PRODUTOS ======
                            1 - Editar preço
                            2 - Remover Produto
                            0 - Voltar""");
                    op = scanner.nextInt();
                    verificaSubMenu = sistema.verificaSubMenu(op, 2);

                    if(!verificaSubMenu){
                        System.out.println("Entre com uma opção valida");
                        continue;
                    }

                    else if (op == 0) {
                        System.out.println("\n\nVoltando ao menu\n\n");
                        break;
                    } else if (sistema.listaVazia()) {
                        System.out.println("Lista vazia, voltando ao menu");
                        break;

                    }
                    if (op == 1) {
                        System.out.println("\n\n====== EDITAR PRECO ======");
                        System.out.println("Qual produto Deseja editar?");
                        scanner.nextLine();
                        String nome = scanner.nextLine();

                        System.out.println("Qual o novo preco?");
                        double novoPreco = scanner.nextDouble();

                        boolean consegui = sistema.atualizaPreco(nome, novoPreco);

                        if (consegui) {
                            System.out.println("Atualizado com sucesso");
                        } else {
                            System.out.println("Erro ao atualizar");
                        }
                    } //Editar Preco

                    else if (op == 2) {
                        System.out.println("\n\n====== REMOVER PRODUTO ======");
                        System.out.println("Entre com o nome do produto que deseja remover");
                        scanner.nextLine();
                        String removeProduto = scanner.nextLine();
                        boolean remove = sistema.removerProdutos(removeProduto);

                        if (remove) {
                            System.out.println("Excluido com sucesso");
                        } else {
                            System.out.println("Erro ao excluir");
                        }
                    }//Remover Produto

                } while (op != 0);

            }//Editar

            else if (menuOp == 5) {

                do {
                    System.out.println("""
                            ====== ESTOQUE ======
                            1 - Adiciona estoque                            
                            2 - Produtos com estoques Zerados
                            3 - Produtos com estoque baixo
                            4 - Soma dos valores do estoque
                            5 - Soma do estoque geral
                            0 - Voltar""");
                    op = scanner.nextInt();
                    verificaSubMenu = sistema.verificaSubMenu(op, 5);

                    if (!verificaSubMenu) {
                        System.out.print("\n\nNúmero inválido, entre com um número válido:\n\n");
                        continue;
                    }

                    else if (op == 0) {
                        System.out.println("\n\nVoltando ao menu\n\n");
                        break;
                    }

                    else if (sistema.listaVazia()) {
                        System.out.println("lista vazia, cadastre para visualizar\nVoltando ao menu");
                        break;
                    }

                    if (op == 1) {
                        System.out.println("\n\n====== ATUALIZAR ESTOQUE ======");
                        System.out.println("Qual produto deseja atualizar?");
                        scanner.nextLine();
                        busca = scanner.nextLine();

                        System.out.println("Digite o valor do estoque para atualizar");
                        int atualiza = scanner.nextInt();

                        boolean alterou = sistema.atualizaEstoque(busca, atualiza);

                        if (alterou) {
                            System.out.println("Atualizado com sucesso ");
                        } else {
                            System.out.println("Erro ao atualizar");
                        }

                    }//Atualizar Estoque

                    else if (op == 2) {
                        System.out.println("====== ESTOQUES ZERADOS ======");
                        ArrayList<Produto> produtosSemEstoque = sistema.buscaProdutosSemEstoque();

                        for (int i = 0; i < produtosSemEstoque.size(); i++) {
                            String nome = produtosSemEstoque.get(i).getNome();
                            System.out.println(nome);
                        }
                        System.out.println("Produtos com estoque zerados: " + produtosSemEstoque.size());

                    }//Produtos Zerados

                    else if (op == 3) {
                        System.out.println("====== LISTAR ESTOQUE BAIXO ======");
                        sistema.listarEstoqueBaixo();
                    }//Produto com estoque baixo

                    else if (op == 4) {
                        System.out.println("====== CALCULAR O VALOR TOTAL DO ESTOQUE ======");
                        double valorTotalEstoque = sistema.calculaValorTotal();
                        System.out.printf("O valor total do estoque é: R$ %.2f%n", valorTotalEstoque);
                    }//Calcular Valor Total estoque

                    else if (op == 5) {

                            System.out.println("===== ESTOQUE ======\n" + sistema.produtosCadastrados());
                            System.out.println("Soma de estoque Geral: ");
                            System.out.println(sistema.somaEstoque());

                    }//Estoque Total

                } while (op != 0);
            }
        }
        } while (menuOp != 0);
    }
}


