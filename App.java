import java.util.Scanner;
class App{
    private static Jogo game;
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Qual o seu nome?");
        game=new Jogo(teclado.nextLine());
        game.rodada();
        int monstrosDerrotados=0;
        while (game.continua()) {
            System.out.println("Oh não, um "+game.getNomeM()+" apareceu" );
            while (game.getVidaM()>1 && game.getFugir()==false && game.getParar()==false) {
                menuOpções();
                int escolha=teclado.nextInt();
                switch (escolha) {
                    case 1:
                        game.atacar();
                        break;
                    case 2:
                        game.fuga();
                        break;
                    case 3:
                        game.paraJogo();
                        break;
                    default:
                        System.out.println("Opção Inválida");
                        break;
                }
            }
            if(game.getFugir()==true){
                game.setFugir();
            }
            else{
                if(game.getParar()==false){
                    monstrosDerrotados++;
                }
            }
            game.rodada();
            
        }
        System.out.println("Você durou "+game.getQuantRodada()+" e derrotou "+monstrosDerrotados+" monstros");
        
        teclado.close();
    }
    public static void menuOpções(){
        System.out.println("O "+game.getNomeM()+" está com "+game.getVidaM());
        System.out.println(game.getNomeJ()+" está com "+game.getVidaJ()+" de vida");
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Lutar");
        System.out.println("2 - Fugir");
        System.out.println("3 - Parar");
    }
}
