package view;

import control.EmpresaControl;
import model.Empresa;
import model.Sessao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmLogin extends JFrame {
    private EmpresaControl empresaControl;

    private ImageIcon iconError;

    private final JPanel pnlCabecalho;
    private final RFLabel lblTitulo;
    private final GridBagConstraints cabecalhoConstraints;
    private final JPanel pnlCenter;
    private final GridBagConstraints gridBagConstraints;
    private RFTextField txtCnpj;
    private RFPasswordField txtSenha;
    private final RFLabel lblCnpj;
    private final RFLabel lblSenha;
    private final RFButton btnLogin;
    private final RFButton btnEsqueciMinhaSenha;
    private final Insets insets;
    private final Insets insetsMin;
    private final Insets insetsCabecalho;
    private final Color darkred;
    private final Color verydarkgray;
    public FrmLogin() {
        empresaControl = new EmpresaControl();

        //sessão dos componentes
        iconError = new ImageIcon(getClass().getResource("/Images/Error.png"));

        pnlCabecalho = new JPanel();
        lblTitulo = new RFLabel("Iniciar sessão");
        lblCnpj = new RFLabel("CNPJ da empresa");
        lblSenha = new RFLabel("Senha");
        pnlCenter = new JPanel();
        gridBagConstraints = new GridBagConstraints();
        cabecalhoConstraints = new GridBagConstraints();
        txtCnpj = new RFTextField();
        txtSenha = new RFPasswordField();
        btnLogin = new RFButton("Iniciar sessão");
        btnEsqueciMinhaSenha = new RFButton("Esqueci minha senha?");
        insets = new Insets(10,0,25,0);
        insetsMin = new Insets(5,0,10,0);
        insetsCabecalho = new Insets(75,0,75,0);
        darkred = Color.decode("#9b1b30");
        verydarkgray = Color.decode("#20232a");
        //fim da sessão

        //sessão dos setters
        setSize(480,640);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("RFIND - Iniciar sessão");
        setResizable(false);
        setLayout(new BorderLayout());
        pnlCenter.setBackground(verydarkgray);
        //fim da sessão

        //sessão da mudança de componentes
        Font quicksandatt = lblTitulo.getFont().deriveFont(Font.PLAIN,64);
        lblTitulo.setFont(quicksandatt);
        pnlCabecalho.setPreferredSize(new Dimension(720,150));
        pnlCabecalho.setBackground(darkred);
        pnlCabecalho.setLayout(new GridBagLayout());
        pnlCenter.setLayout(new GridBagLayout());
        //fim da sessão

        //sessão da adição de componentes
        cabecalhoConstraints.insets = insetsCabecalho;
        gridBagConstraints.insets = insets;
        pnlCabecalho.add(lblTitulo,cabecalhoConstraints);
        gridBagConstraints.insets = insetsMin;
        pnlCenter.add(lblCnpj,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = insets;
        pnlCenter.add(txtCnpj,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = insetsMin;
        pnlCenter.add(lblSenha,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = insets;
        pnlCenter.add(txtSenha,gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(5,0,15,0);
        pnlCenter.add(btnLogin,gridBagConstraints);
        gridBagConstraints.gridy = 5;
        pnlCenter.add(btnEsqueciMinhaSenha,gridBagConstraints);

        add(pnlCabecalho,BorderLayout.NORTH);
        add(pnlCenter,BorderLayout.CENTER);
        //fim da sessão

        //sessão dos listeners
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                SwingUtilities.invokeLater(FrmBoasVindas::new);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean empresaExiste = false;
                try{
                    for (Empresa empresa : empresaControl.select()) {
                        if (empresa.getCnpj().equals(txtCnpj.getText()) && empresa.getSenha().equals(String.valueOf(txtSenha.getPassword()))) {
                            empresaExiste = true;
                            break;
                        }
                    }
                    if (!empresaExiste) {
                        RFMessageDialog.showMessageDialog(null,"Preencha todos os campos corretamente!","Erro ao iniciar sessão");
                        txtCnpj.setText(null);
                        txtSenha.setText(null);
                    } else {
                        Empresa empresa = empresaControl.findByCnpj(txtCnpj.getText());
                        Sessao.setSessao(empresa.getId(),empresa.getCnpj(),empresa.getNome(),empresa.getSenha());
                        dispose();
                        SwingUtilities.invokeLater(FrmMenu::new);
                    }
                }
                catch(Exception ex){
                    RFMessageDialog.showMessageDialog(null, "Preencha todos os campos corretamente!","Erro ao iniciar sessão");
                }
            }
        });
        //fim da sessão

        setVisible(true);
    }
}