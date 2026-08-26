package minisistema;

public class Eletronico extends Produto implements Vendavel{
    private String garantia;

    public Eletronico(String nome,double valor, int estoque, String garantia){
        super(nome,valor,estoque); //super = Acesso, chamo algo do pai

        this.garantia = garantia;
    }

    public String getGarantia() {
        return garantia;
    }



    @Override // Sobrescrever um metodo que foi definido pelo pai
    public String toString(){
        return super.toString() + (" -- Garantia "+garantia);

        }
    @Override
    public String getTipo(){
        return "Eletronico";
    }
    @Override
    public boolean vender(int quantidade){
        return realizaVenda(quantidade);
    }

}





