class Personagem{
    private String nome;
    private int vidaAtual;
    private int vidaTotal;
    private int dano;
    public Personagem(String nomePersonagem, int vida, int dano){
        this.nome=nomePersonagem;
        this.vidaTotal=vida;
        this.vidaAtual=vida;
        this.dano=dano;
    }
    public void reset(){
        this.vidaAtual=vidaTotal;
    }
    public void setNome(String nomeNovo){
        this.nome=nomeNovo;
    }
    public String getNome(){
        return nome;
    }
    public void reduzVida(int dano){
        this.vidaAtual-=dano;
    }
    public int getVida(){
        return vidaAtual;
    }
    public int dano(){
        return dano;
    }
}
