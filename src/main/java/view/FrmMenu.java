package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmMenu extends JFrame {
    private ImageIcon iconTable;
    private ImageIcon iconAddFunc;
    private ImageIcon iconConfig;

    private JButton btnConsultar;
    private JButton btnCadastrarFunc;
    private JButton btnConfig;
    //private JButton btnExit;

    private JPanel pnlAddFunc;
    private JPanel pnlConsultar;
    private JPanel pnlConfig;

    private final Color darkred;
    private final Color verydarkgray;
    private final JPanel pnlCabecalho;
    private final JPanel pnlCabecalhoEsquerda;
    private final JPanel pnlCabecalhoDireita;
    private final JPanel pnlCenter;
    private final Insets insetsCabecalho;
    private final Insets insets;
    private final RFLabel lblTitulo;
    private final GridBagConstraints constraintsCabecalho;
    private final GridBagConstraints gridBagConstraints;
    public FrmMenu(){
        //sessão dos componentes
        darkred = Color.decode("#9b1b30");//155,27,48
        verydarkgray = Color.decode("#20232a");//32,35,42
        pnlCabecalho = new JPanel(new GridLayout(1,2));
        pnlCabecalhoEsquerda = new JPanel(new GridBagLayout());
        pnlCabecalhoDireita = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlCenter = new JPanel(new GridBagLayout());
        insetsCabecalho = new Insets(90,10,90,10);
        insets = new Insets(360,60,360,60);
        lblTitulo = new RFLabel("Menu Principal");
        constraintsCabecalho = new GridBagConstraints();
        gridBagConstraints = new GridBagConstraints();
        //contentPane = new JPanel();

        iconAddFunc = new ImageIcon(getClass().getResource("/Images/addIcon.png"));
        btnCadastrarFunc = new JButton(iconAddFunc);
        iconConfig = new ImageIcon(getClass().getResource("/Images/gear.png"));
        btnConfig = new JButton(iconConfig);
        iconTable = new ImageIcon(getClass().getResource("/Images/Table.png"));
        btnConsultar = new JButton(iconTable);
        pnlConfig = new JPanel(new GridLayout(2,1));
        pnlAddFunc = new JPanel(new GridLayout(2,1));
        pnlConsultar = new JPanel(new GridLayout(2,1));
        //fim da sessão

        //sessão da mudança de componentes
        pnlCabecalhoEsquerda.setBackground(darkred);
        pnlCabecalhoDireita.setBackground(darkred);
        pnlCabecalho.setPreferredSize(new Dimension(1280,180));
        pnlCenter.setBackground(verydarkgray);
        Font quicksandatt = lblTitulo.getFont().deriveFont(Font.PLAIN,72);
        lblTitulo.setFont(quicksandatt);
        pnlConfig.setBackground(verydarkgray);
        pnlConsultar.setBackground(verydarkgray);
        pnlAddFunc.setBackground(verydarkgray);
        btnConfig.setContentAreaFilled(false);
        btnConfig.setBorderPainted(false);
        btnConfig.setOpaque(false);
        btnConfig.setFocusPainted(false);
        btnCadastrarFunc.setContentAreaFilled(false);
        btnCadastrarFunc.setBorderPainted(false);
        btnCadastrarFunc.setFocusPainted(false);
        btnCadastrarFunc.setOpaque(false);
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setBorderPainted(false);
        btnConsultar.setOpaque(false);
        btnConsultar.setFocusPainted(false);
        //fim da sessão

        //sessão dos setters
        setSize(1280,720);
        setTitle("RFIND - Menu principal");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fim da sessão

        //sessão da adição de componentes
        constraintsCabecalho.insets = insetsCabecalho;
        pnlCabecalhoEsquerda.add(lblTitulo,constraintsCabecalho);

        pnlAddFunc.add(btnCadastrarFunc);
        pnlAddFunc.add(new RFLabel("Cadastrar funcionário"));

        pnlConsultar.add(btnConsultar);
        pnlConsultar.add(new RFLabel("Consultar funcionários"));

        pnlConfig.add(btnConfig);
        pnlConfig.add(new RFLabel("Configurações"));

        gridBagConstraints.insets = insets;
        pnlCenter.add(pnlAddFunc,gridBagConstraints);
        gridBagConstraints.gridx = 1;
        pnlCenter.add(pnlConsultar,gridBagConstraints);
        gridBagConstraints.gridx = 2;
        pnlCenter.add(pnlConfig,gridBagConstraints);

        pnlCabecalho.add(pnlCabecalhoEsquerda);
        pnlCabecalho.add(pnlCabecalhoDireita);

        add(pnlCabecalho, BorderLayout.NORTH);
        add(pnlCenter,BorderLayout.CENTER);
        //fim da sessão

        //sessão dos listeners
        btnConsultar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        btnCadastrarFunc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        btnConfig.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        btnCadastrarFunc.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(FrmCadastroFuncionario::new);
            }
        });
        //fim da sessão

        setVisible(true);
    }
}