class Personagem{
    private String nome;
    private int vidaAtual;
    private int vidaTotal;
    public Personagem(String nomePersonagem, int vida){
        this.nome=nomePersonagem;
        this.vidaTotal=vida;
        this.vidaAtual=vida;
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
}
