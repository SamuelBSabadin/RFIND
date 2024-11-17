package model.DAO;

import jakarta.persistence.*;
import model.Empresa;
import model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    private Empresa empresa;

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rfind-db");
    private EntityManager entityManager;
    public FuncionarioDAO()
    {
        entityManager = entityManagerFactory.createEntityManager();
    }
    public void insert(Funcionario funcionario){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        if(!entityManager.getTransaction().isActive())
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

    public void update(int id, String cpf, String nome, String sobrenome, String setor){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        if(!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
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
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteById(int id){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        Funcionario funcionario = entityManager.find(Funcionario.class,id);
        if(funcionario == null)
            throw new NullPointerException();
        else if(!funcionario.getAtivado()){
            funcionario = entityManager.merge(funcionario);//garante que o usuário está persistido
            entityManager.remove(funcionario);
        }
        else{
            throw new IllegalArgumentException();
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void desativaFuncionario(int id){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        Funcionario funcionario = entityManager.find(Funcionario.class,id);
        funcionario.setAtivado(false);
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void ativaFuncionario(int id){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        Funcionario funcionario = entityManager.find(Funcionario.class, id);
        funcionario.setAtivado(true);
        entityManager.merge(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}