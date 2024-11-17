package control;

import model.Empresa;
import model.DAO.EmpresaDAO;
import java.util.List;

public class EmpresaControl {
    private EmpresaDAO empresaDAO;
    public EmpresaControl()
    {
        empresaDAO = new EmpresaDAO();
    }
    public void insert(Empresa empresa){
        empresaDAO.insert(empresa);
    }
    public List<Empresa> select(){
        return empresaDAO.select();
    }
    public Empresa findByCnpj(String cnpj){
        return empresaDAO.findByCnpj(cnpj);
    }
    public void deleteById(int id){
        empresaDAO.deleteById(id);
    }
}