package minisistema;

public class Eletronico extends Produto{
    private String garantia;

    public Eletronico(String nome,double valor, int estoque, String garantia){
        super(nome,valor,estoque);

        this.garantia = garantia;
    }

    public String getGarantia() {
        return garantia;
    }

    @Override
    public String toString(){
        return super.toString() + (" -- Garantia "+garantia);

        }
    }

