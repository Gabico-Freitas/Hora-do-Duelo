class Inimigo extends Personagem{
    private String corpo;
    public Inimigo(String nome, int vida, String corpo){
        super(nome, vida);
        this.corpo=corpo;
    }
    public String getCorpo(){
        return corpo;
    }
}
