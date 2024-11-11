import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoInterface {

    private JFrame janelaPrincipal;
    private Jogo j=new Jogo(null);
    private int monstrosDerrotados=0;

    public static void main(String[] args) {
        
        new JogoInterface().mostrarJanelaEntrada();
    }

    // Janela do nome
    private void mostrarJanelaEntrada() {
        JFrame janelaEntrada = new JFrame("Nome do Jogador");
        janelaEntrada.setSize(300, 100);
        janelaEntrada.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaEntrada.setLayout(new FlowLayout());

        JLabel label = new JLabel("Digite seu nome:");
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

        janelaEntrada.add(label);
        janelaEntrada.add(campoNome);
        janelaEntrada.add(botaoConfirmar);
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
        JLabel labelMonstro = new JLabel(j.getNomeM());
        //JLabel labeCorpoMonstro = new JLabel(j.getCorpoM());
        JLabel labelVidaMonstro = new JLabel("Vida do Monstro: " + j.getVidaM());

        painelInfo.add(labelJogador);
        painelInfo.add(labelMonstro);
        //  painelInfo.add(labeCorpoMonstro);
        //  painelInfo.add(labelCorpoJogador);
        painelInfo.add(labelVidaJogador);
        painelInfo.add(labelVidaMonstro);

        //Botões
        JPanel painelBotoes = new JPanel();
        JButton botaoAtacar = new JButton("Atacar");
        JButton botaoFugir = new JButton("Fugir");
        JButton botaoParar = new JButton("Parar");

        botaoAtacar.addActionListener(e -> {
            j.atacar();
            labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
            labelVidaJogador.setText("Vida do Jogador: " + j.getVidaJ());
            if (j.getVidaM() <= 0) {
                JOptionPane.showMessageDialog(janelaPrincipal, "Você derrotou o monstro!");
                monstrosDerrotados++;
                j.rodada();
                labelMonstro.setText(j.getNomeM());
                labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
            }
        });

        botaoFugir.addActionListener(e -> {
            j.fuga();
            if(j.getFugir()){
                JOptionPane.showMessageDialog(janelaPrincipal, "Você fugiu do combate!");
                j.rodada();
                labelMonstro.setText(j.getNomeM());
                labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
            }
            else{
                labelMonstro.setText(j.getNomeM());
                labelVidaMonstro.setText("Vida do Monstro: " + j.getVidaM());
                labelVidaJogador.setText("Vida do Jogador: " + j.getVidaJ());
            }
            

        });
        botaoParar.addActionListener(e -> JOptionPane.showMessageDialog(janelaPrincipal, "Você durou por "+j.getQuantRodada()+" rodadas e derrotou "+monstrosDerrotados+" monstros!"));

        painelBotoes.add(botaoAtacar);
        painelBotoes.add(botaoFugir);
        painelBotoes.add(botaoParar);

        janelaPrincipal.add(painelInfo, BorderLayout.CENTER);
        janelaPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        janelaPrincipal.setLocationRelativeTo(null); // Centraliza a janela
        janelaPrincipal.setVisible(true);
    }
}
