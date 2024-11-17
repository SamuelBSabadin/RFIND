package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import control.EmpresaControl;
import control.FuncionarioControl;
import model.Empresa;
import model.Funcionario;
import model.Sessao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FrmGerenciamento extends JFrame {
    private JFrame frmPromptDesativar;
    private JFrame frmPromptPesquisa;

    private EmpresaControl empresaControl;
    private FuncionarioControl funcionarioControl;

    private String strPesquisa;

    private Color darkred;
    private Color verydarkgray;

    private JPanel pnlCabecalho;
    private JScrollPane pnlCenter;
    private JPanel pnlConteudo;
    private JPanel pnlRodape;

    private RFTable tblFuncionarios;
    private DefaultTableModel dados;

    private RFTextField txtPesquisa;
    private RFTextField txtDesativar;

    private RFButton btnAlterar;
    private RFButton btnExcluir;
    private RFButton btnPesquisar;
    private RFButton btnPesquisar2;
    private RFButton btnDesativar;

    private GridBagConstraints constraintsSecundarias;
    private GridBagConstraints gridBagConstraints;

    private Insets insetsCabecalho;
    private Insets insetsRodape;

    private RFLabel lblTitulo;

    //private JMenuBar menuBar = new JMenuBar();
    //private JMenu menu = new JMenu("Funcionário");
    //private JMenuItem menuItem = new JMenuItem("Desativados");
    private RFMenuBar menuBar;
    private RFMenu menu;
    private RFMenuItem menuItem;

    public FrmGerenciamento(){
        empresaControl = new EmpresaControl();
        funcionarioControl = new FuncionarioControl();

        //sessão dos componentes
        menuBar = new RFMenuBar();
        menu = new RFMenu("Funcionário");
        menuItem = new RFMenuItem("Desativados");

        darkred = new Color(155,27,48);
        verydarkgray = new Color(32,35,42);

        pnlCabecalho = new JPanel();
        pnlRodape = new JPanel();
        pnlCenter = new JScrollPane();
        pnlConteudo = new JPanel();

        tblFuncionarios = new RFTable();
        dados = new DefaultTableModel();

        txtPesquisa = new RFTextField();
        txtDesativar = new RFTextField();

        btnAlterar = new RFButton("Atualizar funcionário");
        btnExcluir = new RFButton("Desativar funcionário");
        btnPesquisar = new RFButton("Pesquisar funcionário");
        btnPesquisar2 = new RFButton("Pesquisar");
        btnDesativar = new RFButton("Desativar");

        lblTitulo = new RFLabel("Gerenciamento de funcionários");

        insetsCabecalho = new Insets(75,0,75,0);
        insetsRodape = new Insets(50,20,50,20);

        constraintsSecundarias = new GridBagConstraints();
        gridBagConstraints = new GridBagConstraints();
        //fim da sessão

        //sessão da mudança de componentes
        pnlCabecalho.setBackground(darkred);
        pnlCabecalho.setLayout(new GridBagLayout());
        pnlCabecalho.setPreferredSize(new Dimension(1280,180));

        pnlCenter.getViewport().setBackground(verydarkgray);
        pnlConteudo.setLayout(new GridBagLayout());

        pnlRodape.setBackground(verydarkgray);
        pnlRodape.setLayout(new GridBagLayout());
        pnlRodape.setPreferredSize(new Dimension(1280,100));

        Font quicksandatt = lblTitulo.getFont().deriveFont(Font.PLAIN,56);
        lblTitulo.setFont(quicksandatt);
        //fim da sessão

        //sessão dos setters
        setTitle("RFIND - Gerenciamento de funcionários");
        setSize(960,640);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        inicializaTabela();
        //fim da sessão

        //sessão da adição de componentes
        constraintsSecundarias.insets = insetsCabecalho;
        pnlCabecalho.add(lblTitulo,constraintsSecundarias);

        constraintsSecundarias.insets = insetsRodape;
        pnlRodape.add(btnAlterar,constraintsSecundarias);
        constraintsSecundarias.gridx = 1;
        pnlRodape.add(btnExcluir,constraintsSecundarias);
        constraintsSecundarias.gridx = 2;
        pnlRodape.add(btnPesquisar,constraintsSecundarias);

        pnlConteudo.add(pnlCenter);

        add(pnlCabecalho,BorderLayout.NORTH);
        add(pnlCenter,BorderLayout.CENTER);
        add(pnlRodape,BorderLayout.SOUTH);

        menuBar.add(menu);
        menu.add(menuItem);
        setJMenuBar(menuBar);
        //fim da sessão

        //sessão dos listeners
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(FrmDesativados::new);
            }
        });

        btnPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frmPromptPesquisa == null){
                    criaFrmPromptPesquisa();
                }
                frmPromptPesquisa.setVisible(true);
            }
        });
        btnAlterar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(FrmAtualizacao::new);
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frmPromptDesativar == null){
                    criaFrmPromptDesativacao();
                }
                frmPromptDesativar.setVisible(true);
            }
        });
        //fim da sessão

        setVisible(true);
    }
    private void inicializaTabela(){
        Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
        dados.addColumn("ID");
        dados.addColumn("Nome");
        dados.addColumn("Sobrenome");
        dados.addColumn("Setor");
        dados.setNumRows(0);
        for(Funcionario funcionario : selectAllAtivados()){
            dados.addRow(new Object[]{funcionario.getId(),funcionario.getNome(),funcionario.getSobrenome(),funcionario.getSetor()});
        }
        tblFuncionarios.setModel(dados);
        pnlCenter.setViewportView(tblFuncionarios);
    }
    private void atualizaTabela(String strPesquisa){
        Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
        dados.setNumRows(0);
        for(Funcionario funcionario : funcListaFuncionarios(strPesquisa)){
            dados.addRow(new Object[]{funcionario.getId(),funcionario.getNome(),funcionario.getSobrenome(),funcionario.getSetor()});
        }
        tblFuncionarios.setModel(dados);
        pnlCenter.setViewportView(tblFuncionarios);
    }
    private Sessao relembraSessao(){
        List<Sessao> sessao = Sessao.getSessao();
        Sessao s = sessao.getFirst();
        return s;
    }
    private void criaFrmPromptPesquisa(){
        frmPromptPesquisa = new JFrame();
        frmPromptPesquisa.setTitle("Pesquisar funcionário");
        frmPromptPesquisa.setSize(450,250);
        frmPromptPesquisa.getContentPane().setBackground(verydarkgray);
        frmPromptPesquisa.setLayout(new GridBagLayout());
        frmPromptPesquisa.setLocationRelativeTo(this);
        frmPromptPesquisa.setResizable(false);
        frmPromptPesquisa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(15,0,15,0);
        frmPromptPesquisa.add(txtPesquisa,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        frmPromptPesquisa.add(btnPesquisar2,gridBagConstraints);

        btnPesquisar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
                strPesquisa = txtPesquisa.getText();
                txtPesquisa.setText(null);
                atualizaTabela(strPesquisa);
                frmPromptPesquisa.dispose();
            }
        });
    }

    private void criaFrmPromptDesativacao(){
        frmPromptDesativar = new JFrame();
        frmPromptDesativar.setTitle("Digite o ID do funcionário");
        frmPromptDesativar.setSize(450,250);
        frmPromptDesativar.getContentPane().setBackground(verydarkgray);
        frmPromptDesativar.setLayout(new GridBagLayout());
        frmPromptDesativar.setLocationRelativeTo(this);
        frmPromptDesativar.setResizable(false);
        frmPromptDesativar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(15,0,15,0);
        frmPromptDesativar.add(txtDesativar,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        frmPromptDesativar.add(btnDesativar,gridBagConstraints);

        btnDesativar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
                    strPesquisa = txtDesativar.getText();
                    txtDesativar.setText(null);
                    funcionarioControl.desativaFuncionario(Integer.parseInt(strPesquisa));
                    frmPromptDesativar.dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
    }

    public List<Funcionario> funcListaFuncionarios(String strPesquisa){
        List<Funcionario> funcionarioList = new ArrayList<>();
        Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
        for(Funcionario f:selectAllAtivados()){
            if(String.valueOf(f.getId()).contains(strPesquisa)||f.getNome().contains(strPesquisa)||f.getSobrenome().contains(strPesquisa)||f.getSetor().contains(strPesquisa))
                funcionarioList.add(f);
        }
        return funcionarioList;
    }

    public List<Funcionario> selectAllAtivados(){
        List<Funcionario> listaTodos = new ArrayList<>();
        Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
        for(Funcionario f : empresa.getFuncionarios()){
            if(f.getAtivado())
                listaTodos.add(f);
        }
        return listaTodos;
    }
}