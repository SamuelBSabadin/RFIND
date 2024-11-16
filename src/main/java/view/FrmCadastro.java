package view;

import control.EmpresaControl;
import model.Empresa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmCadastro extends JFrame {
    private EmpresaControl empresaControl;

    private final Color darkred;
    private final Color verydarkgray;
    private final JPanel pnlCabecalho;
    private final JPanel pnlConteudo;
    private final RFLabel lblTitulo;
    private final GridBagConstraints constraintsCabecalho;
    private final GridBagConstraints gridBagConstraints;
    private final Insets insetsCabecalho;
    private final Insets insets;
    private final Insets insetsMin;
    private final RFLabel lblCnpj;
    private final RFLabel lblNome;
    private final RFLabel lblSenha;
    private final RFLabel lblConfirmarSenha;
    private final RFTextField txtCnpj;
    private final RFTextField txtNome;
    private final RFPasswordField txtSenha;
    private final RFPasswordField txtConfirmarSenha;
    private final RFButton btnCadastrar;
    private JScrollPane pnlScroll;
    private final JScrollBar scrollBar;
    public FrmCadastro(){
        empresaControl = new EmpresaControl();

        //sessão dos componentes
        darkred = Color.decode("#9b1b30");
        verydarkgray = Color.decode("#20232a");
        pnlCabecalho = new JPanel();
        pnlConteudo = new JPanel();
        lblTitulo = new RFLabel("Cadastrar-se");
        insetsCabecalho = new Insets(75,0,75,0);
        insets = new Insets(10,0,25,0);
        insetsMin = new Insets(5,0,10,0);
        constraintsCabecalho = new GridBagConstraints();
        gridBagConstraints = new GridBagConstraints();
        lblCnpj = new RFLabel("CNPJ da empresa");
        lblNome = new RFLabel("Nome da empresa");
        lblSenha = new RFLabel("Senha");
        lblConfirmarSenha = new RFLabel("Confirmar senha");
        txtCnpj = new RFTextField();
        txtNome = new RFTextField();
        txtSenha = new RFPasswordField();
        txtConfirmarSenha = new RFPasswordField();
        btnCadastrar = new RFButton("Cadastrar-se");
        pnlScroll = new JScrollPane();
        scrollBar = pnlScroll.getVerticalScrollBar();
        pnlScroll.getVerticalScrollBar().setUI(new RFScrollBar());
        pnlScroll.getHorizontalScrollBar().setUI(new RFScrollBar());
        //fim da sessão

        //sessão dos setters
        setTitle("RFIND - Cadastrar-se");
        setSize(480,640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        pnlConteudo.setBackground(verydarkgray);
        //fim da sessão

        //sessão da mudança de componentes
        pnlCabecalho.setBackground(darkred);
        pnlCabecalho.setPreferredSize(new Dimension(640,150));
        pnlCabecalho.setLayout(new GridBagLayout());
        Font quicksandatt = lblTitulo.getFont().deriveFont(Font.PLAIN,64);
        pnlConteudo.setLayout(new GridBagLayout());
        lblTitulo.setFont(quicksandatt);
        scrollBar.setUnitIncrement(8);
        scrollBar.setBlockIncrement(50);
        //fim da sessão

        //sessão da adição de componentes;
        constraintsCabecalho.insets = insetsCabecalho;
        pnlCabecalho.add(lblTitulo,constraintsCabecalho);

        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblCnpj,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtCnpj,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblNome,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtNome,gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblSenha,gridBagConstraints);
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtSenha,gridBagConstraints);
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblConfirmarSenha,gridBagConstraints);
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtConfirmarSenha,gridBagConstraints);
        gridBagConstraints.gridy = 8;
        pnlConteudo.add(btnCadastrar,gridBagConstraints);

        pnlScroll.setViewportView(pnlConteudo);

        add(pnlCabecalho,BorderLayout.NORTH);
        add(pnlScroll,BorderLayout.CENTER);
        //fim da sessão

        //sessão dos listeners
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                dispose();
                SwingUtilities.invokeLater(FrmBoasVindas::new);
            }
        });
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(String.valueOf(txtConfirmarSenha.getPassword()).equals(String.valueOf(txtSenha.getPassword()))){
                        Empresa empresa = new Empresa(txtCnpj.getText(), txtNome.getText(), String.valueOf(txtSenha.getPassword()));
                        empresaControl.insert(empresa);
                        txtCnpj.setText(null);
                        txtNome.setText(null);
                        txtSenha.setText(null);
                        txtConfirmarSenha.setText(null);
                        JOptionPane.showMessageDialog(null,"Sua empresa foi cadastrada com sucesso!");
                        dispose();
                        SwingUtilities.invokeLater(FrmBoasVindas::new);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Senhas não são iguais!");
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Erro ao cadastrar a empresa!");
                }
            }
        });
        //fim da sessão

        setVisible(true);
    }
}