import java.util.ArrayList;
import java.util.Random;
class Jogo{
    private int vidaM=10;
    private Personagem jogador;
    private boolean fugir;
    private boolean parar;
    private Random chance;
    private ArrayList<Inimigo> monstros=new ArrayList<>();
    private Inimigo monstroRodada;
    private int quantRodada=0;
    public Jogo(String nome){
        this.jogador=new Personagem(nome, 50);
        this.chance = new Random();
        this.monstros.add(new Inimigo("Ogro", 20, ""));
        this.monstros.add(new Inimigo("Morcego", 5,""));
        this.monstros.add(new Inimigo("Lobo", 10, ""));
    }
    public void rodada(){
        monstroRodada=monstros.get(chance.nextInt(3));
        monstroRodada.reset();
        quantRodada++;
    }
    public boolean critico(){
        int critico=chance.nextInt(9);
        if(critico==0){return true;}else{return false;}
    }
    public void atacar(){
        int acerto=chance.nextInt(9);
        if(acerto<3){
            if(critico()){
                jogador.reduzVida(3);
                //System.out.println("Dano CRÍTICO");
            }
            else{
                jogador.reduzVida(1);
            }
        }
        else{
            if(critico()){
                monstroRodada.reduzVida(3);
                //System.out.println("Dano CRÍTICO");
            }
            else{
                monstroRodada.reduzVida(1);
            }
        }
    }
    public boolean fuga(){
        int fuga=chance.nextInt(9);
        if(fuga<4){
            fugir= true;
        }else{
            fugir= false;
            if(critico()){
                jogador.reduzVida(3);
                //System.out.println("Dano CRÍTICO");
            }
            else{
                jogador.reduzVida(1);
            }
        }
        return fugir;
    }
    public void setNome(String nomeNovo){
        jogador.setNome(nomeNovo);
    }
    public boolean getFugir(){
        return fugir;
    }
    public void setFugir(){
        this.fugir=false;
    }
    public boolean getParar(){
        return parar;
    }
    public int getVidaJ(){
        return jogador.getVida();
    }
    public String getNomeJ() {
        return jogador.getNome();
    }
    public String getNomeM(){
        return monstroRodada.getNome();
    }
    public String getCorpoM(){
        return monstroRodada.getCorpo();
    }
    public int getVidaM(){
        return monstroRodada.getVida();
    }
    public void paraJogo(){
        parar=true;
    }
    public int getQuantRodada(){
        return quantRodada;
    }
    public boolean continua(){
        if((vidaM>1)&&(parar==false)){
            return true;
        }
        else{
            return false;
        }
    }
    
}
