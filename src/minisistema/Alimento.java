package minisistema;

public class Alimento extends Produto implements Vendavel {

    //extends Produto -->  é um Produto

    // implements Vendavel --> é Vendável / pode ser vendido


    private String validade;

    public Alimento(String nome, double preco, int estoque, String validade){
        super(nome,preco,estoque);
        this.validade = validade;
    }

    public String getValidade() {
        return validade;
    }

    @Override //Pega com o super uma coisa do pai e substitui
    public String toString(){
        return super.toString() + (" -- Validade: "+validade);

        //super.toString() → pega nome, preço e estoque de Produto
        //Validade é especifico do Alimento

    }

    @Override
    public String getTipo(){
        return "Alimento";
    }

    @Override // Também pode apenas modificar, Devido ao contrato vendavel
    public boolean vender(int quantidade){

        return realizaVenda(quantidade);

    }
}




