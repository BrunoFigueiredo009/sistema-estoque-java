package minisistema;

public class EstoqueInsuficienteException extends RuntimeException {//Classe pronta do java -> unchecked -> compilado nao obriga tratar

    public EstoqueInsuficienteException(String mensagem){
        super(mensagem);
    }

}
