package minisistema;

public abstract class Produto {

        private String nome;
        private double preco;
        private int estoque;



        public Produto (String nome, double preco, int estoque){
            this.nome = nome;
            if(preco > 0){
                this.preco = preco;
            }else{
                this.preco = 0;
            }
            if(estoque >= 0){
                this.estoque = estoque;
            }else{
                this.estoque = 0;
            }
        }

       public  boolean realizaVenda(int quantidade){
            if (estoque >= quantidade && quantidade > 0){
                this.estoque -= quantidade;
                return true;
            }return  false;
        }

        public boolean aumentaEstoque(int a){
            if(a > 0){
                this.estoque += a;
                return true;

            }
            return false;
        }

        public int getEstoque() {
            return estoque;
        }

        public String getNome() {
            return nome;
        }

        public double getPreco() {
            return preco;
        }

        public boolean setPreco(double preco) {
            if(preco > 0){
                this.preco = preco;
                return true;
            }return false;

        }

        public String toString(){
            return ("Nome: "+nome+" -- Valor: "+preco+" -- Estoque: "+estoque);
        }

        public abstract String getTipo(); //Obriga cada filho ter um getTipo()
    }



