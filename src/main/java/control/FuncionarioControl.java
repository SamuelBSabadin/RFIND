package control;

import jakarta.persistence.*;
import model.Empresa;
import model.Funcionario;

import java.util.List;

public class FuncionarioControl {
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
    public void deleteById(int id){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Funcionario funcionario = entityManager.find(Funcionario.class,id);
        entityManager.remove(funcionario);
        entityManager.getTransaction().commit();//se algo falhar, comentar essa linha
        entityManager.close();
    }
    //método apenas para testes
    public List<Funcionario> selectAll(){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Funcionario> listaTodos = entityManager.createQuery("select f from Funcionario f", Funcionario.class).getResultList();
        entityManager.getTransaction().commit();//se algo falhar, comentar essa linha
        entityManager.close();
        return listaTodos;
    }
}
