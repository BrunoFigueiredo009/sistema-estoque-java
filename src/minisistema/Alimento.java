package minisistema;

public class Alimento extends Produto {
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
        //Validae é especifico do Alimento

    }

}
