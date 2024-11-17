package view;

import control.EmpresaControl;
import control.FuncionarioControl;
import model.Empresa;
import model.Funcionario;
import model.Sessao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class FrmDesativados extends JFrame {
    private EmpresaControl empresaControl;
    private FuncionarioControl funcionarioControl;

    private Color darkred;
    private Color verydarkgray;

    private JPanel pnlCabecalho;
    private JPanel pnlConteudo;
    private JScrollPane pnlCenter;
    private JPanel pnlRodape;

    private RFTable tblFuncDes;
    private DefaultTableModel dados;

    private RFButton btnExcluir;
    private RFButton btnReativar;

    private RFTextField txtExcluir;
    private RFTextField txtReativar;

    private RFButton btnExcluirDefinitivo;
    private RFButton btnReativarDefinitivo;

    private GridBagConstraints constraints;
    private GridBagConstraints gridBagConstraints;

    private RFLabel lblTitulo;

    private String strPesquisa;

    private JFrame frmPromptExcluir;
    private JFrame frmPromptAtivar;

    public FrmDesativados(){
        empresaControl = new EmpresaControl();
        funcionarioControl = new FuncionarioControl();

        //sessão dos componentes
        darkred = new Color(155,27,48);
        verydarkgray = new Color(32,35,42);

        pnlCabecalho = new JPanel();
        pnlConteudo = new JPanel();
        pnlCenter = new JScrollPane();
        pnlRodape = new JPanel();

        lblTitulo = new RFLabel("Funcionários desativados");

        tblFuncDes = new RFTable();
        dados = new DefaultTableModel();

        btnExcluir = new RFButton("Excluir funcionário");
        btnReativar = new RFButton("Reativar funcionário");

        btnExcluirDefinitivo = new RFButton("Excluir");
        btnReativarDefinitivo = new RFButton("Reativar");

        txtExcluir = new RFTextField();
        txtReativar = new RFTextField();
        //fim da sessão

        //sessão da mudança de componentes
        pnlCabecalho.setLayout(new GridBagLayout());
        pnlCabecalho.setBackground(darkred);
        pnlCabecalho.setPreferredSize(new Dimension(960,180));

        pnlConteudo.setBackground(verydarkgray);

        pnlRodape.setBackground(verydarkgray);
        pnlRodape.setLayout(new GridBagLayout());
        pnlRodape.setPreferredSize(new Dimension(960,100));
        pnlCenter.getViewport().setBackground(verydarkgray);

        Font quicksandatt = lblTitulo.getFont().deriveFont(Font.PLAIN,56);
        lblTitulo.setFont(quicksandatt);

        constraints = new GridBagConstraints();
        gridBagConstraints = new GridBagConstraints();
        //fim da sessão

        //sessão dos setters
        setSize(960,640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("RFIND - Funcionários desativados");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        inicializaTabela();
        //fim da sessão

        //sessão da adição de componentes
        constraints.insets = new Insets(90,0,90,0);
        pnlCabecalho.add(lblTitulo,constraints);

        pnlConteudo.add(pnlCenter);

        constraints.insets = new Insets(50,20,50,20);
        pnlRodape.add(btnExcluir,constraints);
        constraints.gridx = 1;
        pnlRodape.add(btnReativar,constraints);

        add(pnlCabecalho,BorderLayout.NORTH);
        add(pnlCenter,BorderLayout.CENTER);
        add(pnlRodape,BorderLayout.SOUTH);
        //fim da sessão

        //sessão dos listeners
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                SwingUtilities.invokeLater(FrmGerenciamento::new);
            }
        });
        btnReativar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frmPromptAtivar == null)
                    criaFrmPromptReativacao();
                frmPromptAtivar.setVisible(true);
            }
        });
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frmPromptExcluir == null)
                    criaFrmPromptExclusao();
                frmPromptExcluir.setVisible(true);
            }
        });
        //fim da sessão

        setVisible(true);
    }

    public List<Funcionario> selectAllDestivados()
    {
        List<Funcionario> listaTodos = new ArrayList<>();
        Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
        for(Funcionario f : empresa.getFuncionarios()){
            if(!f.getAtivado())
                listaTodos.add(f);
        }
        return listaTodos;
    }

    private void inicializaTabela(){
        Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
        dados.addColumn("ID");
        dados.addColumn("Nome");
        dados.addColumn("Sobrenome");
        dados.addColumn("Setor");
        dados.setNumRows(0);
        for(Funcionario funcionario : selectAllDestivados()){
            dados.addRow(new Object[]{funcionario.getId(),funcionario.getNome(),funcionario.getSobrenome(),funcionario.getSetor()});
        }
        tblFuncDes.setModel(dados);
        pnlCenter.setViewportView(tblFuncDes);
    }

    private Sessao relembraSessao(){
        List<Sessao> sessao = Sessao.getSessao();
        Sessao s = sessao.getFirst();
        return s;
    }

    private void atualizaTabela(){
        Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
        dados.setNumRows(0);
        for(Funcionario funcionario : selectAllDestivados()){
            dados.addRow(new Object[]{funcionario.getId(),funcionario.getNome(),funcionario.getSobrenome(),funcionario.getSetor()});
        }
        tblFuncDes.setModel(dados);
        //pnlCenter.setViewportView(tblFuncDes);
    }

    private void criaFrmPromptReativacao(){
        frmPromptAtivar = new JFrame();
        frmPromptAtivar.setTitle("Digite o ID do funcionário");
        frmPromptAtivar.setSize(450,250);
        frmPromptAtivar.getContentPane().setBackground(verydarkgray);
        frmPromptAtivar.setLayout(new GridBagLayout());
        frmPromptAtivar.setLocationRelativeTo(this);
        frmPromptAtivar.setResizable(false);
        frmPromptAtivar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(15,0,15,0);
        frmPromptAtivar.add(txtReativar,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        frmPromptAtivar.add(btnReativarDefinitivo,gridBagConstraints);

        btnReativarDefinitivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Empresa empresa = empresaControl.findByCnpj(relembraSessao().getCnpj());
                    strPesquisa = txtReativar.getText();
                    txtReativar.setText(null);
                    funcionarioControl.ativaFuncionario(Integer.parseInt(strPesquisa));
                    RFMessageDialog.showMessageDialog(null,"Funcionário reativado com sucesso","Aviso do sistema");
                    frmPromptAtivar.dispose();
                    atualizaTabela();
                }
                catch(Exception ex){
                    RFMessageDialog.showMessageDialog(null,"Funcionário não encontrado","Erro");
                }
            }
        });
    }

    private void criaFrmPromptExclusao(){
        frmPromptExcluir = new JFrame();
        frmPromptExcluir.setTitle("Digite o ID do funcionário");
        frmPromptExcluir.setSize(450,250);
        frmPromptExcluir.getContentPane().setBackground(verydarkgray);
        frmPromptExcluir.setLayout(new GridBagLayout());
        frmPromptExcluir.setLocationRelativeTo(this);
        frmPromptExcluir.setResizable(false);
        frmPromptExcluir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(15,0,15,0);
        frmPromptExcluir.add(txtExcluir,gridBagConstraints);
        gridBagConstraints.gridy = 1;
        frmPromptExcluir.add(btnExcluirDefinitivo,gridBagConstraints);

        btnExcluirDefinitivo.addActionListener(new ActionListener() {
            Empresa empresa1 = empresaControl.findByCnpj(relembraSessao().getCnpj());
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    strPesquisa = txtExcluir.getText();
                    txtExcluir.setText(null);
                    funcionarioControl.deleteById(Integer.parseInt(strPesquisa));
                    RFMessageDialog.showMessageDialog(null,"Funcionário excluído com sucesso","Aviso do sistema");
                    frmPromptExcluir.dispose();
                    atualizaTabela();
                }
                catch(NullPointerException ex){//Exception ex
                    RFMessageDialog.showMessageDialog(null,"Digite o ID correto","Erro");
                }
                catch(IllegalArgumentException ex){
                    RFMessageDialog.showMessageDialog(null,"O funcionário está ativado","Erro");
                }
            }
        });
    }
}