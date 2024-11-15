package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMenu extends JFrame {
    private final RFLabel lblTitulo;
    private final RFLabel lblBoasVindas;
    private final RFButton btnLogin;
    private final RFButton btnCadastrar;
    private final JPanel pnlCabecalho;
    private final JPanel pnlCenter;

    private final Color darkred;
    private final Color verydarkgray;

    private final GridBagConstraints gridBagConstraints;
    private final GridBagConstraints constraintsCabecalho;
    private final Insets cabecalhoInsets;
    private final Insets insets;
    public FrmMenu(){
        //sessão dos componentes
        gridBagConstraints = new GridBagConstraints();
        constraintsCabecalho = new GridBagConstraints();
        cabecalhoInsets = new Insets(75,0,75,0);
        insets = new Insets(25,0,25,0);
        lblTitulo = new RFLabel("RFIND");
        lblBoasVindas = new RFLabel("Seja bem-vindo! O que deseja fazer?");
        btnLogin = new RFButton("Iniciar sessão");
        btnCadastrar = new RFButton("Cadastrar-se");
        pnlCabecalho = new JPanel();
        pnlCenter = new JPanel();
        darkred = Color.decode("#9b1b30");
        verydarkgray = Color.decode("#20232a");
        //fim da sessão

        //sessão dos setters
        setSize(480,640);//720,840
        setTitle("RFIND - Seja bem-vindo!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        pnlCabecalho.setLayout(new GridBagLayout());
        pnlCenter.setBackground(verydarkgray);
        pnlCenter.setLayout(new GridBagLayout());
        //fim da sessão

        //sessão da modificação de componentes
        Font quicksandAtt = lblTitulo.getFont().deriveFont(Font.PLAIN,64);//24
        lblTitulo.setFont(quicksandAtt);
        pnlCabecalho.setBackground(darkred);
        pnlCabecalho.setPreferredSize(new Dimension(640,150));
        //fim da sessão

        //sessão da adição de componentes
        constraintsCabecalho.insets = cabecalhoInsets;
        gridBagConstraints.insets = insets;

        pnlCabecalho.add(lblTitulo);
        pnlCenter.add(lblBoasVindas,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        pnlCenter.add(btnLogin,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        pnlCenter.add(btnCadastrar,gridBagConstraints);

        add(pnlCabecalho,BorderLayout.NORTH);
        add(pnlCenter,BorderLayout.CENTER);
        //fim da sessão

        //sessão dos listeners
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(FrmLogin::new);
            }
        });
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(FrmCadastro::new);
            }
        });
        //fim da sessão

        setVisible(true);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(FrmMenu::new);
    }
}