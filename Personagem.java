class Personagem{
    private String nome;
    private int vida;
    public Personagem(String nomePersonagem, int vida){
        this.nome=nomePersonagem;
        this.vida=vida;
    }
    public String getNome(){
        return nome;
    }
    public void reduzVida(int dano){
        this.vida-=dano;
    }
    public int getVida(){
        return vida;
    }
}
