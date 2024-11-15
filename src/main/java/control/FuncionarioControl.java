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
        entityManager.persist(funcionario);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void deleteById(int id){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete f from Funcionario f where f.id = :id", Funcionario.class).setParameter(id,"id").executeUpdate();
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
    /*public List<Funcionario> selectWhereEmpresaId(Empresa empresa){
        if(!entityManager.isOpen())
            entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Funcionario> listaTodosNumaEmpresa = entityManager.createQuery("select f from Funcionario f where f.empresa = :empresa", Funcionario.class).setParameter("empresa",empresa).getResultList();
        entityManager.getTransaction().commit();//se algo falhar, comentar essa linha
        entityManager.close();
        return listaTodosNumaEmpresa;
    }*/
}
