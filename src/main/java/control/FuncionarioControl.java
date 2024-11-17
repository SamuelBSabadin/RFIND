package control;

import jakarta.persistence.*;
import model.Empresa;
import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioControl {
    private Empresa empresa;

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rfind-db");
    private EntityManager entityManager;
    public FuncionarioControl()
    {
        entityManager = entityManagerFactory.createEntityManager();
    }
    public void insert(Funcionario funcionario){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        //código do gpt
        Empresa empresa = funcionario.getEmpresa();
        if (empresa != null && !entityManager.contains(empresa)) {
            empresa = entityManager.merge(empresa);
            funcionario.setEmpresa(empresa);
        }
        //fim
        entityManager.persist(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public void update(int id, String cpf, String nome, String sobrenome, String setor){
        Funcionario funcionario = entityManager.find(Funcionario.class,id);
        if(cpf == null||cpf.isEmpty()){
            funcionario.setCpf(funcionario.getCpf());
        }
        else{
            funcionario.setCpf(cpf);
        }
        if(nome == null||nome.isEmpty()){
            funcionario.setNome(funcionario.getNome());
        }
        else{
            funcionario.setNome(nome);
        }
        if(sobrenome == null||sobrenome.isEmpty()){
            funcionario.setSobrenome(funcionario.getSobrenome());
        }
        else{
            funcionario.setSobrenome(sobrenome);
        }
        if(setor == null||setor.isEmpty()){
            funcionario.setSetor(funcionario.getSetor());
        }
        else{
            funcionario.setSetor(setor);
        }
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void delete(Funcionario funcionario){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(funcionario);
        entityManager.getTransaction().commit();//se algo falhar, comentar essa linha
        entityManager.close();
    }

    public Funcionario findById(Empresa empresa,int id){
        List<Funcionario> unico = new ArrayList<>();
        for(Funcionario f : getDesativados(empresa)){
            if(f.getId() == id){
                unico.add(f);
                break;
            }
        }
        Funcionario funcionario = unico.getFirst();
        return funcionario;
    }

    public void desativaFuncionario(int id){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Funcionario funcionario = entityManager.find(Funcionario.class,id);
        funcionario.setAtivado(false);
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void ativaFuncionario(int id){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Funcionario funcionario = entityManager.find(Funcionario.class,id);
        funcionario.setAtivado(true);
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Funcionario> getDesativados(Empresa empresa){
        List<Funcionario> desativados = new ArrayList<>();
        for(Funcionario f : empresa.getFuncionarios()){
            if(!f.getAtivado()){
                desativados.add(f);
            }
        }
        return desativados;
    }
}