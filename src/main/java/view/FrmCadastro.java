package view;

import control.EmpresaControl;
import model.Empresa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

public class FrmCadastro extends JFrame {
    private InputStream fonteLexendExa;
    private Font lexendExa;

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
        try{
            fonteLexendExa = FrmBoasVindas.class.getResourceAsStream("/Fonts/Lexend_Exa/static/LexendExa-Regular.ttf");
            lexendExa = Font.createFont(Font.TRUETYPE_FONT, fonteLexendExa);
            lexendExa = lexendExa.deriveFont(Font.PLAIN, 48);
        }
        catch(Exception e){

        }

        empresaControl = new EmpresaControl();

        //sessão dos componentes
        darkred = new Color(155,27,48);//Color.decode("#9b1b30");
        verydarkgray = new Color(32,35,42);//Color.decode("#20232a");
        pnlCabecalho = new JPanel();
        pnlConteudo = new JPanel();
        lblTitulo = new RFLabel("CADASTRAR-SE");
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
        pnlConteudo.setLayout(new GridBagLayout());
        lblTitulo.setFont(lexendExa);
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
                        RFMessageDialog.showMessageDialog(null,"Cadastro concluído com êxito!","Sucesso");
                        dispose();
                        SwingUtilities.invokeLater(FrmBoasVindas::new);
                    }
                    else{
                        RFMessageDialog.showMessageDialog(null,"Senhas não são iguais!","Erro ao cadastrar");
                    }
                }
                catch(Exception ex){
                    RFMessageDialog.showMessageDialog(null,"Preencha todos os campos corretamente!","Erro ao cadastrar");
                }
            }
        });
        //fim da sessão

        setVisible(true);
    }
}