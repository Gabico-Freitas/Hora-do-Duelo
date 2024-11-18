class Inimigo extends Personagem{
    private String corpo;
    public Inimigo(String nome, int vida, String corpo, int dano){
        super(nome, vida, dano);
        this.corpo=corpo;
    }
    public String getCorpo(){
        return corpo;
    }
}
