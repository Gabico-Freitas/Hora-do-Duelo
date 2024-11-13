import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoInterface {

    private JFrame janelaPrincipal;
    private Jogo j=new Jogo(null);
    private int monstrosDerrotados=0, recordeMonstros=0, recordeRodadas=0;
    private int maiorQuantRod=0,maiorQauntMonst=0;

    public static void main(String[] args) {
        
        JogoInterface jI=new JogoInterface();
        jI.mostrarJanelaEntrada();
    }

    // Janela do menu
    private void mostrarJanelaEntrada() {
        JFrame janelaEntrada = new JFrame("Hora do Duelo");
        janelaEntrada.setSize(450, 165);
        janelaEntrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaEntrada.setLayout(new BoxLayout(janelaEntrada.getContentPane(), BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Pronto pra começar uma aventura?");
        JLabel label2 = new JLabel("Digite seu nome:");
        // JLabel recordRod = new JLabel("Rodadas sobrevividas: "+recordeRodadas);
        // JLabel recordMons = new JLabel("Monstros derrotados: "+recordeMonstros);
        JTextField campoNome = new JTextField(15);
        JButton botaoConfirmar = new JButton("Confirmar");

        botaoConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                j.setNome(campoNome.getText().trim());
                if (!j.getNomeJ().isEmpty()) {
                    janelaEntrada.dispose(); // Fecha a janela de entrada
                    mostrarJanelaPrincipal(); // Abre a janela principal
                } else {
                    JOptionPane.showMessageDialog(janelaEntrada, "Qual o seu nome?");
                }
            }
        });
        String[] colunas = {"", "Última tentativa","Melhor Tentativa"};
        String[][] dados = {{"Rodadas sobrevividas", String.valueOf(recordeRodadas),String.valueOf(maiorQuantRod)},
                            {"Monstros derrotados", String.valueOf(recordeMonstros),String.valueOf(maiorQauntMonst)}};
        JTable tabela = new JTable(dados, colunas);
        tabela.setFillsViewportHeight(true); // Para preencher o painel
        JScrollPane scrollPane = new JScrollPane(tabela); // Adiciona o JScrollPane para a tabela com rolagem

        JPanel nomeTextFieldBotao=new JPanel();
        nomeTextFieldBotao.setLayout(new FlowLayout());
        nomeTextFieldBotao.add(campoNome);
        nomeTextFieldBotao.add(botaoConfirmar);
        nomeTextFieldBotao.setAlignmentX(Component.CENTER_ALIGNMENT);
        janelaEntrada.add(label);
        janelaEntrada.add(label2);
        janelaEntrada.add(nomeTextFieldBotao);
        //janelaEntrada.add(botaoConfirmar);
        janelaEntrada.add(scrollPane);
        // janelaEntrada.add(recordMons);
        // janelaEntrada.add(recordRod);
        janelaEntrada.setLocationRelativeTo(null); // Centraliza a janela
        janelaEntrada.setVisible(true);
    }

    // Janela principal
    private void mostrarJanelaPrincipal() {
        janelaPrincipal = new JFrame("Jogo - Hora do Duelo!");
        janelaPrincipal.setSize(500, 700);
        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaPrincipal.setLayout(new BorderLayout());

        JPanel painelInfo = new JPanel();
        painelInfo.setLayout(new GridLayout(3, 2));
        j.rodada();
        JLabel labelJogador = new JLabel(j.getNomeJ());
        JLabel labelVidaJogador = new JLabel("Vida do Jogador: " + j.getVidaJ());
        JLabel labelCorpoJogador = new JLabel("j.getCorpoJ()");
        
        JLabel labelMonstro = new JLabel(j.getNomeM());
        JLabel labelCorpoMonstro = new JLabel("j.getCorpoM()");
        JLabel labelVidaMonstro = new JLabel("Vida do Monstro: " + j.getVidaM());

        painelInfo.add(labelJogador);
        painelInfo.add(labelMonstro);
        painelInfo.add(labelCorpoJogador);
        painelInfo.add(labelCorpoMonstro);
        painelInfo.add(labelVidaJogador);
        painelInfo.add(labelVidaMonstro);

        //Botões
        JPanel painelBotoes = new JPanel();
        JButton botaoAtacar = new JButton("Atacar");
        JButton botaoFugir = new JButton("Fugir");
        JButton botaoParar = new JButton("Parar");

        JTextArea historico=new JTextArea();

        botaoAtacar.addActionListener(e -> {
            j.atacar();
            int vidaAnteriorMonstro=j.getVidaM();
            int vidaAnteriorJogador=j.getVidaJ();
            labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
            labelVidaJogador.setText("Vida do Jogador: " + j.getVidaJ());
            
            if (j.getVidaM() <= 0) {
                JOptionPane.showMessageDialog(janelaPrincipal, "Você derrotou o monstro!");
                monstrosDerrotados++;
                j.rodada();
                labelMonstro.setText(j.getNomeM());
                labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
            }
            if(j.getVidaJ()<1){
                botaoParar.doClick();
            }
        });

        botaoFugir.addActionListener(e -> {
            j.fuga();
            if(j.getFugir()){
                JOptionPane.showMessageDialog(janelaPrincipal, "Você fugiu do combate!");
                j.rodada();
                labelMonstro.setText(j.getNomeM());
                labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
                if(j.getVidaJ()<1){
                    botaoParar.doClick();
                }
            }
            else{
                labelMonstro.setText(j.getNomeM());
                labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
                labelVidaJogador.setText("Vida do Jogador: " + j.getVidaJ());
            }

        });
        botaoParar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(j.getVidaJ()<1){
                    JOptionPane.showMessageDialog(janelaPrincipal, "                   VOCÊ MORREU!\n Aguentou "+j.getQuantRodada()+" rodadas e derrotou "+monstrosDerrotados+" monstros!");
                }
                else{
                    JOptionPane.showMessageDialog(janelaPrincipal, "Você durou por "+j.getQuantRodada()+" rodadas e derrotou "+monstrosDerrotados+" monstros!");
                }
                recordeMonstros=monstrosDerrotados;
                recordeRodadas=j.getQuantRodada();
                if(monstrosDerrotados>maiorQauntMonst){
                    maiorQauntMonst=monstrosDerrotados;
                }
                if(j.getQuantRodada()>maiorQuantRod){
                    maiorQuantRod=j.getQuantRodada();
                }
                j.reset();
                janelaPrincipal.setVisible(false);
                janelaPrincipal.dispose();
                mostrarJanelaEntrada();
            }
        }
        );
        new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(j.getVidaJ()<1){
                    botaoParar.doClick();
                }
            }
        };
        painelBotoes.add(botaoAtacar);
        painelBotoes.add(botaoFugir);
        painelBotoes.add(botaoParar);
        janelaPrincipal.add(painelInfo, BorderLayout.CENTER);
        janelaPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        janelaPrincipal.setLocationRelativeTo(null); // Centraliza a janela
        janelaPrincipal.setVisible(true);
    }
}
