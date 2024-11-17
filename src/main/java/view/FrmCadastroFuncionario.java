package view;

import control.EmpresaControl;
import control.FuncionarioControl;
import model.Empresa;
import model.Funcionario;
import model.Sessao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.List;

public class FrmCadastroFuncionario extends JFrame
{
    private InputStream fonteLexendExa;
    private Font lexendExa;

    private Color verydarkgray;
    private Color darkred;

    //private Font quicksandatt;

    private JScrollPane pnlCenter;
    private JPanel pnlCabecalho;
    private JPanel pnlConteudo;

    private GridBagConstraints constraintsCabecalho;
    private GridBagConstraints gridBagConstraints;

    private Insets insetsCabecalho;
    private Insets insets;
    private Insets insetsMin;

    private RFLabel lblTitulo;
    private RFLabel lblCpf;
    private RFLabel lblNome;
    private RFLabel lblSobrenome;
    private RFLabel lblSetor;

    private RFTextField txtCpf;
    private RFTextField txtNome;
    private RFTextField txtSobrenome;
    private RFTextField txtSetor;

    private RFButton btnCadastrar;

    private JScrollBar scrollBar;

    private EmpresaControl empresaControl;
    private FuncionarioControl funcionarioControl;

    private Sessao sessao;

    public FrmCadastroFuncionario()
    {
        try{
            fonteLexendExa = FrmBoasVindas.class.getResourceAsStream("/Fonts/Lexend_Exa/static/LexendExa-Regular.ttf");
            lexendExa = Font.createFont(Font.TRUETYPE_FONT, fonteLexendExa);
            lexendExa = lexendExa.deriveFont(Font.PLAIN, 24);
        }
        catch(Exception e){

        }

        empresaControl = new EmpresaControl();
        funcionarioControl = new FuncionarioControl();
        sessao = relembrarSessao();

        funcionarioControl = new FuncionarioControl();
        //sessão dos componentes
        verydarkgray = new Color(32,35,42);
        darkred = new Color(155,27,48);

        lblTitulo = new RFLabel("CADASTRAR FUNCIONÁRIO");
        lblCpf = new RFLabel("CPF do funcionário");
        lblNome = new RFLabel("Nome do funcionário");
        lblSobrenome = new RFLabel("Sobrenome do funcionário");
        lblSetor = new RFLabel("Setor do funcionário");

        txtCpf = new RFTextField();
        txtNome = new RFTextField();
        txtSobrenome = new RFTextField();
        txtSetor = new RFTextField();

        btnCadastrar = new RFButton("Cadastrar");

        pnlCabecalho = new JPanel(new GridBagLayout());
        pnlCenter = new JScrollPane();
        pnlConteudo = new JPanel(new GridBagLayout());

        gridBagConstraints = new GridBagConstraints();
        constraintsCabecalho = new GridBagConstraints();

        insetsCabecalho = new Insets(75,0,75,0);
        insets = new Insets(10,0,25,0);
        insetsMin = new Insets(5,0,10,0);

        scrollBar = pnlCenter.getVerticalScrollBar();
        //fim da sessão

        //sessão da mudança de componentes
        //quicksandatt = lblTitulo.getFont().deriveFont(Font.PLAIN,40);
        lblTitulo.setFont(lexendExa);

        pnlCabecalho.setPreferredSize(new Dimension(640,150));
        pnlCabecalho.setBackground(darkred);
        pnlConteudo.setBackground(verydarkgray);

        pnlCenter.getVerticalScrollBar().setUI(new RFScrollBar());
        pnlCenter.getHorizontalScrollBar().setUI(new RFScrollBar());

        scrollBar.setUnitIncrement(8);
        scrollBar.setBlockIncrement(50);
        //fim da sessão

        //sessão dos setters
        setTitle("RFIND - Cadastrar funcionário");
        setSize(480,640);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        //fim da sessão

        //sessão da adição de componentes
        constraintsCabecalho.insets = insetsCabecalho;
        pnlCabecalho.add(lblTitulo,constraintsCabecalho);

        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblCpf,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtCpf,gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblNome,gridBagConstraints);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtNome,gridBagConstraints);
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblSobrenome,gridBagConstraints);
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtSobrenome,gridBagConstraints);
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = insetsMin;
        pnlConteudo.add(lblSetor,gridBagConstraints);
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = insets;
        pnlConteudo.add(txtSetor,gridBagConstraints);
        gridBagConstraints.gridy = 8;
        pnlConteudo.add(btnCadastrar,gridBagConstraints);

        pnlCenter.setViewportView(pnlConteudo);

        add(pnlCabecalho,BorderLayout.NORTH);
        add(pnlCenter,BorderLayout.CENTER);
        //fim da sessão

        //sessão dos listeners
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Empresa empresa = empresaControl.findByCnpj(sessao.getCnpj());
                    Funcionario funcionario = new Funcionario(txtCpf.getText(),txtNome.getText(),txtSobrenome.getText(),txtSetor.getText(),empresa);
                    funcionarioControl.insert(funcionario);
                    RFMessageDialog.showMessageDialog(null,"Funcionário cadastrado com sucesso!","Aviso do sistema");
                    txtCpf.setText(null);
                    txtNome.setText(null);
                    txtSobrenome.setText(null);
                    txtSetor.setText(null);
                    dispose();
                }
                catch(Exception ex){
                    RFMessageDialog.showMessageDialog(null,"Preencha todos os campos corretamente!","Erro");
                }
            }
        });
        //fim da sessão

        setVisible(true);
    }
    private Sessao relembrarSessao(){
        List<Sessao> sessao = Sessao.getSessao();
        return sessao.getFirst();
    }
}