package control;

import model.Empresa;
import model.Funcionario;
import model.DAO.FuncionarioDAO;

public class FuncionarioControl {
    private FuncionarioDAO funcionarioDAO;
    public FuncionarioControl()
    {
        funcionarioDAO = new FuncionarioDAO();
    }

    public void insert(Funcionario funcionario){
        funcionarioDAO.insert(funcionario);
    }
    public void update(int id/*, String cpf*/, String nome, String sobrenome, String setor){
        funcionarioDAO.update(id/*,cpf*/,nome,sobrenome,setor);
    }
    public void deleteById(int id){
        funcionarioDAO.deleteById(id);
    }
    public void desativaFuncionario(int id){
        funcionarioDAO.desativaFuncionario(id);
    }
    public void ativaFuncionario(int id){
        funcionarioDAO.ativaFuncionario(id);
    }
    public void deleteByIdEmpresa(Empresa empresa, int id){
        funcionarioDAO.deleteByIdEmpresa(empresa,id);
    }
}