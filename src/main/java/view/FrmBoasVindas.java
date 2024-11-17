package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;

public class FrmBoasVindas extends JFrame {
    private InputStream fonteLexendExa;
    private Font lexendExa;

    private final RFLabel lblTitulo;
    private final RFLabel lblBoasVindas;
    private final RFButton btnLogin;
    private final RFButton btnCadastrar;
    private final JPanel pnlCabecalho;
    private final JPanel pnlCenter;
    private JPanel contentPane;

    private final Color darkred;
    private final Color verydarkgray;

    private final GridBagConstraints gridBagConstraints;
    private final GridBagConstraints constraintsCabecalho;
    private final Insets cabecalhoInsets;
    private final Insets insets;
    public FrmBoasVindas(){

        try{
            fonteLexendExa = FrmBoasVindas.class.getResourceAsStream("/Fonts/Lexend_Exa/static/LexendExa-Regular.ttf");
            lexendExa = Font.createFont(Font.TRUETYPE_FONT, fonteLexendExa);
            lexendExa = lexendExa.deriveFont(Font.PLAIN, 72);
        }
        catch(Exception e){

        }

        //sessão dos componentes
        gridBagConstraints = new GridBagConstraints();
        constraintsCabecalho = new GridBagConstraints();
        cabecalhoInsets = new Insets(75,0,75,0);
        insets = new Insets(25,0,25,0);
        lblTitulo = new RFLabel("RFIND");
        lblBoasVindas = new RFLabel("Seja bem-vindo! O que deseja fazer?");
        btnLogin = new RFButton("Iniciar sessão");
        btnCadastrar = new RFButton("Cadastrar-se");
        pnlCabecalho = new JPanel(new GridBagLayout());
        pnlCenter = new JPanel();
        darkred = new Color(155,27,48);
        verydarkgray = new Color(32,35,42);
        //fim da sessão

        //sessão dos setters
        setSize(480,640);//720,840
        setTitle("RFIND - Seja bem-vindo!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        pnlCenter.setBackground(verydarkgray);
        pnlCenter.setLayout(new GridBagLayout());
        //fim da sessão

        //sessão da modificação de componentes
        lblTitulo.setFont(lexendExa);

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
        System.setProperty("awt.useSystemAAFontSettings","on");
        SwingUtilities.invokeLater(FrmBoasVindas::new);
    }
}